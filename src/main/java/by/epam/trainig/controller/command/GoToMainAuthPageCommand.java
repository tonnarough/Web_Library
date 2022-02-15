package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public enum GoToMainAuthPageCommand implements Command {
    INSTANCE(PropertyContext.getInstance(), RequestFactory.getInstance(), BookService.getInstance());

    private static final Logger logger = LogManager.getLogger(GoToMainAuthPageCommand.class);

    private static final String MAIN_AUTH_PAGE = "page.main_auth";

    private static final String NUMBER_OF_PAGE = "numberOfPage";
    private static final String CURRENT_PAGE = "currentPage";

    private static final String BOOKS_TO_JSP_PARAMETER = "books";
    private static final String URL = "url";
    private static final String PARAMETER_FROM_REQUEST = "command";
    private static final String SECOND_PARAMETER = "&currentPage=";

    private final PropertyContext propertyContext;
    private final RequestFactory requestFactory;
    private final BookService bookService;

    GoToMainAuthPageCommand(PropertyContext propertyContext, RequestFactory requestFactory, BookService bookService) {
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
        this.bookService = bookService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {

        final int recordOnPage = 6;
        int currentPage;

        if (request.getParameter(CURRENT_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(CURRENT_PAGE));
        } else {
            currentPage = 1;
        }

        final List<Book> books = bookService.findAll((currentPage - 1) * recordOnPage, recordOnPage);

        request.addAttributeToJsp(BOOKS_TO_JSP_PARAMETER, books);

        int numberOfRowsInDB = bookService.getNumberOfRows();

        int numberOfPages = (int) Math.ceil(numberOfRowsInDB * 1.0 / recordOnPage);

        request.addToSession(URL, urlBuilder(request.getRequestURL(), request.getParameter(PARAMETER_FROM_REQUEST) +
                SECOND_PARAMETER + currentPage));
        request.addAttributeToJsp(NUMBER_OF_PAGE, numberOfPages);
        request.addAttributeToJsp(CURRENT_PAGE, currentPage);



        return requestFactory.createForwardResponse(propertyContext.get(MAIN_AUTH_PAGE));

    }
}
