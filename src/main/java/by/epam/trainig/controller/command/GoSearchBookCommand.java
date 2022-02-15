package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public enum GoSearchBookCommand implements Command{
    INSTANCE(PropertyContext.getInstance(), RequestFactory.getInstance(), BookService.getInstance());

    private static final Logger logger = LogManager.getLogger(GoSearchBookCommand.class);

    private static final String SEARCH_BOOK = "page.search_book";
    private static final String TITLE = "title";
    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";
    private static final String SEARCH_PARAMETER = "search";
    private static final String BOOK_PARAMETER = "books";
    private static final String ERROR_MESSAGE = "Nothing found";
    private static final String SECOND_PARAMETER = "&search=";

    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;
    private final BookService bookService;

    GoSearchBookCommand(PropertyContext propertyContext, RequestFactory requestFactory, BookService bookService) {
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
        this.bookService = bookService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST) +
                SECOND_PARAMETER + request.getParameter(SEARCH_PARAMETER)));

        final List<Book> books = bookService.findAllWhere(TITLE, request.getParameter(SEARCH_PARAMETER));

        if(books.isEmpty()){

            request.addAttributeToJsp(BOOK_PARAMETER, ERROR_MESSAGE);

        } else {

            request.addAttributeToJsp(BOOK_PARAMETER, books);

        }

        return requestFactory.createForwardResponse(propertyContext.get(SEARCH_BOOK));
    }
}