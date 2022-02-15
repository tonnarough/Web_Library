package by.epam.trainig.service.impl;

import by.epam.trainig.dao.PublishingHouseDAO;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.exception.DAOException;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.PublishingHouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public enum PublishingHouseServiceImpl implements PublishingHouseService {
    INSTANCE(PublishingHouseDAO.getInstance());

    private static final Logger logger = LogManager.getLogger(PublishingHouseServiceImpl.class);

    private final PublishingHouseDAO publishingHouseDAO;

    PublishingHouseServiceImpl(PublishingHouseDAO publishingHouseDAO) {
        this.publishingHouseDAO = publishingHouseDAO;
    }

    @Override
    public List<PublishingHouse> findAll(int currentPage, int recordsPerPage) {

        return publishingHouseDAO.findAll(currentPage, recordsPerPage);

    }

    @Override
    public Optional<PublishingHouse> findBy(String columnName, Object value) {

        return publishingHouseDAO.findBy(columnName, value);

    }

    @Override
    public void delete(PublishingHouse publishingHouse) throws ServiceException {

        try {

            publishingHouseDAO.delete(publishingHouse.getId());

        } catch (DAOException e) {

            logger.error("Sql exception occurred while deleting publishingHouse", e);
            throw new ServiceException("Sql exception occurred while deleting publishingHouse", e);
        }

    }

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) throws ServiceException {

        try {

            publishingHouseDAO.update(updColumn, updValue, whereColumn, whereValue);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while updating publishing house", e);
            throw new ServiceException("Sql exception occurred while updating publishing house", e);

        }

    }


    @Override
    public List<PublishingHouse> findAllWhere(String column, Object value) {

        return publishingHouseDAO.findAllWhere(column, value);

    }

    @Override
    public void create(PublishingHouse entity) throws ServiceException {

        try {

            publishingHouseDAO.create(entity);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while creating publishingHouse", e);
            throw new ServiceException("Sql exception occurred while creating publishingHouse", e);
        }

    }

    @Override
    public int getNumberOfRows() {

        return publishingHouseDAO.getCountOfRows();

    }

    @Override
    public List<Book> findBooksByPublishingHouseId(int publishingHouseId) {

        return publishingHouseDAO.findBooksByPublishingHouseId(publishingHouseId);

    }
}
