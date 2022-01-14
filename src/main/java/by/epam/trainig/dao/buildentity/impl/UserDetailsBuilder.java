package by.epam.trainig.dao.buildentity.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.entity.user.UserDetail;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDetailsBuilder implements EntityBuilder<UserDetail> {

    private final Class<UserDetail> entityClass = UserDetail.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private List<String> userDetailsColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getDatabaseContext(tableName.name());

    @Override
    public UserDetail buildEntity(ResultSet resultSet) throws SQLException {
        UserDetail userDetail = new UserDetail();
        userDetail.setId(resultSet.getInt(userDetailsColumns.get(0)));
        userDetail.setLastName(resultSet.getString(userDetailsColumns.get(1)));
        userDetail.setFirstName(resultSet.getString(userDetailsColumns.get(2)));
        userDetail.setFatherName(resultSet.getString(userDetailsColumns.get(3)));
        userDetail.setEmail(resultSet.getString(userDetailsColumns.get(4)));
        userDetail.setMobile(resultSet.getString(userDetailsColumns.get(5)));
        userDetail.setBirthday(resultSet.getDate(userDetailsColumns.get(6)));
        return userDetail;
    }

    @Override
    public void buildResultSetByEntity(PreparedStatement preparedStatement, UserDetail entity) throws SQLException {
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setString(3, entity.getFirstName());
        preparedStatement.setString(4, entity.getFatherName());
        preparedStatement.setString(5, entity.getEmail());
        preparedStatement.setString(6, entity.getMobile());
        preparedStatement.setDate(7, entity.getBirthday());
    }
}
