package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.connectionpool.ConnectionPool;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public final class MethodUserDAO implements EntityDAO<User> {

    private final Class<UserDetail> userDetailClass = UserDetail.class;
    private final Table tableUserDetail = userDetailClass.getAnnotation(Table.class);
    private final Class<User> userClass = User.class;
    private final Table tableUser = userClass.getAnnotation(Table.class);
    private final QueryOperation queryOperation = QueryOperation.getInstance();
    private final List<String> userColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableUser.name());
    private final List<String> userDetailColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableUser.name());


    @Override
    public void update(String column1, Object value1, String column2, Object value2) {

        queryOperation.update(tableUser, column1, value1, column2, value2);
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

        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try {
            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            queryOperation.create(userDetailColumnNames, tableUserDetail, userDetail, UserDetail.class, connection);

            Optional<UserDetail> userDetailFromDB = queryOperation.findBy(tableUserDetail, userDetailColumnNames.get(4),
                    userDetail.getEmail(), UserDetail.class);

            userDetailFromDB.ifPresent(detail -> user.setUserDetailsId(detail.getId()));

            queryOperation.create(userColumnNames, tableUser, user, User.class, connection);

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();

            connection.rollback();

        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public Optional<User> findBy(String columnName, Object value) {

        return queryOperation.findBy(tableUser, userColumnNames.get(userColumnNames.indexOf(String.format("%s", columnName))),
                value, User.class);
    }

}
