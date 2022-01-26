package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;

public enum GoToMainUnauthPageCommand implements Command {
    INSTANCE(PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final String MAIN_UNAUTH_PAGE = "page.main_unauth";
    private static final String URL = "url";
    private static final String PARAMETER_NAME = "?command=";
    private static final String PARAMETER_FROM_REQUEST = "command";

    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    GoToMainUnauthPageCommand(PropertyContext propertyContext, RequestFactory requestFactory) {
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {

        final String url = request.getRequestURL() + PARAMETER_NAME + request.getParameter(PARAMETER_FROM_REQUEST);

        request.addToSession(URL, url);

        return requestFactory.createForwardResponse(propertyContext.get(MAIN_UNAUTH_PAGE));
    }
}
