package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.CommonDAO;
import by.epam.trainig.dao.CreditCardDAO;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public final class CreditCardDAOImpl extends CommonDAO<CreditCard> implements CreditCardDAO {

    private static final Logger logger = LogManager.getLogger(CreditCardDAOImpl.class);

    private static final String TABLE_MANY_TO_MANY = "bank_account";

    private final Class<CreditCard> entityClass = CreditCard.class;
    private final Table tableCreditCardName = entityClass.getAnnotation(Table.class);
    private final List<String> creditCardColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getTableColumn(tableCreditCardName.name());
    private final List<String> creditCardUserColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getManyToManyColumn(tableCreditCardName.name());

    private CreditCardDAOImpl() {
    }

    public List<String> getCreditCardUserColumns() {
        return creditCardUserColumns;
    }

    @Override
    public List<CreditCard> findCreditCardByUserId(int userId, String userTableName, List<String> userColumns) {

        return executeStatementForEntities(
                findByManyToManyQuery(tableCreditCardName.name(), userTableName, TABLE_MANY_TO_MANY,
                        creditCardColumns.get(0), creditCardUserColumns.get(0), userColumns.get(0), userId),
                this::extractResultCatchingException,
                null);

    }

    public static CreditCardDAO getInstance() {
        return CreditCardDAOImpl.Holder.INSTANCE;
    }

    @Override
    public boolean update(String updColumn, Object updValue, String whereColumn, Object whereValue) throws DAOException {

        final int result = executePreparedUpdate(
                updateQuery(tableCreditCardName.name(), updColumn, updValue, whereColumn, whereValue),
                null);

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while updating");
            throw new DAOException("Sql exception occurred while updating");

        }

    }

    @Override
    public List<CreditCard> findAll() {

        return executeStatementForEntities(
                findAllQuery(tableCreditCardName.name()),
                this::extractResultCatchingException,
                null);

    }

    @Override
    public boolean delete(CreditCard entity) throws DAOException {

        final int result = executePreparedUpdate(
                deleteQuery(tableCreditCardName.name(), creditCardColumns.get(0), entity.getId()),
                null);

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while deleting");
            throw new DAOException("Sql exception occurred while deleting");

        }

    }

    @Override
    public boolean create(CreditCard entity) throws DAOException {

        final int result = executePreparedUpdate(
                createQuery(creditCardColumns, tableCreditCardName.name()),
                statement -> fillEntity(statement, entity));

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while creating");
            throw new DAOException("Sql exception occurred while creating");

        }

    }

    @Override
    public boolean create(CreditCard entity, Connection connection) throws DAOException {

        final int result = executePreparedUpdateWithTransaction(
                createQuery(creditCardColumns, tableCreditCardName.name()),
                statement -> fillEntity(statement, entity),
                connection);

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while creating");
            throw new DAOException("Sql exception occurred while creating");

        }

    }

    @Override
    public Optional<CreditCard> findBy(String columnName, Object value) {

        return executeStatementForSpecificEntity(
                findByQuery(tableCreditCardName.name(), columnName, value),
                this::extractResultCatchingException,
                null);

    }

    @Override
    protected CreditCard extractResult(ResultSet rs) throws SQLException {

        return new CreditCard(
        rs.getInt(creditCardColumns.get(0)),
        rs.getString(creditCardColumns.get(1)),
        rs.getString(creditCardColumns.get(2)),
        rs.getDate(creditCardColumns.get(3)),
        rs.getInt(creditCardColumns.get(4)),
        rs.getBigDecimal(creditCardColumns.get(5))
        );

    }

    @Override
    protected void fillEntity(PreparedStatement statement, CreditCard entity) throws SQLException {

        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getCreditCardNumber());
        statement.setString(3, entity.getCardholderName());
        statement.setDate(4, entity.getCardExpirationDate());
        statement.setInt(5, entity.getCVV());
        statement.setBigDecimal(6, entity.getBalance());

    }

    private static class Holder {
        public static final CreditCardDAO INSTANCE = new CreditCardDAOImpl();
    }

}
