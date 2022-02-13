package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.CommonDAO;
import by.epam.trainig.dao.RoleDAO;
import by.epam.trainig.entity.user.Role;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public final class RoleDAOImpl extends CommonDAO<Role> implements RoleDAO {

    private static final Logger logger = LogManager.getLogger(RoleDAOImpl.class);

    private final Class<Role> entityClass = Role.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private final List<String> roleColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getTableColumn(tableName.name());

    private RoleDAOImpl() {
    }

    public static RoleDAO getInstance() {
        return RoleDAOImpl.Holder.INSTANCE;
    }

    @Override
    public boolean update(String updColumn, Object updValue, String whereColumn, Object whereValue) throws DAOException {

        final int result = executePreparedUpdate(
                updateQuery(tableName.name(), updColumn, updValue, whereColumn, whereValue),
                null);

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while updating");
            throw new DAOException("Sql exception occurred while updating");

        }

    }

    @Override
    public List<Role> findAll() {

        return executeStatementForEntities(
                findAllQuery(tableName.name()),
                this::extractResultCatchingException,
                null);

    }

    @Override
    public boolean delete(Role entity) throws DAOException {

        final int result = executePreparedUpdate(
                deleteQuery(tableName.name(), roleColumns.get(0), entity.getId()),
                null);

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while deleting");
            throw new DAOException("Sql exception occurred while deleting");

        }

    }

    @Override
    public boolean create(Role entity) throws DAOException {

        final int result = executePreparedUpdate(
                createQuery(roleColumns, tableName.name()),
                statement -> fillEntity(statement, entity));

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while creating");
            throw new DAOException("Sql exception occurred while creating");

        }

    }

    @Override
    public Optional<Role> findBy(String columnName, Object value) {

        return executeStatementForSpecificEntity(
                findByQuery(tableName.name(), columnName, value),
                this::extractResultCatchingException,
                null);

    }

    @Override
    protected Role extractResult(ResultSet rs) throws SQLException {

        return new Role(
        rs.getInt(roleColumns.get(0)),
        rs.getString(roleColumns.get(1))
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
