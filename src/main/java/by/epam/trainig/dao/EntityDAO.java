package by.epam.trainig.dao;

import by.epam.trainig.entity.Entity;
import by.epam.trainig.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface EntityDAO<T extends Entity> {

    boolean update(String updColumn, Object updValue, String whereColumn, Object whereValue) throws DAOException;

    List<T> findAll();

    boolean delete(T entity) throws DAOException;

    boolean create (T entity) throws DAOException;

    Optional<T> findBy(String columnName, Object value);

}
