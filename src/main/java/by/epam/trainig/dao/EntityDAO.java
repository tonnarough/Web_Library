package by.epam.trainig.dao;

import by.epam.trainig.entity.Entity;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EntityDAO<T extends Entity> {

    void update(String updColumn, Object updValue, String whereColumn, Object whereValue);

    List<T> findAll() throws DAOException;

    void delete(T entity);

    void create(T entity);

    Optional<T> findBy(String columnName, Object value);

    Optional<T> findById(int id);

    default void rollback(Connection connection, Logger logger) throws DAOException {

        if (connection != null) {

            try {

                connection.rollback();

            } catch (SQLException e) {

                logger.error("Unable to rollback transaction", e);
                throw new DAOException("Unable to rollback transaction", e);

            }
        }
    }

    default void reliaseConnection(Connection connection, Logger logger) throws DAOException {

        if (connection != null) {

            try {

                connection.setAutoCommit(true);
                connection.close();

            } catch (SQLException e) {

                logger.error("Unable to return connection to connection pool", e);
                throw new DAOException("Unable to return connection to connection pool", e);

            }
        }
    }

}
