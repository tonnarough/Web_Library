package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.SubscriptionDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum MethodSubscriptionDAO implements SubscriptionDAO {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger(MethodSubscriptionDAO.class);

    private final QueryOperation queryOperation = QueryOperation.getInstance();

    private final Class<Subscription> SubscriptionClass = Subscription.class;
    private final Table tableSubscription = SubscriptionClass.getAnnotation(Table.class);
    private final List<String> subscriptionColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableSubscription.name());

    private final Class<SubscriptionType> subscriptionTypeClass = SubscriptionType.class;
    private final Table tableSubscriptionTypes = subscriptionTypeClass.getAnnotation(Table.class);
    private final List<String> subscriptionTypesColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableSubscriptionTypes.name());


    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        queryOperation.update(tableSubscription, updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public List<Subscription> findAll() {

        return null;

    }

    @Override
    public void delete(String column, Object values) {

    }

    @Override
    public void create(Subscription entity) {

        queryOperation.create(subscriptionColumnNames, tableSubscription, entity, Subscription.class);

    }

    @Override
    public Optional<Subscription> findBy(String columnName, Object value) {

        return queryOperation.findBy(tableSubscription, subscriptionColumnNames.get(subscriptionColumnNames.indexOf(String.format("%s", columnName))),
                value, Subscription.class);

    }

    @Override
    public List<SubscriptionType> findAllTypes() throws DAOException {

        try {

            return queryOperation.findAll(tableSubscriptionTypes, SubscriptionType.class);

        } catch (SQLException e) {

            logger.error("Failed finding of all types of subscription", e);
            throw new DAOException(e);

        }
    }
}
