package by.epam.trainig.controller;

import by.epam.trainig.controller.command.Command;
import by.epam.trainig.controller.command.CommandRequest;
import by.epam.trainig.controller.command.CommandResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final String COMMAND_NAME_PARAM = "command";

    private final RequestFactory requestFactory = RequestFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commandName = req.getParameter(COMMAND_NAME_PARAM);
        Command command = Command.of(commandName);
        CommandRequest commandRequest = requestFactory.createRequest(req);
        CommandResponse commandResponse = command.execute(commandRequest);
        processWithResponse(req, resp, commandResponse);
    }

    private void processWithResponse(HttpServletRequest req, HttpServletResponse resp, CommandResponse commandResponse) throws ServletException, IOException {
        if (commandResponse.isRedirect()) {
            resp.sendRedirect(commandResponse.getPath());
        } else {
            final RequestDispatcher requestDispatcher = req.getRequestDispatcher(commandResponse.getPath());
            requestDispatcher.forward(req,resp);
        }
    }


}
