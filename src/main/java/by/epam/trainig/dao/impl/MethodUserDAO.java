package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.UserDAO;
import by.epam.trainig.dao.connectionpool.ConnectionPool;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public enum MethodUserDAO implements UserDAO {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger(MethodUserDAO.class);

    private final QueryOperation queryOperation = QueryOperation.getInstance();

    private final Class<UserDetail> userDetailClass = UserDetail.class;
    private final Table tableUserDetail = userDetailClass.getAnnotation(Table.class);
    private final Class<User> userClass = User.class;
    private final Table tableUser = userClass.getAnnotation(Table.class);

    private final List<String> userColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableUser.name());

    private final List<String> userDetailColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableUserDetail.name());


    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        queryOperation.update(tableUser, updColumn, updValue, whereColumn, whereValue);
    }

    @Override
    public List<User> findAll() throws SQLException {

        return queryOperation.findAll(tableUser, User.class);
    }

    @Override
    public void delete(String column, Object values) {

        queryOperation.delete(tableUser, column, values);
    }

    @Override
    public void create(User user) {

        queryOperation.create(userColumnNames, tableUser, user, User.class);
    }

    public void create(User user, UserDetail userDetail) throws SQLException {
        logger.trace("Create User & UserDetail, start transaction");

        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try {
            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            queryOperation.create(userDetailColumnNames, tableUserDetail, userDetail, UserDetail.class, connection);

            Optional<UserDetail> userDetailFromDB = queryOperation.findBy(tableUserDetail, userDetailColumnNames.get(4),
                    userDetail.getEmail(), UserDetail.class);

            userDetailFromDB.ifPresent(detail -> user.setUserDetailsId(detail.getId()));

            queryOperation.create(userColumnNames, tableUser, user, User.class, connection);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            connection.commit();
        } catch (SQLException e) {
            logger.error("Failed transaction", e);

            connection.rollback();

        } finally {
            logger.trace("Transaction is completed");
            connection.setAutoCommit(true);
        }
    }

    @Override
    public Optional<User> findBy(String columnName, Object value) {

        return queryOperation.findBy(tableUser, userColumnNames.get(userColumnNames.indexOf(String.format("%s", columnName))),
                value, User.class);
    }

}
