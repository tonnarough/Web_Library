package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public enum LogoutCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.getInstance());

    private static final String INDEX_PAGE = "go_to_index_page";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    LogoutCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        request.clearSession();
        return requestFactory.createRedirectResponse(propertyContext.get(INDEX_PAGE));
    }

}
