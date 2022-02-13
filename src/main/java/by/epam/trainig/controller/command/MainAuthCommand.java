package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;

import javax.servlet.ServletException;
import java.io.IOException;

public enum MainAuthCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.getInstance());

    private static final String MAIN_AUTH = "page.main_auth";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    MainAuthCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        return requestFactory.createForwardResponse(propertyContext.get(MAIN_AUTH));
    }

}
