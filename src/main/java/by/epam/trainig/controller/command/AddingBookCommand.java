package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.BookService;
import com.amazonaws.util.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public enum AddingBookCommand implements Command {
    INSTANCE(BookService.getInstance(), RequestFactory.getInstance(), PropertyContext.getInstance());

    private static final Logger logger = LogManager.getLogger(AddingBookCommand.class);

    private static final String ADDING_BOOK_PAGE = "page.adding_book";
    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";

    private static final String TEMP = "/temp";

    private static final String ERROR_BOOK_IS_ALREADY_EXIST_ATTRIBUTE = "bookExist";
    private static final String ERROR_BOOK_IS_ALREADY_EXIST_PARAMETER = "Book is already exist";

    private static final String BOOK_ADDED_ATTRIBUTE = "addedBook";
    private static final String BOOK_ADDED_PARAMETER = "Book added successfully";

    private static final String BOOK_TITLE = "book_title";
    private static final String BOOK_FILE = "book_file";
    private static final String BOOK_PICTURE = "picture";
    private static final String BOOK_AGE_LIMIT = "age_limit";
    private static final String BOOK_DESCRIPTION = "description";
    private static final String NUMBER_OF_PAGE = "number_of_page";
    private static final String AUTHOR_FIRST_NAME = "author_first_name";
    private static final String AUTHOR_LAST_NAME = "author_last_name";
    private static final String AUTHOR_FATHER_NAME = "author_father_name";
    private static final String GENRE_TITLE = "genre_title";
    private static final String PUBLISHING_HOUSE_TITLE = "publishing_title";
    private static final String YEAR_OF_PUBLISHING = "year_of_publishing";

    private final BookService bookService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    AddingBookCommand(BookService bookService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.bookService = bookService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST)));

        final Book book = new Book(
                request.getParameter(BOOK_TITLE),
                request.getParameter(BOOK_DESCRIPTION),
                request.getParameter(BOOK_AGE_LIMIT),
                Integer.parseInt(request.getParameter(NUMBER_OF_PAGE)),
                request.getParameter(BOOK_FILE),
                request.getParameter(BOOK_PICTURE)
        );

        for (Part part : request.getParts()) {

            if (part.getName().equals(BOOK_FILE)) {

                book.setFile(part.getSubmittedFileName());

            } else if (part.getName().equals(BOOK_PICTURE)){

                book.setPicture(part.getSubmittedFileName());

            }
        }

        book.setAuthor(new Author(
                request.getParameter(AUTHOR_FIRST_NAME),
                request.getParameter(AUTHOR_LAST_NAME),
                request.getParameter(AUTHOR_FATHER_NAME)
        ));

        book.setPublishingHouse(new PublishingHouse(
                request.getParameter(PUBLISHING_HOUSE_TITLE),
                Integer.parseInt(request.getParameter(YEAR_OF_PUBLISHING))
        ));

        book.setGenre(new Genre(
                request.getParameter(GENRE_TITLE)
        ));

        try {

            bookService.create(book);

        } catch (ServiceException e) {

            request.addAttributeToJsp(ERROR_BOOK_IS_ALREADY_EXIST_ATTRIBUTE, ERROR_BOOK_IS_ALREADY_EXIST_PARAMETER);
            return requestFactory.createRedirectResponse(propertyContext.get(ADDING_BOOK_PAGE));
        }

        for (Part part : request.getParts()) {
            if (part.getName().equals(BOOK_FILE) || part.getName().equals(BOOK_PICTURE)) {

                String fileName = part.getSubmittedFileName();
                String path = request.getServletContext().getRealPath(TEMP + File.separator + fileName);
                InputStream inputStream = part.getInputStream();
                uploadFile(inputStream, path);
                bookService.uploadBook(fileName, new File(path));
                Files.delete(Path.of(path));

            }
        }
        request.addAttributeToJsp(BOOK_ADDED_ATTRIBUTE, BOOK_ADDED_PARAMETER);
        return requestFactory.createForwardResponse(propertyContext.get(ADDING_BOOK_PAGE));
    }

    private void uploadFile(InputStream inputStream, String path) throws IOException {

        String stringFromInputStream = IOUtils.toString(inputStream);
        byte[] bytes = stringFromInputStream.getBytes();
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        fileOutputStream.write(bytes);
        fileOutputStream.close();

    }
}
