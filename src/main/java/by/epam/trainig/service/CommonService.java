package by.epam.trainig.service;

import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.entity.Entity;
import by.epam.trainig.exception.DAOException;
import by.epam.trainig.exception.ServiceException;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public abstract class CommonService<T extends Entity> implements EntityService<T> {

    private final EntityDAO<T> entityDAO;
    private final Logger logger;

    public CommonService(EntityDAO<T> entityDAO, Logger logger) {
        this.entityDAO = entityDAO;
        this.logger = logger;
    }

    @Override
    public List<T> findAllWhere(String column, Object value) {

        return entityDAO.findAllWhere(column, value);

    }

    @Override
    public void create(T entity) throws ServiceException {

        try {

            entityDAO.create(entity);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while creating entity", e);
            throw new ServiceException("Sql exception occurred while creating entity", e);
        }

    }

    @Override
    public int getNumberOfRows() {

        return entityDAO.getCountOfRows();

    }

    @Override
    public void updateIfChanged(boolean isSomethingChanged, String updColumn, Object updValue, String whereColumn, Object whereValue) throws ServiceException {

        if(isSomethingChanged) {

            try {

                entityDAO.update(updColumn, updValue, whereColumn, whereValue);

            } catch (DAOException e) {

                logger.error("Sql exception occurred while updating entity", e);
                throw new ServiceException("Sql exception occurred while updating entity", e);

            }
        }

    }

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) throws ServiceException {

        try {

            entityDAO.update(updColumn, updValue, whereColumn, whereValue);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while updating entity", e);
            throw new ServiceException("Sql exception occurred while updating entity", e);
        }

    }

    @Override
    public void delete(Integer id) throws ServiceException {

        try {

            entityDAO.delete(id);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while deleting entity", e);
            throw new ServiceException("Sql exception occurred while deleting entity", e);
        }

    }

    @Override
    public List<T> findAll(int currentPage, int recordsPerPage) {

        return entityDAO.findAll(currentPage, recordsPerPage);

    }

    @Override
    public Optional<T> findBy(String columnName, Object value) {

        return entityDAO.findBy(columnName, value);

    }
}
