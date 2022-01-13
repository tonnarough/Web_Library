package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.UserDAO;
import by.epam.trainig.dao.queryoperation.Impl.QueryOperationImpl;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public final class MethodUserDAO implements UserDAO {

    private final Class<User> userClass = User.class;
    private final Table tableName = userClass.getAnnotation(Table.class);
    private final QueryOperation queryOperation = QueryOperationImpl.getInstance();
    private final DatabaseEntityContext databaseEntityContext = DatabaseEntityContext
            .getDatabaseEntityContext();

    @Override
    public void update(String column1, Object value1, String column2, Object value2) {

        queryOperation.update(tableName, column1, value1, column2, value2);
    }

    @Override
    public List<User> findAll() throws SQLException {

        return queryOperation.findAll(tableName, User.class);
    }

    @Override
    public void delete(String column, Object values) {

        queryOperation.delete(tableName, column, values);
    }

    @Override
    public void create(User user) {

        queryOperation.create(databaseEntityContext.getDatabaseContext(tableName.name()),
                tableName, user, User.class);
    }


    @Override
    public Optional<User> findUserById(Integer id) {

        return queryOperation.findBy(tableName, databaseEntityContext
                .getDatabaseContext(tableName.name()).get(0), id.toString(), User.class);
    }

    @Override
    public Optional<User> findByLogin(String login) {

        return queryOperation.findBy(tableName, databaseEntityContext
                .getDatabaseContext(tableName.name()).get(3), login, User.class);
    }
}
