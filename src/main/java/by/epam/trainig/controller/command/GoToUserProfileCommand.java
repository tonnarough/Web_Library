package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;
import by.epam.trainig.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

public enum GoToUserProfileCommand implements Command{
    INSTANCE(PropertyContext.getInstance(), RequestFactory.getInstance(), UserService.getInstance());

    private static final Logger logger = LogManager.getLogger(GoToSubscriptionPageCommand.class);

    private static final String ERROR_PAGE = "page.error";
    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";
    private static final String USER_PROFILE = "page.user_profile";
    private static final String USER_PARAMETER = "user";
    private static final String LOGIN = "login";
    private static final String USER_DETAIL_PARAMETER = "userDetail";

    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;
    private final UserService userService;

    GoToUserProfileCommand(PropertyContext propertyContext, RequestFactory requestFactory, UserService userService) {
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
        this.userService = userService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        request.addToSession(URL, urlBuilder(request.getRequestURL(),
                request.getParameter(PARAMETER_FROM_REQUEST)));

        final Optional<Object> userFromSession = request.retrieveFromSession(USER_PARAMETER);
        final User user;

        if (userFromSession.isPresent() && userFromSession.get() instanceof User) {

            user = (User) userFromSession.get();

        } else {

            return requestFactory.createRedirectResponse(propertyContext.get(ERROR_PAGE));

        }

       Optional<User> userFromDB = userService.findBy(LOGIN, user.getLogin());

        if(userFromDB.isEmpty()){

            return requestFactory.createRedirectResponse(propertyContext.get(ERROR_PAGE));

        }

        request.addAttributeToJsp(USER_PARAMETER, userFromDB.get());
        request.addAttributeToJsp(USER_DETAIL_PARAMETER, userFromDB.get().getUserDetail());

        return requestFactory.createForwardResponse(propertyContext.get(USER_PROFILE));

    }
}
