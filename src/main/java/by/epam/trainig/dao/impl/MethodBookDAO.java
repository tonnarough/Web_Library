package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.AuthorDAO;
import by.epam.trainig.dao.BookDAO;
import by.epam.trainig.dao.GenreDAO;
import by.epam.trainig.dao.PublishingHouseDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum MethodBookDAO implements BookDAO {
    INSTANCE(QueryOperation.getInstance());

    private static final Logger logger = LogManager.getLogger(MethodBookDAO.class);

    private final QueryOperation queryOperation;

    private static final String PAGINATION_QUERY = "SELECT * FROM books LIMIT %s, %s";

    private final Class<Book> bookClass = Book.class;
    private final Table tableBook = bookClass.getAnnotation(Table.class);
    private final List<String> bookColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableBook.name());

    MethodBookDAO(QueryOperation queryOperation) {
        this.queryOperation = queryOperation;
    }

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        queryOperation.update(tableBook, updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public List<Book> findAll() throws DAOException {

        try {

            return queryOperation.findAll(tableBook, Book.class);

        } catch (SQLException e) {

            logger.error("Failed finding of books", e);
            throw new DAOException(e);
        }

    }

    @Override
    public void delete(Book book) {

        queryOperation.delete(tableBook, bookColumnNames.get(0), book.getId());

    }

    @Override
    public void create(Book entity) {

        queryOperation.create(bookColumnNames, tableBook, entity, Book.class);

    }

    @Override
    public Optional<Book> findBy(String columnName, Object value) {

        return queryOperation.findBy(tableBook, columnName, value, Book.class);

    }

    @Override
    public Optional<Book> findById(int id) {

        return findBy(bookColumnNames.get(0), id);

    }

    @Override
    public List<Book> findByParameter(String column, String parameter) {

        return queryOperation.findByParameter(tableBook,column, parameter, Book.class);

    }

    @Override
    public List<Book> findAllBooks(int currentPage, int recordsOnPage) {

        return queryOperation.findWithSql(String.format(PAGINATION_QUERY, currentPage, recordsOnPage), Book.class);

    }

    @Override
    public int getNumberOfRows() {

        return queryOperation.getCountOfRows();

    }

}
