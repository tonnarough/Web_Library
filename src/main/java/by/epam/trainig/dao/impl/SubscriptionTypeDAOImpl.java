package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.CommonDAO;
import by.epam.trainig.dao.SubscriptionTypeDAO;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SubscriptionTypeDAOImpl extends CommonDAO<SubscriptionType> implements SubscriptionTypeDAO {

    private static final Logger logger = LogManager.getLogger(SubscriptionTypeDAOImpl.class);

    private final Class<SubscriptionType> entityClass = SubscriptionType.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private final List<String> subscriptionTypeColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getTableColumn(tableName.name());

    public static SubscriptionTypeDAO getInstance() {
        return SubscriptionTypeDAOImpl.Holder.INSTANCE;
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
    public List<SubscriptionType> findAll() {

        return executeStatementForEntities(
                findAllQuery(tableName.name()),
                this::extractResultCatchingException,
                null);

    }

    @Override
    public boolean delete(SubscriptionType entity) throws DAOException {

        final int result = executePreparedUpdate(
                deleteQuery(tableName.name(), subscriptionTypeColumns.get(0), entity.getId()),
                null);

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while deleting");
            throw new DAOException("Sql exception occurred while deleting");

        }

    }

    @Override
    public boolean create(SubscriptionType entity) throws DAOException {

        final int result = executePreparedUpdate(
                createQuery(subscriptionTypeColumns, tableName.name()),
                statement -> fillEntity(statement, entity));

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while creating");
            throw new DAOException("Sql exception occurred while creating");

        }

    }

    @Override
    public Optional<SubscriptionType> findBy(String columnName, Object value) {

        return executeStatementForSpecificEntity(
                findByQuery(tableName.name(), columnName, value),
                this::extractResultCatchingException,
                null);

    }


    @Override
    protected SubscriptionType extractResult(ResultSet rs) throws SQLException {

        return new SubscriptionType(
                rs.getInt(subscriptionTypeColumns.get(0)),
                rs.getString(subscriptionTypeColumns.get(1)),
                rs.getBigDecimal(subscriptionTypeColumns.get(2))
        );

    }

    @Override
    protected void fillEntity(PreparedStatement statement, SubscriptionType entity) throws SQLException {

        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getDescription());
        statement.setBigDecimal(3, entity.getPrice());

    }

    private static class Holder {
        public static final SubscriptionTypeDAO INSTANCE = new SubscriptionTypeDAOImpl();
    }

}
