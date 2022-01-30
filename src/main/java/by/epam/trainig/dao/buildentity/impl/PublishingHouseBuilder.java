package by.epam.trainig.dao.buildentity.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PublishingHouseBuilder implements EntityBuilder<PublishingHouse> {

    private final Class<PublishingHouse> entityClass = PublishingHouse.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private final List<String> columnNames = DatabaseEntityContext.getDatabaseEntityContext()
            .getDatabaseContext(tableName.name());

    @Override
    public PublishingHouse buildEntity(ResultSet resultSet) throws SQLException {

        return new PublishingHouse(
                resultSet.getInt(columnNames.get(0)),
                resultSet.getString(columnNames.get(1)),
                resultSet.getInt(columnNames.get(2))
        );
    }

    @Override
    public void buildResultSetByEntity(PreparedStatement preparedStatement, PublishingHouse entity) throws SQLException {

        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setString(2, entity.getTitle());
        preparedStatement.setInt(3, entity.getYearOfPublishing());

    }
}
