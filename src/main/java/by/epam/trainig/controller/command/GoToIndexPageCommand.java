package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public enum GoToIndexPageCommand implements Command{
    INSTANCE(PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final String INDEX_PAGE = "page.index";

    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    GoToIndexPageCommand(PropertyContext propertyContext, RequestFactory requestFactory) {
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        return requestFactory.createForwardResponse(propertyContext.get(INDEX_PAGE));
    }
}
