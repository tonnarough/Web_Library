package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public enum GoToSelectedBookCommand implements Command{
    INSTANCE(PropertyContext.getInstance(), RequestFactory.getInstance(), BookService.getInstance());

    private static final Logger logger = LogManager.getLogger(GoToSubscriptionPageCommand.class);

    private static final String SELECTED_BOOK = "page.selected_book";
    private static final String AUTHORS_PARAMETER = "authors";
    private static final String GENRES_PARAMETER = "genres";
    private static final String PUBLISHING_HOUSES_PARAMETER = "publishingHouses";
    private static final String BOOKS_PARAMETER = "books";
    private static final String ERROR_PAGE = "page.error";
    private static final String URL = "url";
    private static final String ID = "id";
    private static final String PARAMETER_FROM_REQUEST = "command";
    private static final String SECOND_PARAMETER = "&books=";

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

        request.addToSession(URL, urlBuilder(request.getRequestURL(),
                request.getParameter(PARAMETER_FROM_REQUEST) + SECOND_PARAMETER + request.getParameter(BOOKS_PARAMETER)));

        final Optional<Book> book = bookService.findBy(ID, Integer.parseInt(request.getParameter(BOOKS_PARAMETER)));

        if(book.isEmpty()){

            logger.error("Failed finding of book");
            return requestFactory.createForwardResponse(propertyContext.get(ERROR_PAGE));

        }

        request.addAttributeToJsp(BOOKS_PARAMETER, book.get());
        request.addAttributeToJsp(AUTHORS_PARAMETER, book.get().getAuthors());
        request.addAttributeToJsp(GENRES_PARAMETER, book.get().getGenres());
        request.addAttributeToJsp(PUBLISHING_HOUSES_PARAMETER, book.get().getPublishingHouses());

        return requestFactory.createForwardResponse(propertyContext.get(SELECTED_BOOK));

    }
}
