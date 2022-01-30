package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.BookDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.book.Book;
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
    public void delete(String column, Object values) {

        queryOperation.delete(tableBook, column, values);

    }

    @Override
    public void create(Book entity) {

        queryOperation.create(bookColumnNames, tableBook, entity, Book.class);

    }

    @Override
    public Optional<Book> findBy(String columnName, Object value) {

        return queryOperation.findBy(tableBook, columnName, value, Book.class);

    }
}
