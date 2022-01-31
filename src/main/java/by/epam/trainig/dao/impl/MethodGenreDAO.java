package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.GenreDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum MethodGenreDAO implements GenreDAO {
    INSTANCE(QueryOperation.getInstance());

    private static final Logger logger = LogManager.getLogger(MethodGenreDAO.class);

    private final QueryOperation queryOperation;

    private final Class<Genre> genreClass = Genre.class;
    private final Table tableGenre = genreClass.getAnnotation(Table.class);
    private final List<String> genreColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableGenre.name());

    MethodGenreDAO(QueryOperation queryOperation) {
        this.queryOperation = queryOperation;
    }

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        queryOperation.update(tableGenre, updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public List<Genre> findAll() throws DAOException {

        try {

            return queryOperation.findAll(tableGenre, Genre.class);

        } catch (SQLException e) {

            logger.error("Failed finding of genres", e);
            throw new DAOException(e);
        }

    }

    @Override
    public void delete(String column, Object values) {

        queryOperation.delete(tableGenre, column, values);

    }

    @Override
    public void create(Genre entity) {

        queryOperation.create(genreColumnNames, tableGenre, entity, Genre.class);

    }

    @Override
    public Optional<Genre> findBy(String columnName, Object value) {

        return queryOperation.findBy(tableGenre, columnName, value, Genre.class);

    }

    @Override
    public Optional<Genre> findById(int id) {

        return findBy(genreColumnNames.get(0), id);

    }
}
