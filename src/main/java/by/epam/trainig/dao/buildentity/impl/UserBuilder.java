package by.epam.trainig.dao.buildentity.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.annotation.Column;
import by.epam.trainig.entity.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class UserBuilder implements EntityBuilder<User> {

    private List<String> userColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getDatabaseContext("users");

    @Override
    public User buildEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUsersId(resultSet.getInt(userColumns.get(0)));
        user.setLogin(resultSet.getString(userColumns.get(1)));
        user.setPassword(resultSet.getString(userColumns.get(2)));
        user.setEmail(resultSet.getString(userColumns.get(3)));
        user.setLastName(resultSet.getString(userColumns.get(4)));
        user.setName(resultSet.getString(userColumns.get(5)));
        user.setFatherName(resultSet.getString(userColumns.get(6)));
        user.setGender(resultSet.getString(userColumns.get(7)));
        user.setMobile(resultSet.getString(userColumns.get(8)));
        user.setPassport(resultSet.getString(userColumns.get(9)));
        user.setBirthday(resultSet.getDate(userColumns.get(10)));
        return user;
    }

    @Override
    public void buildResultSetByEntity(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setInt(1, user.getUsersId());
        preparedStatement.setString(2, user.getLogin());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getLastName());
        preparedStatement.setString(6, user.getName());
        preparedStatement.setString(7, user.getFatherName());
        preparedStatement.setString(8, user.getGender());
        preparedStatement.setString(9, user.getMobile());
        preparedStatement.setString(10, user.getPassport());
        preparedStatement.setDate(11, user.getBirthday());
    }

}


