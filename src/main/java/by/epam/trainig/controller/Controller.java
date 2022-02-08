package by.epam.trainig.controller;

import by.epam.trainig.controller.command.Command;
import by.epam.trainig.controller.command.CommandRequest;
import by.epam.trainig.controller.command.CommandResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
public class Controller extends HttpServlet {

    private static final String COMMAND_NAME_PARAM = "command";
    private static final String TEXT_HTML = "text/html";
    private static final String CONTENT_TYPE = "APPLICATION/OCTET-STREAM";
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String REQUEST_PARAMETER = "download";
    private static final String ATTACHMENT = "attachment; filename=";

    private final RequestFactory requestFactory = RequestFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter(COMMAND_NAME_PARAM);
        Command command = Command.of(commandName);
        CommandRequest commandRequest = requestFactory.createRequest(request);
        CommandResponse commandResponse = command.execute(commandRequest);
        processWithResponse(request, response, commandResponse);
    }

    private void processWithResponse(HttpServletRequest request, HttpServletResponse response, CommandResponse commandResponse) throws ServletException, IOException {

        if (commandResponse.getInputStream() != null) {
            downloadBooksFromServer(response, request.getParameter(REQUEST_PARAMETER), commandResponse);
        }

        if (commandResponse.isRedirect()) {

            if (commandResponse.getCookie() != null) {
                response.addCookie(commandResponse.getCookie());
            }

            response.sendRedirect(commandResponse.getPath());
        } else {
            final RequestDispatcher requestDispatcher = request.getRequestDispatcher(commandResponse.getPath());
            requestDispatcher.forward(request, response);
        }
    }

    private void downloadBooksFromServer(HttpServletResponse response, String fileName, CommandResponse commandResponse) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType(TEXT_HTML);
        response.setContentType(CONTENT_TYPE);
        response.setHeader(CONTENT_DISPOSITION, ATTACHMENT+"\""
                + fileName + "\"");
        int i;
        while ((i = commandResponse.getInputStream().read()) != -1) {
            out.write(i);
        }
        commandResponse.getInputStream().close();
        out.close();
    }

}
