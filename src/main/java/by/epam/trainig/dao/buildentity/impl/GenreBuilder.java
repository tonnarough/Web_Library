package by.epam.trainig.dao.buildentity.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GenreBuilder implements EntityBuilder<Genre> {

    private final Class<Genre> entityClass = Genre.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private final List<String> columnNames = DatabaseEntityContext.getDatabaseEntityContext()
            .getDatabaseContext(tableName.name());

    @Override
    public Genre buildEntity(ResultSet resultSet) throws SQLException {
        return new Genre(
                resultSet.getInt(columnNames.get(0)),
                resultSet.getString(columnNames.get(1))
        );
    }

    @Override
    public void buildResultSetByEntity(PreparedStatement preparedStatement, Genre entity) throws SQLException {

        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setString(2, entity.getTitle());

    }
}
