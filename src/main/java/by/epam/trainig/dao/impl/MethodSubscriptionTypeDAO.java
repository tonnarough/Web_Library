package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.SubscriptionTypeDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum MethodSubscriptionTypeDAO implements SubscriptionTypeDAO {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger(MethodSubscriptionTypeDAO.class);

    private final QueryOperation queryOperation = QueryOperation.getInstance();

    private final Class<SubscriptionType> subscriptionTypeClass = SubscriptionType.class;
    private final Table tableSubscriptionTypes = subscriptionTypeClass.getAnnotation(Table.class);
    private final List<String> subscriptionTypesColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableSubscriptionTypes.name());

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        queryOperation.update(tableSubscriptionTypes, updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public List<SubscriptionType> findAll() throws DAOException {

        try {

            return queryOperation.findAll(tableSubscriptionTypes, SubscriptionType.class);

        } catch (SQLException e) {

            logger.error("Failed finding of all types of subscription", e);
            throw new DAOException(e);

        }

    }

    @Override
    public void delete(String column, Object values) {

        queryOperation.delete(tableSubscriptionTypes, column, values);

    }

    @Override
    public void create(SubscriptionType entity) {

        queryOperation.create(subscriptionTypesColumnNames, tableSubscriptionTypes, entity, SubscriptionType.class);

    }

    @Override
    public Optional<SubscriptionType> findBy(String columnName, Object value) {

        return queryOperation.findBy(tableSubscriptionTypes, columnName, value, SubscriptionType.class);

    }

    @Override
    public Optional<SubscriptionType> findById(int id) {

        return findBy(subscriptionTypesColumnNames.get(0), id);

    }
}
