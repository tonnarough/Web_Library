package by.epam.trainig.service.impl;

import by.epam.trainig.dao.AuthorDAO;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.exception.DAOException;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.AuthorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public enum AuthorServiceImpl implements AuthorService {
    INSTANCE(AuthorDAO.getInstance());

    private static final Logger logger = LogManager.getLogger(AuthorServiceImpl.class);

    private final AuthorDAO authorDAO;

    AuthorServiceImpl(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Override
    public List<Author> findAll(int currentPage, int recordsPerPage) {

        return authorDAO.findAll(currentPage, recordsPerPage);

    }

    @Override
    public Optional<Author> findBy(String columnName, Object value) {

        return authorDAO.findBy(columnName, value);

    }

    @Override
    public Optional<Author> findBy(String[] columnNames, Object[] values) {

        return authorDAO.findBy(columnNames, values);

    }

    @Override
    public List<Book> findBooksByAuthorId(int authorId) {

        return authorDAO.findBooksByAuthorId(authorId);

    }

    @Override
    public void delete(Author author) throws ServiceException {

        try {

            authorDAO.delete(author.getId());

        } catch (DAOException e) {

            logger.error("Sql exception occurred while deleting author", e);
            throw new ServiceException("Sql exception occurred while deleting author", e);
        }

    }

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) throws ServiceException {

        try {

            authorDAO.update(updColumn, updValue, whereColumn, whereValue);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while updating author", e);
            throw new ServiceException("Sql exception occurred while updating author", e);

        }

    }

    @Override
    public List<Author> findAllWhere(String column, Object value) {

        return authorDAO.findAllWhere(column, value);

    }

    @Override
    public void create(Author entity) throws ServiceException {

        try {

            authorDAO.create(entity);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while creating author", e);
            throw new ServiceException("Sql exception occurred while creating author", e);

        }

    }

    @Override
    public int getNumberOfRows() {

        return authorDAO.getCountOfRows();

    }

}
