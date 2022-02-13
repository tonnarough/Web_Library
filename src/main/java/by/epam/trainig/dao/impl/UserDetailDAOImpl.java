package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.CommonDAO;
import by.epam.trainig.dao.UserDetailDAO;
import by.epam.trainig.entity.user.UserDetail;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public final class UserDetailDAOImpl extends CommonDAO<UserDetail> implements UserDetailDAO {

    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);

    private final Class<UserDetail> userDetailClass = UserDetail.class;
    private final Table tableUserDetail = userDetailClass.getAnnotation(Table.class);
    private final List<String> userDetailColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getTableColumn(tableUserDetail.name());

    private UserDetailDAOImpl() {
    }

    public static UserDetailDAO getInstance() {
        return UserDetailDAOImpl.Holder.INSTANCE;
    }

    @Override
    public boolean update(String updColumn, Object updValue, String whereColumn, Object whereValue) throws DAOException {

        final int result = executePreparedUpdate(
                updateQuery(tableUserDetail.name(), updColumn, updValue, whereColumn, whereValue),
                null);

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while updating");
            throw new DAOException("Sql exception occurred while updating");

        }

    }

    @Override
    public List<UserDetail> findAll() {

        return executeStatementForEntities(
                findAllQuery(tableUserDetail.name()),
                this::extractResultCatchingException,
                null);

    }

    @Override
    public boolean delete(UserDetail entity) throws DAOException {

        final int result = executePreparedUpdate(
                deleteQuery(tableUserDetail.name(), userDetailColumnNames.get(0), entity.getId()),
                null);

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while deleting");
            throw new DAOException("Sql exception occurred while deleting");

        }

    }

    @Override
    public boolean create(UserDetail entity) throws DAOException {

        final int result = executePreparedUpdate(
                createQuery(userDetailColumnNames, tableUserDetail.name()),
                statement -> fillEntity(statement, entity));

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while creating");
            throw new DAOException("Sql exception occurred while creating");

        }

    }

    @Override
    public Optional<UserDetail> findBy(String columnName, Object value) {

        return executeStatementForSpecificEntity(
                findByQuery(tableUserDetail.name(), columnName, value),
                this::extractResultCatchingException,
                null);

    }

    @Override
    public boolean create(UserDetail userDetail, Connection connection) throws DAOException {

        final int result = executePreparedUpdateWithTransaction(
                createQuery(userDetailColumnNames, tableUserDetail.name()),
                statement -> fillEntity(statement, userDetail),
                connection);

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while creating");
            throw new DAOException("Sql exception occurred while creating");

        }

    }

    @Override
    protected UserDetail extractResult(ResultSet rs) throws SQLException {

        return new UserDetail(
                rs.getInt(userDetailColumnNames.get(0)),
                rs.getString(userDetailColumnNames.get(1)),
                rs.getString(userDetailColumnNames.get(2)),
                rs.getString(userDetailColumnNames.get(3)),
                rs.getString(userDetailColumnNames.get(4)),
                rs.getString(userDetailColumnNames.get(5)),
                rs.getDate(userDetailColumnNames.get(6))
        );
    }

    @Override
    protected void fillEntity(PreparedStatement statement, UserDetail entity) throws SQLException {

        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getLastName());
        statement.setString(3, entity.getFirstName());
        statement.setString(4, entity.getFatherName());
        statement.setString(5, entity.getEmail());
        statement.setString(6, entity.getMobile());
        statement.setDate(7, entity.getBirthday());

    }

    private static class Holder {
        public static final UserDetailDAO INSTANCE = new UserDetailDAOImpl();
    }

}
