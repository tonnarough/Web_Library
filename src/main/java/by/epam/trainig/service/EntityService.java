package by.epam.trainig.service;

import by.epam.trainig.entity.Entity;
import by.epam.trainig.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface EntityService<T extends Entity> {

    List<T> findAll() throws ServiceException;

    void create(T entity);

}
