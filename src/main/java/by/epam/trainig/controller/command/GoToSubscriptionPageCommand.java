package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;

public enum GoToSubscriptionPageCommand implements Command{
    INSTANCE(PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final String SUBSCRIPTION_PAGE = "page.subscription";

    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    GoToSubscriptionPageCommand(PropertyContext propertyContext, RequestFactory requestFactory) {
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {

        return requestFactory.createForwardResponse(propertyContext.get(SUBSCRIPTION_PAGE));
    }
}
