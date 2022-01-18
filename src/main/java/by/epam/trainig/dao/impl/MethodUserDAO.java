package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.UserDAO;
import by.epam.trainig.dao.connectionpool.ConnectionPool;
import by.epam.trainig.dao.queryoperation.Impl.QueryOperationImpl;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public final class MethodUserDAO implements UserDAO {

    private final Class<UserDetail> userDetailClass = UserDetail.class;
    private final Table tableUserDetail = userDetailClass.getAnnotation(Table.class);
    private final Class<User> userClass = User.class;
    private final Table tableUser = userClass.getAnnotation(Table.class);
    private final QueryOperation queryOperation = QueryOperationImpl.getInstance();
    private final DatabaseEntityContext databaseEntityContext = DatabaseEntityContext
            .getDatabaseEntityContext();

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

        queryOperation.create(databaseEntityContext.getDatabaseContext(tableUser.name()),
                tableUser, user, User.class);
    }

    public void create(User user, UserDetail userDetail) throws SQLException {

        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try {
            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            queryOperation.create(databaseEntityContext.getDatabaseContext(tableUserDetail.name()),
                    tableUserDetail, userDetail, UserDetail.class, connection);

            Optional<UserDetail> userDetailFromDB = queryOperation.findBy(tableUserDetail, databaseEntityContext.getDatabaseContext(tableUserDetail.name()).get(4),
                    userDetail.getEmail(), UserDetail.class);

            userDetailFromDB.ifPresent(detail -> user.setUserDetailsId(detail.getId()));

            queryOperation.create(databaseEntityContext.getDatabaseContext(tableUser.name()),
                    tableUser, user, User.class, connection);

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();

            connection.rollback();

        } finally {
            connection.setAutoCommit(true);
        }
    }


    @Override
    public Optional<User> findUserById(Integer id) {

        return queryOperation.findBy(tableUser, databaseEntityContext
                .getDatabaseContext(tableUser.name()).get(0), id.toString(), User.class);
    }

    @Override
    public Optional<User> findByLogin(String login) {

        return queryOperation.findBy(tableUser, databaseEntityContext
                .getDatabaseContext(tableUser.name()).get(3), login, User.class);
    }
}
