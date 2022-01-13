package by.epam.trainig.dao.buildentity.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.entity.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class UserBuilder implements EntityBuilder<User> {

    private final Class<User> entityClass = User.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private List<String> userColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getDatabaseContext(tableName.name());

    @Override
    public User buildEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(userColumns.get(0)));
        user.setRoleId(resultSet.getInt(userColumns.get(1)));
        user.setUserDetailsId(resultSet.getInt(userColumns.get(2)));
        user.setLogin(resultSet.getString(userColumns.get(3)));
        user.setPassword(resultSet.getString(userColumns.get(4)));
        return user;
    }

    @Override
    public void buildResultSetByEntity(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setInt(2, entity.getRoleId());
        preparedStatement.setInt(3, entity.getUserDetailsId());
        preparedStatement.setString(4, entity.getLogin());
        preparedStatement.setString(5, entity.getPassword());
    }

}


