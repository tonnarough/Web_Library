package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

public enum RegistrationCommand implements Command {
    INSTANCE(UserService.getInstance(), PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    private static final String REGISTRATION_PAGE = "go_to_registration_page";
    private static final String SUBSCRIPTION_PAGE = "go_to_subscription_page";
    private static final String ERROR_PAGE = "page.error";

    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String ERROR_REGISTRATION_PASS_ATTRIBUTE = "registrationErrorPassMessage";
    private static final String ERROR_REGISTRATION_PASS_MESSAGE = "User with this login already exists";

    private static final String LOGIN_REQUEST_PARAMETR_NAME = "login";
    private static final String PASSWORD_REQUEST_PARAMETR_NAME = "password";
    private static final String LASTNAME_REQUEST_PARAMETR_NAME = "last_name";
    private static final String FIRSTNAME_REQUEST_PARAMETR_NAME = "first_name";
    private static final String FATHERNAME_REQUEST_PARAMETR_NAME = "father_name";
    private static final String EMAIL_REQUEST_PARAMETR_NAME = "email";
    private static final String MOBILE_REQUEST_PARAMETR_NAME = "mobile";
    private static final String BIRTHDAY_REQUEST_PARAMETR_NAME = "birthday";

    private final UserService userService;
    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    RegistrationCommand(UserService userService, PropertyContext propertyContext, RequestFactory requestFactory) {
        this.userService = userService;
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException {

        final String login = request.getParameter(LOGIN_REQUEST_PARAMETR_NAME);
        final String password = request.getParameter(PASSWORD_REQUEST_PARAMETR_NAME);
        final String lastName = request.getParameter(LASTNAME_REQUEST_PARAMETR_NAME);
        final String firstName = request.getParameter(FIRSTNAME_REQUEST_PARAMETR_NAME);
        final String fatherName = request.getParameter(FATHERNAME_REQUEST_PARAMETR_NAME);
        final String email = request.getParameter(EMAIL_REQUEST_PARAMETR_NAME);
        final String mobile = request.getParameter(MOBILE_REQUEST_PARAMETR_NAME);
        final Date birthday = Date.valueOf(request.getParameter(BIRTHDAY_REQUEST_PARAMETR_NAME));

        if (!userService.isExists(login)) {

            try {

                userService.registration(login, password, lastName, firstName, fatherName, email, mobile, birthday);

                Optional<User> user = userService.findBy(LOGIN_REQUEST_PARAMETR_NAME, login);
                request.createSession();
                request.addToSession(USER_SESSION_ATTRIBUTE_NAME, user.get());

                return requestFactory.createRedirectResponse(propertyContext.get(SUBSCRIPTION_PAGE));

            } catch (ServiceException e) {

                logger.error("Failed registration", e);
                return requestFactory.createForwardResponse(propertyContext.get(ERROR_PAGE));

            }
        } else {

            request.addAttributeToJsp(ERROR_REGISTRATION_PASS_ATTRIBUTE, ERROR_REGISTRATION_PASS_MESSAGE);
            return requestFactory.createRedirectResponse(propertyContext.get(REGISTRATION_PAGE));

        }
    }
}
