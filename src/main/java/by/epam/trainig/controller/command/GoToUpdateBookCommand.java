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

import java.util.List;
import java.util.Optional;

public enum GoToUpdateBookCommand implements Command {
    INSTANCE(BookService.getInstance(), PropertyContext.getInstance(), RequestFactory.getInstance());

    private static final Logger logger = LogManager.getLogger(GoToUpdateBookCommand.class);

    private static final String ERROR_PAGE = "page.error";

    private static final String UPDATE_BOOK_PAGE = "page.update_book";
    private static final String URL = "url";
    private static final String ID = "id";
    private static final String PARAMETER_FROM_REQUEST = "command";
    private static final String SECOND_PARAMETER = "&books=";
    private static final String BOOK_PARAMETER = "books";

    private static final String BOOK_TO_JSP = "book";
    private static final String AUTHORS_TO_JSP = "authors";
    private static final String GENRES_TO_JSP = "genres";
    private static final String PUBLISHING_HOUSES_TO_JSP = "publishingHouse";

    private final BookService bookService;
    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;

    GoToUpdateBookCommand(BookService bookService, PropertyContext propertyContext, RequestFactory requestFactory) {
        this.bookService = bookService;
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {

        request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST) +
                SECOND_PARAMETER + request.getParameter(BOOK_PARAMETER)));

        String s = urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST) +
                SECOND_PARAMETER + request.getParameter(BOOK_PARAMETER));

        String sr = request.getParameter(BOOK_PARAMETER);

        Optional<Book> book = bookService.findBy(ID, Integer.parseInt(request.getParameter(BOOK_PARAMETER)));

        if (book.isEmpty()){

            return requestFactory.createForwardResponse(propertyContext.get(ERROR_PAGE));

        }

        List<Author> authors = bookService.findAuthorsByBookId(book.get().getId());

        List<Genre> genres = bookService.findGenresByBookId(book.get().getId());

        List<PublishingHouse> publishingHouses = bookService.findPublishingHousesByBookId(book.get().getId());

        request.addAttributeToJsp(BOOK_TO_JSP, book);
        request.addAttributeToJsp(AUTHORS_TO_JSP, authors);
        request.addAttributeToJsp(GENRES_TO_JSP, genres);
        request.addAttributeToJsp(PUBLISHING_HOUSES_TO_JSP, publishingHouses);

        return requestFactory.createForwardResponse(propertyContext.get(UPDATE_BOOK_PAGE));

    }
}
