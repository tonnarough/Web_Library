package by.epam.trainig.dao.buildentity.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AuthorBuilder implements EntityBuilder<Author> {

    private final Class<Author> entityClass = Author.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private final List<String> columnNames = DatabaseEntityContext.getDatabaseEntityContext()
            .getDatabaseContext(tableName.name());

    @Override
    public Author buildEntity(ResultSet resultSet) throws SQLException {

        return new Author(
                resultSet.getInt(columnNames.get(0)),
                resultSet.getString(columnNames.get(1)),
                resultSet.getString(columnNames.get(2)),
                resultSet.getString(columnNames.get(3))
        );
    }

    @Override
    public void buildResultSetByEntity(PreparedStatement preparedStatement, Author entity) throws SQLException {

        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setString(3, entity.getFirstName());
        preparedStatement.setString(4, entity.getFatherName());

    }
}
