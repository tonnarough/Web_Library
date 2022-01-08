package by.epam.trainig.dao.buildentity;

import by.epam.trainig.entity.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityBuilder<T extends Entity> {

    T buildEntity(ResultSet resultSet) throws SQLException;

    void buildResultSetByEntity(PreparedStatement preparedStatement, T entity) throws SQLException;


}
