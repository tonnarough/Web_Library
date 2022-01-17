package by.epam.trainig.dao.buildentity.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.entity.user.Role;
import by.epam.trainig.entity.user.UserDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleBuilder implements EntityBuilder<Role> {

    private final Class<Role> entityClass = Role.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private final List<String> roleColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getDatabaseContext(tableName.name());

    @Override
    public Role buildEntity(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt(roleColumns.get(0)));
        role.setTitle(resultSet.getString(roleColumns.get(1)));
        return role;
    }

    @Override
    public void buildResultSetByEntity(PreparedStatement preparedStatement, Role entity) throws SQLException {
        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setString(2, entity.getTitle());
    }
}
