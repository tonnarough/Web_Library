package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.PublishingHouseDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
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
    public void delete(String column, Object values) {

        queryOperation.delete(tablePublishingHouse, column, values);

    }

    @Override
    public void create(PublishingHouse entity) {

        queryOperation.create(publishingHouseColumnNames, tablePublishingHouse, entity, PublishingHouse.class);

    }

    @Override
    public Optional<PublishingHouse> findBy(String columnName, Object value) {

        return queryOperation.findBy(tablePublishingHouse, columnName, value, PublishingHouse.class);

    }
}
