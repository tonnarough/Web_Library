package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PagePath;
import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.BookService;
import by.epam.trainig.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public enum GoToMainAuthPageCommand implements Command {
    INSTANCE(PropertyContext.getInstance(), RequestFactory.getInstance(), BookService.getInstance());

    private static final Logger logger = LogManager.getLogger(GoToMainAuthPageCommand.class);

    private static final String MAIN_AUTH_PAGE = "page.main_auth";
    private static final String ERROR_PAGE = "page.error";

    private static final String BOOKS_TO_JSP_PARAMETER = "books";
    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";

    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;
    private final BookService bookService;

    GoToMainAuthPageCommand(PropertyContext propertyContext, RequestFactory requestFactory, BookService bookService) {
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
        this.bookService = bookService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {

        request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST)));

        try {

            request.addAttributeToJsp(BOOKS_TO_JSP_PARAMETER, bookService.findAll());

        } catch (ServiceException e) {

            logger.error("Books not found", e);
            return requestFactory.createForwardResponse(propertyContext.get(ERROR_PAGE));

        }

        return requestFactory.createForwardResponse(propertyContext.get(MAIN_AUTH_PAGE));

    }
}
