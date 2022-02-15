package by.epam.trainig.service.impl;

import by.epam.trainig.dao.GenreDAO;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.exception.DAOException;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.GenreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public enum GenreServiceImpl implements GenreService {
    INSTANCE(GenreDAO.getInstance());

    private static final Logger logger = LogManager.getLogger(GenreServiceImpl.class);

    private final GenreDAO genreDAO;

    GenreServiceImpl(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) throws ServiceException {

        try {

            genreDAO.update(updColumn, updValue, whereColumn, whereValue);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while updating genre", e);
            throw new ServiceException("Sql exception occurred while updating genre", e);

        }

    }

    @Override
    public void delete(Genre genre) throws ServiceException {

        try {

            genreDAO.delete(genre.getId());

        } catch (DAOException e) {

            logger.error("Sql exception occurred while deleting genre", e);
            throw new ServiceException("Sql exception occurred while deleting genre", e);
        }

    }

    @Override
    public List<Genre> findAll(int currentPage, int recordsPerPage) {

        return genreDAO.findAll(currentPage, recordsPerPage);

    }

    @Override
    public Optional<Genre> findBy(String columnName, Object value) {

        return genreDAO.findBy(columnName, value);

    }

    @Override
    public List<Genre> findAllWhere(String column, Object value) {

        return genreDAO.findAllWhere(column,value);

    }

    @Override
    public void create(Genre entity) throws ServiceException {

        try {

            genreDAO.create(entity);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while creating genre", e);
            throw new ServiceException("Sql exception occurred while creating genre", e);
        }

    }

    @Override
    public int getNumberOfRows() {

        return genreDAO.getCountOfRows();

    }

    @Override
    public List<Book> findBooksByGenreId(int genreId) {

        return genreDAO.findBooksByGenreId(genreId);

    }
}
