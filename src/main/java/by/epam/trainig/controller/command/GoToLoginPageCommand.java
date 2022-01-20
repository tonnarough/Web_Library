package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PagePath;
import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public enum GoToLoginPageCommand implements Command {
    INSTANCE(PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final String LOGIN_PAGE = "page.login";

    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    GoToLoginPageCommand(PropertyContext propertyContext, RequestFactory requestFactory) {
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {

        return requestFactory.createForwardResponse(propertyContext.get(LOGIN_PAGE));
    }
}
