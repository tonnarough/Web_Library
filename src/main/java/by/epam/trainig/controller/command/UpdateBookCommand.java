package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.dao.AuthorDAO;
import by.epam.trainig.dao.PublishingHouseDAO;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.exception.ControllerException;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.AuthorService;
import by.epam.trainig.service.BookService;
import by.epam.trainig.service.GenreService;
import by.epam.trainig.service.PublishingHouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public enum UpdateBookCommand implements Command {
    INSTANCE(BookService.getInstance(), AuthorService.getInstance(), GenreService.getInstance(), PublishingHouseService.getInstance(), RequestFactory.getInstance(), PropertyContext.getInstance());

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
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PublishingHouseService publishingHouseService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    UpdateBookCommand(BookService bookService, AuthorService authorService, GenreService genreService, PublishingHouseService publishingHouseService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.publishingHouseService = publishingHouseService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST)));

        final Optional<Book> book = bookService.findBy(ID, Integer.parseInt(request.getParameter(BOOK_TO_JSP)));

        if (book.isEmpty()) {

            logger.error("Failed finding of book");
            return requestFactory.createForwardResponse(propertyContext.get(ERROR_PAGE));

        }

        try {

            bookUpdates(request, book.get());
            authorUpdates(request, book.get().getAuthors());
            genreUpdates(request, book.get().getGenres());
            publishingHouseUpdate(request, book.get().getPublishingHouses());

        } catch (ControllerException e) {

            request.addAttributeToJsp(UPDATING_ATTRIBUTE, UPDATE_ERROR_MESSAGE);
            return requestFactory.createRedirectResponse(propertyContext.get(ERROR_PAGE));
        }


        request.addAttributeToJsp(UPDATING_ATTRIBUTE, SUCCESSFUL_UPDATE_MESSAGE);

        request.addAttributeToJsp(BOOK_PARAMETER, book.get().getId());

        return requestFactory.createRedirectResponse(propertyContext.get(String.format(UPDATE_BOOK, book.get().getId())));

    }

    private void bookUpdates(CommandRequest request, Book book) throws ControllerException {

        final boolean isBookTitleChanged = !request.getParameter(BOOK_TITLE).equals(book.getTitle());

        final boolean isBookAgeLimitChanged = !request.getParameter(BOOK_AGE_LIMIT).equals(book.getAgeLimit());

        final boolean isBookDescriptionChanged = !request.getParameter(BOOK_DESCRIPTION).equals(book.getDescription());

        try {

            bookService.updateIfChanged(isBookTitleChanged, BOOK_TITLE, request.getParameter(BOOK_TITLE), ID, book.getId());

            bookService.updateIfChanged(isBookAgeLimitChanged, BOOK_AGE_LIMIT, request.getParameter(BOOK_AGE_LIMIT), ID, book.getId());

            bookService.updateIfChanged(isBookDescriptionChanged, BOOK_DESCRIPTION, request.getParameter(BOOK_DESCRIPTION), ID, book.getId());

        } catch (ServiceException e) {

            logger.error("Failed of book updating");
            throw new ControllerException(e);
        }
    }

    private void authorUpdates(CommandRequest request, List<Author> authors) throws ControllerException {

        for (Author author : authors) {

            final boolean isAuthorFirstNameChanged = !request.getParameter(author.getId() + AUTHOR_FIRST_NAME).equals(author.getFirstName());

            final boolean isAuthorLastNameChanged = !request.getParameter(author.getId() + AUTHOR_LAST_NAME).equals(author.getLastName());

            final boolean isAuthorFatherNameChanged = !request.getParameter(author.getId() + AUTHOR_FATHER_NAME).equals(author.getFatherName());

            try {

                authorService.updateIfChanged(isAuthorFirstNameChanged, AUTHOR_FIRST_NAME, request.getParameter(author.getId() + AUTHOR_FIRST_NAME), ID, author.getId());

                authorService.updateIfChanged(isAuthorLastNameChanged, AUTHOR_LAST_NAME, request.getParameter(author.getId() + AUTHOR_LAST_NAME), ID, author.getId());

                authorService.updateIfChanged(isAuthorFatherNameChanged, AUTHOR_FATHER_NAME, request.getParameter(author.getId() + AUTHOR_FATHER_NAME), ID, author.getId());

            } catch (ServiceException e) {

                logger.error("Failed of author updating");
                throw new ControllerException(e);
            }
        }
    }

    private void genreUpdates(CommandRequest request, List<Genre> genres) throws ControllerException {

        for (Genre genre : genres) {

            final boolean isGenreTitleChanged = !request.getParameter(genre.getId() + GENRE_TITLE).equals(genre.getTitle());

            try {

                genreService.updateIfChanged(isGenreTitleChanged, GENRE_TITLE, request.getParameter(genre.getId() + GENRE_TITLE), ID, genre.getId());

            } catch (ServiceException e) {

                logger.error("Failed of author updating");
                throw new ControllerException(e);
            }
        }
    }

    private void publishingHouseUpdate(CommandRequest request, List<PublishingHouse> publishingHouses) throws ControllerException {

        for (PublishingHouse publishingHouse : publishingHouses) {

            final boolean isPublishingHouseChanged = !(request.getParameter(publishingHouse.getId() + PUBLISHING_HOUSE_TITLE).equals(publishingHouse.getTitle()));

            try {

                publishingHouseService.updateIfChanged(isPublishingHouseChanged, PUBLISHING_HOUSE_TITLE, request.getParameter(publishingHouse.getId() + PUBLISHING_HOUSE_TITLE), ID, publishingHouse.getId());

            } catch (ServiceException e) {

                logger.error("Failed of author updating");
                throw new ControllerException(e);
            }
        }
    }
}
