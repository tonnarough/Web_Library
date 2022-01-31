package by.epam.trainig.service.impl;

import by.epam.trainig.dao.BookDAO;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.exception.DAOException;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public enum BookServiceImpl implements BookService {
    INSTANCE(BookDAO.getInstance());

    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

    private final BookDAO bookDAO;

    BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> findAll() throws ServiceException {

        try {

            return bookDAO.findAll();

        } catch (DAOException e) {

            logger.error("Failed finding of all books", e);
            throw new ServiceException(e);
        }

    }

    @Override
    public void create(Book entity) {

    }

    @Override
    public List<Book> findByAuthor(Author author) {
        return null;
    }

    @Override
    public List<Book> findByPublishingHouse(PublishingHouse publishingHouse) {
        return null;
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        return null;
    }

    @Override
    public List<Book> findAllBook() {
        return null;
    }
}
