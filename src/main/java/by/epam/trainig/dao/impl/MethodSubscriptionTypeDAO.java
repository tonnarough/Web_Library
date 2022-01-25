package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.SubscriptionTypeDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.SubscriptionType;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum MethodSubscriptionTypeDAO implements SubscriptionTypeDAO {
    INSTANCE;

    private final Class<SubscriptionType> subscriptionTypeClass = SubscriptionType.class;
    private final Table tableSubscriptionTypes = subscriptionTypeClass.getAnnotation(Table.class);
    private final QueryOperation queryOperation = QueryOperation.getInstance();
    private final List<String> subscriptionTypesColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableSubscriptionTypes.name());

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

    }

    @Override
    public List<SubscriptionType> findAll() throws SQLException {
        return queryOperation.findAll(tableSubscriptionTypes, SubscriptionType.class);
    }

    @Override
    public void delete(String column, Object values) {

    }

    @Override
    public void create(SubscriptionType entity) {

    }

    @Override
    public Optional<SubscriptionType> findBy(String columnName, Object value) {
        return Optional.empty();
    }
}
