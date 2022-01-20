package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.Subscription;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public final class MethodSubscriptionDAO implements EntityDAO<Subscription> {

    private final Class<Subscription> SubscriptionClass = Subscription.class;
    private final Table tableSubscription = SubscriptionClass.getAnnotation(Table.class);
    private final QueryOperation queryOperation = QueryOperation.getInstance();
    private final List<String> subscriptionColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableSubscription.name());

    @Override
    public void update(String column1, Object value1, String column2, Object value2) {

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
