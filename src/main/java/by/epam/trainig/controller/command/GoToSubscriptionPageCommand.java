package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.SubscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public enum GoToSubscriptionPageCommand implements Command {
    INSTANCE(SubscriptionService.getInstance(), PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final Logger logger = LogManager.getLogger(GoToSubscriptionPageCommand.class);

    private static final String SUBSCRIPTION_PAGE = "page.subscription";
    private static final String SUBSCRIPTION_PARAMETER = "subscriptionTypes";
    private static final String ERROR_PAGE = "page.error";
    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";

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

        try {

            request.addAttributeToJsp(SUBSCRIPTION_PARAMETER, subscriptionService.findAllTypes());

            request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST)));

            return requestFactory.createForwardResponse(propertyContext.get(SUBSCRIPTION_PAGE));

        } catch (ServiceException e) {

            logger.error("Failed finding of subscription types", e);
            return requestFactory.createForwardResponse(propertyContext.get(ERROR_PAGE));

        }
    }

}
