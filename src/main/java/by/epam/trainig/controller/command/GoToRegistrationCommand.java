package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PagePath;
import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public enum GoToRegistrationCommand implements Command {
    INSTANCE(PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final String REGISTRATION_PAGE = "page.registration";
    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";

    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    GoToRegistrationCommand(PropertyContext propertyContext, RequestFactory requestFactory) {
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {

        request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST)));

        return requestFactory.createForwardResponse(propertyContext.get(REGISTRATION_PAGE));

    }
}
