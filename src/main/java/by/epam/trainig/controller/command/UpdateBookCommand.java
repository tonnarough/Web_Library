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

public enum UpdateBookCommand implements Command {
    INSTANCE(BookService.getInstance(), RequestFactory.getInstance(), PropertyContext.getInstance());

    private static final Logger logger = LogManager.getLogger(UpdateBookCommand.class);

    private static final String UPDATE_BOOK = "go_to_update_book_page&books=%s";

    private static final String ERROR_PAGE = "page.error";
    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";

    private static final String BOOK_PARAMETER = "books";
    private static final String BOOK_TO_JSP = "book";

    private static final String ID = "id";

    private static final String UPDATE_ERROR_MESSAGE = "You haven't changed a thing";
    private static final String UPDATING_ATTRIBUTE = "updating";
    private static final String SUCCESSFUL_UPDATE_MESSAGE = "Completed!";

    private static final String BOOK_TITLE = "title";
    private static final String BOOK_AGE_LIMIT = "age_limit";
    private static final String BOOK_DESCRIPTION = "description";
    private static final String AUTHOR_FIRST_NAME = "first_name";
    private static final String AUTHOR_LAST_NAME = "last_name";
    private static final String AUTHOR_FATHER_NAME = "father_name";
    private static final String GENRE_TITLE = "title";
    private static final String PUBLISHING_HOUSE_TITLE = "title";

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

        try {
            request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST)));

            final Optional<Book> book = bookService.findBookWithAuthorGenrePublishingHouseById(Integer.parseInt(request.getParameter(BOOK_TO_JSP)));

            final boolean isChanged = bookUpdates(request, book) | authorUpdates(request, book.get().getAuthors()) |
                    genreUpdates(request, book.get().getGenres()) | publishingHouseUpdate(request, book.get().getPublishingHouses());

            if (isChanged){

                request.addAttributeToJsp(UPDATING_ATTRIBUTE, SUCCESSFUL_UPDATE_MESSAGE);

            }else{

                request.addAttributeToJsp(UPDATING_ATTRIBUTE, UPDATE_ERROR_MESSAGE);

            }

            request.addAttributeToJsp(BOOK_PARAMETER, book.get().getId());

            return requestFactory.createRedirectResponse(propertyContext.get(String.format(UPDATE_BOOK, book.get().getId())));

        } catch (ServiceException e) {

            logger.error(e);
            return requestFactory.createForwardResponse(propertyContext.get(ERROR_PAGE));

        }

    }

    private boolean bookUpdates(CommandRequest request, Optional<Book> book) {

        final boolean isBookTitleChanged = request.getParameter(BOOK_TITLE).equals(book.get().getTitle());
        final boolean isBookAgeLimitChanged = request.getParameter(BOOK_AGE_LIMIT).equals(book.get().getAgeLimit());
        final boolean isBookDescriptionChanged = request.getParameter(BOOK_DESCRIPTION).equals(book.get().getDescription());

        boolean result = false;

        if (!isBookTitleChanged) {

            bookService.updateBook(BOOK_TITLE, request.getParameter(BOOK_TITLE), ID, book.get().getId());
            result = true;

        }
        if (!isBookAgeLimitChanged) {

            bookService.updateBook(BOOK_AGE_LIMIT, request.getParameter(BOOK_AGE_LIMIT), ID, book.get().getId());
            result = true;

        }
        if (!isBookDescriptionChanged) {

            bookService.updateBook(BOOK_DESCRIPTION, request.getParameter(BOOK_DESCRIPTION), ID, book.get().getId());
            result = true;
        }

        return result;
    }

    private boolean authorUpdates(CommandRequest request, List<Author> authors) {

        boolean result = false;

        for (Author author : authors) {

            if (!(request.getParameter(author.getId() + AUTHOR_FIRST_NAME).equals(author.getFirstName()))) {

                bookService.updateAuthor(AUTHOR_FIRST_NAME, request.getParameter(author.getId() + AUTHOR_FIRST_NAME), ID, author.getId());
                result = true;

            }
            if (!(request.getParameter(author.getId() + AUTHOR_LAST_NAME).equals(author.getLastName()))) {

                bookService.updateAuthor(AUTHOR_LAST_NAME, request.getParameter(author.getId() + AUTHOR_LAST_NAME), ID, author.getId());
                result = true;

            }
            if (!(request.getParameter(author.getId() + AUTHOR_FATHER_NAME).equals(author.getLastName()))) {

                bookService.updateAuthor(AUTHOR_FATHER_NAME, request.getParameter(author.getId() + AUTHOR_FATHER_NAME), ID, author.getId());
                result = true;

            }
        }
        return result;
    }

    private boolean genreUpdates(CommandRequest request, List<Genre> genres) {

        boolean result = false;

        for (Genre genre : genres) {

            if (!(request.getParameter(genre.getId() + GENRE_TITLE).equals(genre.getTitle()))) {

                bookService.updateGenre(GENRE_TITLE, request.getParameter(genre.getId() + GENRE_TITLE), ID, genre.getId());
                result = true;

            }
        }
        return result;
    }

    private boolean publishingHouseUpdate(CommandRequest request, List<PublishingHouse> publishingHouses){

        boolean result = false;

        for (PublishingHouse publishingHouse : publishingHouses) {

            if (!(request.getParameter(publishingHouse.getId() + PUBLISHING_HOUSE_TITLE).equals(publishingHouse.getTitle()))) {

                bookService.updatePublishingHouse(PUBLISHING_HOUSE_TITLE, request.getParameter(publishingHouse.getId() + PUBLISHING_HOUSE_TITLE), ID, publishingHouse.getId());
                result = true;

            }
        }
        return result;
    }

}
