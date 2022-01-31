package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

public enum GoToSelectedBookCommand implements Command{
    INSTANCE(PropertyContext.getInstance(), RequestFactory.getInstance(), BookService.getInstance());

    private static final Logger logger = LogManager.getLogger(GoToSubscriptionPageCommand.class);

    private static final String SELECTED_BOOK = "page.selected_book";
    private static final String BOOK_ID_PARAMETR = "books";
    private static final String ERROR_PAGE = "page.error";

    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;
    private final BookService bookService;

    GoToSelectedBookCommand(PropertyContext propertyContext, RequestFactory requestFactory, BookService bookService) {
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
        this.bookService = bookService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        try {

            bookService.findBookById(Integer.parseInt(request.getParameter(BOOK_ID_PARAMETR)));
            return requestFactory.createForwardResponse(propertyContext.get(SELECTED_BOOK));

        } catch (ServiceException e) {

            logger.error("Failed finding of book", e);
            return requestFactory.createForwardResponse(propertyContext.get(ERROR_PAGE));

        }



    }
}
