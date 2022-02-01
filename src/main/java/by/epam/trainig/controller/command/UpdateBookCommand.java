package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

public enum UpdateBookCommand implements Command {
    INSTANCE( BookService.getInstance(), RequestFactory.getInstance(), PropertyContext.getInstance());

    private static final Logger logger = LogManager.getLogger(UpdateBookCommand.class);

    private static final String BOOK_PARAMETER = "books";
    private static final String MAIN_AUTH_PAGE = "go_to_main_auth_page";
    private static final String ERROR_PAGE = "page.error";

    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";
    private static final String SECOND_PARAMETER = "&books=";

    private final BookService bookService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    UpdateBookCommand(BookService bookService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.bookService = bookService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        return null;

    }
}
