package by.epam.trainig.dao;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.entity.Entity;

import java.sql.SQLException;
import java.util.List;

public interface EntityDAO <T extends Entity>{

    void update(String column1, Object value1, String column2, Object value2);

    List<T> findAll() throws SQLException;

    void delete(String column, Object values);

    void create(T entity);

}
