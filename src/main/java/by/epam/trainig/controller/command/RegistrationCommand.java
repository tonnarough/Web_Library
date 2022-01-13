package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PagePath;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.service.impl.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    private final String LOGIN_REQUEST_PARAMETR_NAME = "login";
    private final String PASSWORD_REQUEST_PARAMETR_NAME = "password";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        final String login = req.getParameter(LOGIN_REQUEST_PARAMETR_NAME);
        final String password = req.getParameter(PASSWORD_REQUEST_PARAMETR_NAME);

        //TODO: normal registration
        boolean flag = false;

        if (flag) {
            resp.sendRedirect("controller?command=go_to_main_auth_page&Registration info=" + login + " " + password);
        } else {
            System.out.println(login + " " + password);
            resp.sendRedirect("controller?command=GO_TO_REGISTRATION_PAGE");

        }
    }
}
