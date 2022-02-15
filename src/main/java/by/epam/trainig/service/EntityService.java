package by.epam.trainig.service;

import by.epam.trainig.entity.Entity;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface EntityService<T extends Entity> {

    List<T> findAllWhere(String column, Object value);

    void create(T entity) throws ServiceException;

    int getNumberOfRows();

    void updateIfChanged(boolean isSomethingChanged, String updColumn, Object updValue, String whereColumn, Object whereValue) throws ServiceException;

    void update(String updColumn, Object updValue, String whereColumn, Object whereValue) throws ServiceException;

    void delete(Integer id) throws ServiceException;

    List<T> findAll(int currentPage, int recordsPerPage);

    Optional<T> findBy(String columnName, Object value);

}
