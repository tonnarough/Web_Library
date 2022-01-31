package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.UserDAO;
import by.epam.trainig.dao.UserDetailDAO;
import by.epam.trainig.dao.connectionpool.ConnectionPool;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public enum MethodUserDAO implements UserDAO {
    INSTANCE(QueryOperation.getInstance(), UserDetailDAO.getInstance());

    private static final Logger logger = LogManager.getLogger(MethodUserDAO.class);

    private static final String FIND_USER_DETAIL_BY_EMAIL = "email";

    private final QueryOperation queryOperation;
    private UserDetailDAO userDetailDAO;

    private final Class<User> userClass = User.class;
    private final Table tableUser = userClass.getAnnotation(Table.class);
    private final List<String> userColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableUser.name());

    MethodUserDAO(QueryOperation queryOperation, UserDetailDAO userDetailDAO) {
        this.queryOperation = queryOperation;
        this.userDetailDAO = userDetailDAO;
    }

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        queryOperation.update(tableUser, updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public List<User> findAll() throws DAOException {

        try {

            return queryOperation.findAll(tableUser, User.class);

        } catch (SQLException e) {

            logger.error("Failed with finding of all users", e);
            throw new DAOException(e);

        }

    }

    @Override
    public void delete(String column, Object values) {

        queryOperation.delete(tableUser, column, values);

    }

    @Override
    public void create(User user) {

        queryOperation.create(userColumnNames, tableUser, user, User.class);

    }

    public void create(User user, UserDetail userDetail) throws DAOException {
        logger.trace("Create User & UserDetail, start transaction");

        Connection connection = ConnectionPool.getConnectionPool().getConnection();

        try {

            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            userDetailDAO.create(userDetail, connection);

            Optional<UserDetail> userDetailFromDB = userDetailDAO.findBy(FIND_USER_DETAIL_BY_EMAIL, userDetail.getEmail());

            userDetailFromDB.ifPresent(detail -> user.setUserDetailsId(detail.getId()));

            queryOperation.create(userColumnNames, tableUser, user, User.class, connection);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            connection.commit();

        } catch (SQLException e) {

            logger.error("Failed transaction", e);
            rollback(connection, logger);

        } finally {

            reliaseConnection(connection, logger);

        }
    }

    @Override
    public Optional<User> findBy(String columnName, Object value) {

        return queryOperation.findBy(tableUser, userColumnNames.get(userColumnNames.indexOf(String.format("%s", columnName))),
                value, User.class);

    }

    @Override
    public Optional<User> findById(int id) {

        return findBy(userColumnNames.get(0), id);

    }

}
