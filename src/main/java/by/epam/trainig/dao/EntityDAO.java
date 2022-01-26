package by.epam.trainig.dao;

import by.epam.trainig.entity.Entity;
import by.epam.trainig.entity.user.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EntityDAO<T extends Entity> {

    void update(String updColumn, Object updValue, String whereColumn, Object whereValue);

    List<T> findAll() throws SQLException;

    void delete(String column, Object values);

    void create(T entity);

    Optional<T> findBy(String columnName, Object value);

}
