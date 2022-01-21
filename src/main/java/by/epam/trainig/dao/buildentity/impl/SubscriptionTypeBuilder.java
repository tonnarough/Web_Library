package by.epam.trainig.dao.buildentity.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.SubscriptionType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubscriptionTypeBuilder implements EntityBuilder<SubscriptionType> {

    private final Class<SubscriptionType> entityClass = SubscriptionType.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private final List<String> subscriptionTypeColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getDatabaseContext(tableName.name());

    @Override
    public SubscriptionType buildEntity(ResultSet resultSet) throws SQLException {
        SubscriptionType subscriptionType = new SubscriptionType();
        subscriptionType.setId(resultSet.getInt(subscriptionTypeColumns.get(0)));
        subscriptionType.setDescription(resultSet.getString(subscriptionTypeColumns.get(1)));
        subscriptionType.setPrice(resultSet.getBigDecimal(subscriptionTypeColumns.get(2)));
        return subscriptionType;
    }

    @Override
    public void buildResultSetByEntity(PreparedStatement preparedStatement, SubscriptionType entity) throws SQLException {
        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setString(2, entity.getDescription());
        preparedStatement.setBigDecimal(3, entity.getPrice());
    }
}
