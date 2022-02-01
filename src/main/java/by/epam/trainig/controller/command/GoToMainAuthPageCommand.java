package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PagePath;
import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.book.Book;
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
import java.util.List;

public enum GoToMainAuthPageCommand implements Command {
    INSTANCE(PropertyContext.getInstance(), RequestFactory.getInstance(), BookService.getInstance());

    private static final Logger logger = LogManager.getLogger(GoToMainAuthPageCommand.class);

    private static final String MAIN_AUTH_PAGE = "page.main_auth";

    private static final String BOOKS_TO_JSP_PARAMETER = "books";
    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String SECOND_PARAMETER = "&currentPage=";

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

        final int recordOnPage = 6;
        int currentPage;

        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        } else {
                currentPage = 1;
        }

        final List<Book> books = bookService.findAllBooks((currentPage - 1) * recordOnPage, recordOnPage);

        request.addAttributeToJsp(BOOKS_TO_JSP_PARAMETER, books);

        int numberOfRowsInDB = bookService.getNumberOfRows();

        int numberOfPages = (int) Math.ceil(numberOfRowsInDB * 1.0 / recordOnPage);

        request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST) +
                SECOND_PARAMETER + request.getParameter(CURRENT_PAGE)));
        request.addAttributeToJsp("numberOfPage", numberOfPages);
        request.addAttributeToJsp("currentPage", currentPage);


        return requestFactory.createForwardResponse(propertyContext.get(MAIN_AUTH_PAGE));

    }
}
