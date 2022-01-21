package by.epam.trainig.service;

import by.epam.trainig.entity.Entity;

import java.sql.SQLException;
import java.util.List;

public interface EntityService<T extends Entity> {

    List<T> findAll();

    void create(T entity);

}
