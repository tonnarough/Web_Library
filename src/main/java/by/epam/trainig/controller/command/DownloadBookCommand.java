package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

public enum DownloadBookCommand implements Command{
    INSTANCE(RequestFactory.getInstance(), PropertyContext.getInstance(), BookService.getInstance());

    private static final Logger logger = LogManager.getLogger(DownloadBookCommand.class);

    private static final String BOOKS_PARAMETER = "download";
    private static final String SELECTED_BOOK = "page.selected_book";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private final BookService bookService;

    DownloadBookCommand(RequestFactory requestFactory, PropertyContext propertyContext, BookService bookService) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
        this.bookService = bookService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        final String file = request.getParameter(BOOKS_PARAMETER);

        return requestFactory.createRedirectResponseWithInputStream(propertyContext.get(SELECTED_BOOK), bookService.downloadBook(file));

    }
}
