package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.service.BookService;
import com.amazonaws.util.IOUtils;
import liquibase.ObjectMetaData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public enum AddingBookCommand implements Command {
    INSTANCE(BookService.getInstance(), RequestFactory.getInstance(), PropertyContext.getInstance());

    private static final Logger logger = LogManager.getLogger(AddingBookCommand.class);

    private static final String ADDING_BOOK_PAGE = "page.adding_book";
    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";
    private final ClassLoader classLoader = AddingBookCommand.class.getClassLoader();

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

    AddingBookCommand(BookService bookService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.bookService = bookService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST)));

        List<Part> list = new ArrayList<>(request.getParts());

        for (Part part : request.getParts()) {
            if (part.getName().equals("book_file")) {

                String fileName = part.getSubmittedFileName();
                String path = request.getServletContext().getRealPath("/temp" + File.separator + fileName);
                InputStream inputStream = part.getInputStream();
                uploadFile(inputStream, path);
                bookService.uploadBook(fileName, new File(path));
                Files.delete(Path.of(path));
            }
        }
        return requestFactory.createForwardResponse(propertyContext.get(ADDING_BOOK_PAGE));
    }

    private void uploadFile(InputStream inputStream, String path) throws IOException {

        String stringFromInputStream = IOUtils.toString(inputStream);
        byte[] bytes = stringFromInputStream.getBytes();
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        fileOutputStream.write(bytes);

    }

}
