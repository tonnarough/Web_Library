package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.SubscriptionDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.Subscription;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum MethodSubscriptionDAO implements SubscriptionDAO {
    INSTANCE;

    private final Class<Subscription> SubscriptionClass = Subscription.class;
    private final Table tableSubscription = SubscriptionClass.getAnnotation(Table.class);
    private final QueryOperation queryOperation = QueryOperation.getInstance();
    private final List<String> subscriptionColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableSubscription.name());

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {
        queryOperation.update(tableSubscription, updColumn, updValue, whereColumn, whereValue);
    }

    @Override
    public List<Subscription> findAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(String column, Object values) {

    }

    @Override
    public void create(Subscription entity) {

    }

    @Override
    public Optional<Subscription> findBy(String columnName, Object value) {
        return queryOperation.findBy(tableSubscription, subscriptionColumnNames.get(subscriptionColumnNames.indexOf(String.format("%s", columnName))),
                value, Subscription.class);
    }
}
