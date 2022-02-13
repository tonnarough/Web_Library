package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.service.SubscriptionService;
import by.epam.trainig.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.Optional;

public enum LoginCommand implements Command {
    INSTANCE(UserService.getInstance(), SubscriptionService.getInstance(), PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final String LOGIN_PAGE = "go_to_login_page";
    private static final String MAIN_AUTH_PAGE = "go_to_main_auth_page";
    private static final String SUBSCRIPTION_PAGE = "go_to_subscription_page";
    private static final String ERROR_PAGE = "page.error";

    private static final String LOGIN_REQUEST_PARAMETR_NAME = "login";
    private static final String PASSWORD_REQUEST_PARAMETR_NAME = "password";

    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String ERROR_LOGIN_PASS_ATTRIBUTE = "errorLoginPassMessage";
    private static final String ERROR_LOGIN_PASS_MESSAGE = "Invalid login or password";

    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    LoginCommand(UserService userService, SubscriptionService subscriptionService, PropertyContext propertyContext, RequestFactory requestFactory) {
        this.userService = userService;
        this.subscriptionService = subscriptionService;
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {

        if (request.sessionExists() && request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME).isPresent()) {

            //user already logged in
            return requestFactory.createForwardResponse(propertyContext.get(ERROR_PAGE));

        }

        final String login = request.getParameter(LOGIN_REQUEST_PARAMETR_NAME);
        final String password = request.getParameter(PASSWORD_REQUEST_PARAMETR_NAME);
        final Optional<User> user = userService.authenticate(login, password);

        if (user.isEmpty()) {

            request.addAttributeToJsp(ERROR_LOGIN_PASS_ATTRIBUTE, ERROR_LOGIN_PASS_MESSAGE);
            return requestFactory.createRedirectResponse(propertyContext.get(LOGIN_PAGE));

        }

        final Optional<Subscription> subscription = subscriptionService.findByUserId(user.get().getId());

        //noinspection OptionalGetWithoutIsPresent
        if (subscription.get().getEndDate().getTime() <= (new Date().getTime()) && user.get().getRole().getId() != 1) {

            request.clearSession();
            request.createSession();
            request.addToSession(USER_SESSION_ATTRIBUTE_NAME, user.get());

            return requestFactory.createRedirectResponse(propertyContext.get(SUBSCRIPTION_PAGE));

        }

        request.clearSession();
        request.createSession();
        request.addToSession(USER_SESSION_ATTRIBUTE_NAME, user.get());

        return requestFactory.createRedirectResponse(propertyContext.get(MAIN_AUTH_PAGE));
    }

}
