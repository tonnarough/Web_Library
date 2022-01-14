package by.epam.trainig.dao.buildentity.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.entity.user.Role;
import by.epam.trainig.entity.user.Subscriptions;
import by.epam.trainig.entity.user.UserDetail;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubscriptionsBuilder implements EntityBuilder<Subscriptions> {

    private final Class<Subscriptions> entityClass = Subscriptions.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private List<String> subscriptionsColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getDatabaseContext(tableName.name());

    @Override
    public Subscriptions buildEntity(ResultSet resultSet) throws SQLException {
        Subscriptions subscriptions = new Subscriptions();
        subscriptions.setId(resultSet.getInt(subscriptionsColumns.get(0)));
        subscriptions.setUserId(resultSet.getInt(subscriptionsColumns.get(1)));
        subscriptions.setSubscriptionTypeId(resultSet.getInt(subscriptionsColumns.get(2)));
        subscriptions.setExpired(resultSet.getBoolean(subscriptionsColumns.get(3)));
        subscriptions.setStartDate(resultSet.getDate(subscriptionsColumns.get(4)));
        subscriptions.setEndDate(resultSet.getDate(subscriptionsColumns.get(5)));
        return subscriptions;
    }

    @Override
    public void buildResultSetByEntity(PreparedStatement preparedStatement, Subscriptions entity) throws SQLException {
        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setInt(2, entity.getUserId());
        preparedStatement.setInt(3, entity.getSubscriptionTypeId());
        preparedStatement.setBoolean(4, entity.isExpired());
        preparedStatement.setDate(7, (Date) entity.getStartDate());
        preparedStatement.setDate(5, (Date) entity.getStartDate());
    }
}
