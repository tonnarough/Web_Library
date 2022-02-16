package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;

public enum GoToAuthorBooksPageCommand implements Command {
    INSTANCE(PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final String ADDING_BOOK_PAGE = "page.adding_book";
    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";

    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    GoToAuthorBooksPageCommand(PropertyContext propertyContext, RequestFactory requestFactory) {
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {

        request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST)));

        return requestFactory.createForwardResponse(propertyContext.get(ADDING_BOOK_PAGE));

    }

}
