package by.epam.trainig.dao.buildentity.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.entity.user.Subscription;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubscriptionBuilder implements EntityBuilder<Subscription> {

    private final Class<Subscription> entityClass = Subscription.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private final List<String> subscriptionsColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getDatabaseContext(tableName.name());

    @Override
    public Subscription buildEntity(ResultSet resultSet) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setId(resultSet.getInt(subscriptionsColumns.get(0)));
        subscription.setUserId(resultSet.getInt(subscriptionsColumns.get(1)));
        subscription.setSubscriptionTypeId(resultSet.getInt(subscriptionsColumns.get(2)));
        subscription.setStartDate(resultSet.getDate(subscriptionsColumns.get(3)));
        subscription.setEndDate(resultSet.getDate(subscriptionsColumns.get(4)));
        return subscription;
    }

    @Override
    public void buildResultSetByEntity(PreparedStatement preparedStatement, Subscription entity) throws SQLException {
        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setInt(2, entity.getUserId());
        preparedStatement.setInt(3, entity.getSubscriptionTypeId());
        preparedStatement.setDate(4, (Date) entity.getStartDate());
        preparedStatement.setDate(5, (Date) entity.getEndDate());
    }
}
