package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.PublishingHouseDAO;
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

public enum MethodPublishingHouseDAO implements PublishingHouseDAO {
    INSTANCE(QueryOperation.getInstance());

    private static final Logger logger = LogManager.getLogger(MethodPublishingHouseDAO.class);

    private final QueryOperation queryOperation;

    private static final String FIND_PUBLISHING_HOUSE_BY_BOOK_ID = "SELECT * FROM publishing_houses INNER JOIN publishing_houses_books ON" +
            " publishing_houses.id = publishing_houses_books.publishing_house_id INNER JOIN books ON publishing_houses_books.book_id = books.id WHERE books.id = %s";

    private final Class<PublishingHouse> publishingHouseClass = PublishingHouse.class;
    private final Table tablePublishingHouse = publishingHouseClass.getAnnotation(Table.class);
    private final List<String> publishingHouseColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tablePublishingHouse.name());

    MethodPublishingHouseDAO(QueryOperation queryOperation) {
        this.queryOperation = queryOperation;
    }

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        queryOperation.update(tablePublishingHouse, updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public List<PublishingHouse> findAll() throws DAOException {

        try {

            return queryOperation.findAll(tablePublishingHouse, PublishingHouse.class);

        } catch (SQLException e) {

            logger.error("Failed finding of publishing houses", e);
            throw new DAOException(e);
        }

    }

    @Override
    public void delete(PublishingHouse publishingHouse) {

        queryOperation.delete(tablePublishingHouse, publishingHouseColumnNames.get(0), publishingHouse.getId());

    }

    @Override
    public void create(PublishingHouse entity) {

        queryOperation.create(publishingHouseColumnNames, tablePublishingHouse, entity, PublishingHouse.class);

    }

    @Override
    public Optional<PublishingHouse> findBy(String columnName, Object value) {

        return queryOperation.findBy(tablePublishingHouse, columnName, value, PublishingHouse.class);

    }

    @Override
    public Optional<PublishingHouse> findById(int id) {

        return findBy(publishingHouseColumnNames.get(0), id);

    }

    @Override
    public List<PublishingHouse> findPublishingHouseByBookId(int id) {

        final String sqlQuery = String.format(FIND_PUBLISHING_HOUSE_BY_BOOK_ID, id);

        return queryOperation.findWithSql(sqlQuery, PublishingHouse.class);

    }

}
