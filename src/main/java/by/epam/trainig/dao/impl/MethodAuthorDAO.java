package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.AuthorDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.dao.queryoperation.QueryOperator;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum MethodAuthorDAO implements AuthorDAO {
    INSTANCE(QueryOperation.getInstance());

    private static final Logger logger = LogManager.getLogger(MethodAuthorDAO.class);

    private final QueryOperation queryOperation;

    private static final String FIND_AUTHOR_BY_BOOK_ID = "SELECT * FROM authors INNER JOIN authors_books ON" +
            " authors.id = authors_books.author_id INNER JOIN books ON authors_books.book_id = books.id WHERE books.id = %s";

    private final Class<Author> authorClass = Author.class;
    private final Table tableAuthor = authorClass.getAnnotation(Table.class);
    private final List<String> authorColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableAuthor.name());

    MethodAuthorDAO(QueryOperation queryOperation) {
        this.queryOperation = queryOperation;
    }

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        queryOperation.update(tableAuthor, updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public List<Author> findAll() throws DAOException {

        try {

            return queryOperation.findAll(tableAuthor, Author.class);

        } catch (SQLException e) {

            logger.error("Failed finding of authors", e);
            throw new DAOException(e);
        }

    }

    @Override
    public void delete(String column, Object values) {

        queryOperation.delete(tableAuthor, column, values);

    }

    @Override
    public void create(Author entity) {

        queryOperation.create(authorColumnNames, tableAuthor, entity, Author.class);

    }

    @Override
    public Optional<Author> findBy(String columnName, Object value) {

        return queryOperation.findBy(tableAuthor, columnName, value, Author.class);

    }

    @Override
    public Optional<Author> findById(int id) {

        return findBy(authorColumnNames.get(0), id);

    }

    @Override
    public List<Author> findAuthorsByBookId(int id) {

        final String sqlQuery = String.format(FIND_AUTHOR_BY_BOOK_ID, id);

        return queryOperation.findWithSql(sqlQuery, Author.class);

    }
}
