package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

public enum DeleteBookCommand implements Command {
    INSTANCE( BookService.getInstance(), RequestFactory.getInstance(), PropertyContext.getInstance());

    private static final Logger logger = LogManager.getLogger(DeleteBookCommand.class);

    private static final String BOOK_PARAMETER = "books";
    private static final String MAIN_AUTH_PAGE = "go_to_main_auth_page";
    private static final String ERROR_PAGE = "page.error";

    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";
    private static final String SECOND_PARAMETER = "&books=";

    private final BookService bookService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    DeleteBookCommand(BookService bookService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.bookService = bookService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        try {

            request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST) +
                    SECOND_PARAMETER + request.getParameter(BOOK_PARAMETER)));

            bookService.deleteBook(bookService.findBookById(Integer.parseInt(request.getParameter(BOOK_PARAMETER))));

            return requestFactory.createRedirectResponse(propertyContext.get(MAIN_AUTH_PAGE));

        } catch (ServiceException e) {

            logger.error("Failed deleting of book", e);
        }
        return requestFactory.createRedirectResponse(propertyContext.get(ERROR_PAGE));

    }
}
