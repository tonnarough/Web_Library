package by.epam.trainig.dao;

import by.epam.trainig.entity.Entity;
import by.epam.trainig.exception.DAOException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface EntityDAO<T extends Entity> {

    List<T> findAllWhere(String column, Object value);

    int getCountOfRows();

    void update(String updColumn, Object updValue, String whereColumn, Object whereValue) throws DAOException;

    List<T> findAll();

    void delete(Integer id) throws DAOException;

    void create (T entity) throws DAOException;

    void create(T entity, Connection connection) throws DAOException;

    Optional<T> findBy(String[] columnNames, Object[] values);

    Optional<T> findBy(String columnName, Object value);

}
