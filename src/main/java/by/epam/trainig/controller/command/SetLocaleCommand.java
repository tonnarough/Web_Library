package by.epam.trainig.controller.command;

import by.epam.trainig.controller.RequestFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public enum SetLocaleCommand implements Command {
    INSTANCE(RequestFactory.getInstance());

    private final RequestFactory requestFactory;

    private static final String LANGUAGE_ATTRIBUTE = "local";
    private static final String URL = "url";

    SetLocaleCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        request.addToSession(LANGUAGE_ATTRIBUTE, request.getParameter(LANGUAGE_ATTRIBUTE));

        return requestFactory.createRedirectResponse(request.retrieveFromSession(URL).get().toString());
    }
}
