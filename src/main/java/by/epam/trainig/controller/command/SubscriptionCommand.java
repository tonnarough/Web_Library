package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.service.SubscriptionService;

public enum SubscriptionCommand implements Command{
    INSTANCE(SubscriptionService.getInstance(), PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final String MAIN_AUTH_PAGE = "go_to_main_auth_page";


    private final SubscriptionService subscriptionService;
    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    SubscriptionCommand(SubscriptionService subscriptionService, PropertyContext propertyContext, RequestFactory requestFactory) {
        this.subscriptionService = subscriptionService;
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {

        return requestFactory.createRedirectResponse(propertyContext.get(MAIN_AUTH_PAGE));
    }
}
