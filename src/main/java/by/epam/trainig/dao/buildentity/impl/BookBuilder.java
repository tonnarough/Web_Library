package by.epam.trainig.dao.buildentity.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.entity.book.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookBuilder implements EntityBuilder<Book> {

    private final Class<Book> entityClass = Book.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private final List<String> columnNames = DatabaseEntityContext.getDatabaseEntityContext()
            .getDatabaseContext(tableName.name());

    @Override
    public Book buildEntity(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getString(columnNames.get(1)),
                resultSet.getString(columnNames.get(2)),
                resultSet.getString(columnNames.get(3)),
                resultSet.getInt(columnNames.get(4)),
                resultSet.getString(columnNames.get(5)),
                resultSet.getString(columnNames.get(6))
        );
    }

    @Override
    public void buildResultSetByEntity(PreparedStatement preparedStatement, Book entity) throws SQLException {

        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setString(2, entity.getTitle());
        preparedStatement.setString(3, entity.getDescription());
        preparedStatement.setString(4, entity.getAgeLimit());
        preparedStatement.setInt(5, entity.getNumberOfPages());
        preparedStatement.setString(6, entity.getFile());
        preparedStatement.setString(6, entity.getPicture());

    }
}
