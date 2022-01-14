package by.epam.trainig.controller.command;

import by.epam.trainig.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class RegistrationCommand implements Command {

    private final String LOGIN_REQUEST_PARAMETR_NAME = "login";
    private final String PASSWORD_REQUEST_PARAMETR_NAME = "password";
    private final String LASTNAME_REQUEST_PARAMETR_NAME = "last_name";
    private final String FIRSTNAME_REQUEST_PARAMETR_NAME = "first_name";
    private final String FATHERNAME_REQUEST_PARAMETR_NAME = "father_name";
    private final String EMAIL_REQUEST_PARAMETR_NAME = "email";
    private final String MOBILE_REQUEST_PARAMETR_NAME = "mobile";
    private final String BIRTHDAY_REQUEST_PARAMETR_NAME = "birthday";
    private final UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        final String login = req.getParameter(LOGIN_REQUEST_PARAMETR_NAME);
        final String password = req.getParameter(PASSWORD_REQUEST_PARAMETR_NAME);
        final String lastName = req.getParameter(LASTNAME_REQUEST_PARAMETR_NAME);
        final String firstName = req.getParameter(FIRSTNAME_REQUEST_PARAMETR_NAME);
        final String fatherName = req.getParameter(FATHERNAME_REQUEST_PARAMETR_NAME);
        final String email = req.getParameter(EMAIL_REQUEST_PARAMETR_NAME);
        final String mobile = req.getParameter(MOBILE_REQUEST_PARAMETR_NAME);
        final Date birthday;
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter(BIRTHDAY_REQUEST_PARAMETR_NAME));
            birthday = new Date(date.getTime());
            if (!userService.isExists(login)) {
                userService.registration(login, password, lastName, firstName, fatherName, email, mobile, birthday);
                resp.sendRedirect("controller?command=go_to_main_auth_page&Registration info=" + login + " " + password);
            } else {
                resp.sendRedirect("controller?command=GO_TO_REGISTRATION_PAGE");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
