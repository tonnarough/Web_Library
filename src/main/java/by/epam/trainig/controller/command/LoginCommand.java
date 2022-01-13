package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PagePath;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.service.impl.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command {

    private final String LOGIN_REQUEST_PARAMETR_NAME = "login";
    private final String PASSWORD_REQUEST_PARAMETR_NAME = "password";
    private final UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String login = req.getParameter(LOGIN_REQUEST_PARAMETR_NAME);
        final String password = req.getParameter(PASSWORD_REQUEST_PARAMETR_NAME);
        final Optional<User> user = userService.authenticate(login, password);

        if (user.isPresent()) {
            resp.sendRedirect("controller?command=go_to_main_auth_page&Login info=" + login + " " + password);
        } else {
            resp.sendRedirect("controller?command=GO_TO_LOGIN_PAGE");
        }
    }
}
