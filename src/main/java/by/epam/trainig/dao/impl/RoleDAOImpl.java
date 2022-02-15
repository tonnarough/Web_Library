package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.CommonDAO;
import by.epam.trainig.dao.RoleDAO;
import by.epam.trainig.entity.user.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class RoleDAOImpl extends CommonDAO<Role> implements RoleDAO {

    private static final Logger logger = LogManager.getLogger(RoleDAOImpl.class);

    private static final Class<Role> entityClass = Role.class;
    private static final Table tableRoleName = entityClass.getAnnotation(Table.class);
    private static final List<String> columnRoleNames = DatabaseEntityContext.getDatabaseEntityContext()
            .getTableColumn(tableRoleName.name());

    private RoleDAOImpl() {
        super(logger, columnRoleNames, tableRoleName);
    }

    public static RoleDAO getInstance() {
        return RoleDAOImpl.Holder.INSTANCE;
    }

    @Override
    protected Role extractResult(ResultSet rs) throws SQLException {

        return new Role(
        rs.getInt(columnRoleNames.get(0)),
        rs.getString(columnRoleNames.get(1))
        );

    }

    @Override
    protected void fillEntity(PreparedStatement statement, Role entity) throws SQLException {

        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getTitle());

    }

    private static class Holder {
        public static final RoleDAO INSTANCE = new RoleDAOImpl();
    }

}
