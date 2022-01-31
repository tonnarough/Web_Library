package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.CreditCardDAO;
import by.epam.trainig.dao.connectionpool.ConnectionPool;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum MethodCreditCardDAO implements CreditCardDAO {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger(MethodCreditCardDAO.class);

    private final QueryOperation queryOperation = QueryOperation.getInstance();

    private static final String ADDED_IN_BANK_ACCOUNT_TABLE = "INSERT INTO bank_account (user_id,credit_card_id) VALUES (?,?)";

    private final Class<CreditCard> creditCardClass = CreditCard.class;
    private final Table tableCreditCardClass = creditCardClass.getAnnotation(Table.class);
    private final List<String> creditCardColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableCreditCardClass.name());

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

    }

    @Override
    public List<CreditCard> findAll() throws DAOException {

        return null;

    }

    @Override
    public void delete(String column, Object values) {

    }

    @Override
    public void create(CreditCard entity) {

    }

    @Override
    public void create(User user, CreditCard creditCard) throws DAOException {

        Connection connection = ConnectionPool.getConnectionPool().getConnection();

        try {

            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            queryOperation.create(creditCardColumnNames, tableCreditCardClass, creditCard, CreditCard.class, connection);

            Optional<CreditCard> creditCardFromDB = queryOperation.findBy(tableCreditCardClass, creditCardColumnNames.get(1),
                    creditCard.getCreditCardNumber(), CreditCard.class);

            queryOperation.create(ADDED_IN_BANK_ACCOUNT_TABLE, user.getId(), creditCardFromDB.get().getId(), connection);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            connection.commit();

        } catch (SQLException e) {

            logger.error("Failed transaction", e);
            rollback(connection ,logger);

        } finally {

            reliaseConnection(connection, logger);

        }
    }

    @Override
    public Optional<CreditCard> findBy(String columnName, Object value) {

        return Optional.empty();

    }

    @Override
    public Optional<CreditCard> findById(int id) {

        return findBy(creditCardColumnNames.get(0), id);

    }

    @Override
    public void updateCreditCard(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        queryOperation.update(tableCreditCardClass, updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public Optional<CreditCard> findCreditCardBy(String columnName, Object value) {

        return queryOperation.findBy(tableCreditCardClass, creditCardColumnNames.get(creditCardColumnNames.indexOf(String.format("%s", columnName))),
                value, CreditCard.class);

    }

}
