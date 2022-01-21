package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.service.SubscriptionService;

import java.util.List;

public enum GoToSubscriptionPageCommand implements Command {
    INSTANCE(SubscriptionService.getInstance(), PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final String SUBSCRIPTION_PAGE = "page.subscription";
    private static final String SUBSCRIPTION_PARAMETER = "subscriptionTypes";

    private final SubscriptionService subscriptionService;
    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    GoToSubscriptionPageCommand(SubscriptionService subscriptionService, PropertyContext propertyContext, RequestFactory requestFactory) {
        this.subscriptionService = subscriptionService;
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {

        final List<SubscriptionType> subscriptionTypes = subscriptionService.findAllTypes();

        request.addAttributeToJsp(SUBSCRIPTION_PARAMETER, subscriptionTypes);

        return requestFactory.createForwardResponse(propertyContext.get(SUBSCRIPTION_PAGE));
    }
}
