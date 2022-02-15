package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;
import by.epam.trainig.exception.ControllerException;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

public enum UpdateUserProfileCommand implements Command {
    INSTANCE(UserService.getInstance(), UserDetailService.getInstance(), RequestFactory.getInstance(), PropertyContext.getInstance());

    private static final Logger logger = LogManager.getLogger(UpdateUserProfileCommand.class);

    private static final String ADDING_BOOK = "go_to_adding_book_page";

    private static final String ERROR_PAGE = "page.error";
    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";

    private static final String ID = "id";

    private static final String UPDATE_ERROR_MESSAGE = "Error while updating";
    private static final String UPDATING_ATTRIBUTE = "updating";
    private static final String SUCCESSFUL_UPDATE_MESSAGE = "Completed!";

    private static final String USER_PARAMETER = "user";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String FATHER_NAME = "fatherName";
    private static final String EMAIL = "email";
    private static final String MOBILE = "mobile";
    private static final String PASSWORD = "password";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String CONFIRM_OLD_PASSWORD = "confirmOldPassword";
    private static final String EMPTY_LINE = "";

    private final UserService userService;
    private final UserDetailService userDetailService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    UpdateUserProfileCommand(UserService userService, UserDetailService userDetailService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.userService = userService;
        this.userDetailService = userDetailService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST)));

        final Optional<Object> userFromSession = request.retrieveFromSession(USER_PARAMETER);
        final User user;

        if (userFromSession.isPresent() && userFromSession.get() instanceof User) {

            user = (User) userFromSession.get();

        } else {

            return requestFactory.createRedirectResponse(propertyContext.get(ERROR_PAGE));

        }

        try {

            userUpdates(request, user);
            userDetailsUpdates(request, user.getUserDetail());

        } catch (ControllerException e) {

            request.addAttributeToJsp(UPDATING_ATTRIBUTE, UPDATE_ERROR_MESSAGE);
            return requestFactory.createRedirectResponse(propertyContext.get(ERROR_PAGE));
        }


        request.addAttributeToJsp(UPDATING_ATTRIBUTE, SUCCESSFUL_UPDATE_MESSAGE);
        return requestFactory.createRedirectResponse(propertyContext.get(ADDING_BOOK));

    }

    private void userUpdates(CommandRequest request, User user) throws ControllerException {

        final boolean isUserPasswordChanged = !request.getParameter(NEW_PASSWORD).equals(EMPTY_LINE);

        final boolean passwordIsConfirmed = userService.verifyPassword(request.getParameter(CONFIRM_OLD_PASSWORD), user.getPassword());

        if (isUserPasswordChanged && passwordIsConfirmed) {

            try {

                userService.updatePassword(PASSWORD, request.getParameter(NEW_PASSWORD), ID, user.getId());

            } catch (ServiceException e) {

                logger.error("Failed of user password updating");
                throw new ControllerException(e);
            }
        }
    }

    private void userDetailsUpdates(CommandRequest request, UserDetail userDetail) throws ControllerException {

        final boolean isUserLastNameChanged = !request.getParameter(LAST_NAME).equals(userDetail.getLastName());

        final boolean isUserFirstNameChanged = !request.getParameter(FIRST_NAME).equals(userDetail.getFirstName());

        final boolean isUserFatherNameChanged = !request.getParameter(FATHER_NAME).equals(userDetail.getFatherName());

        final boolean isUserEmailChanged = !request.getParameter(EMAIL).equals(userDetail.getEmail());

        final boolean isUserMobileChanged = !request.getParameter(MOBILE).equals(userDetail.getMobile());

        try {

            userDetailService.updateIfChanged(isUserLastNameChanged, LAST_NAME, request.getParameter(LAST_NAME), ID, userDetail.getId());

            userDetailService.updateIfChanged(isUserFirstNameChanged, FIRST_NAME, request.getParameter(FIRST_NAME), ID, userDetail.getId());

            userDetailService.updateIfChanged(isUserFatherNameChanged, FATHER_NAME, request.getParameter(FATHER_NAME), ID, userDetail.getId());

            userDetailService.updateIfChanged(isUserEmailChanged, EMAIL, request.getParameter(EMAIL), ID, userDetail.getId());

            userDetailService.updateIfChanged(isUserMobileChanged, MOBILE, request.getParameter(MOBILE), ID, userDetail.getId());

        } catch (ServiceException e) {

            logger.error("Failed of user detail updating");
            throw new ControllerException(e);
        }

    }

}
