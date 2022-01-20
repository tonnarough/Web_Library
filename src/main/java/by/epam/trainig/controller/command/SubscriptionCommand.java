package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.service.UserService;

import java.util.Optional;

public enum SubscriptionCommand implements Command{
    INSTANCE(UserService.getInstance(), PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final String SUBSCRIPTION_PAGE = "go_to_subscription_page";
    private static final String MAIN_AUTH_PAGE = "go_to_main_auth_page";

    private static final String LOGIN_REQUEST_PARAMETR_NAME = "login";
    private static final String PASSWORD_REQUEST_PARAMETR_NAME = "password";

    private final UserService userService;
    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    SubscriptionCommand(UserService userService, PropertyContext propertyContext, RequestFactory requestFactory) {
        this.userService = userService;
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {

        final String login = request.getParameter(LOGIN_REQUEST_PARAMETR_NAME);
        final String password = request.getParameter(PASSWORD_REQUEST_PARAMETR_NAME);
        final Optional<User> user = userService.authenticate(login, password);

        if (user.isPresent()) {
            return requestFactory.createRedirectResponse(propertyContext.get(MAIN_AUTH_PAGE));
        }

        return requestFactory.createRedirectResponse(propertyContext.get(SUBSCRIPTION_PAGE));

    }
}
