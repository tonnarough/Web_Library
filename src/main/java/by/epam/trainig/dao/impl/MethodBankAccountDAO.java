package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.BankAccountDAO;
import by.epam.trainig.dao.connectionpool.ConnectionPool;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.BankAccount;
import by.epam.trainig.entity.user.CreditCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum MethodBankAccountDAO implements BankAccountDAO {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger(MethodBankAccountDAO.class);

    private final QueryOperation queryOperation = QueryOperation.getInstance();

    private final Class<BankAccount> bankAccountClass = BankAccount.class;
    private final Table tableBankAccountClass = bankAccountClass.getAnnotation(Table.class);
    private final List<String> bankAccountColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableBankAccountClass.name());

    private final Class<CreditCard> creditCardClass = CreditCard.class;
    private final Table tableCreditCardClass = creditCardClass.getAnnotation(Table.class);
    private final List<String> creditCardColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableCreditCardClass.name());

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

    }

    @Override
    public List<BankAccount> findAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(String column, Object values) {

    }

    @Override
    public void create(BankAccount entity) {

    }

    @Override
    public void create(BankAccount bankAccount, CreditCard creditCard) throws SQLException {

        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try {
            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            queryOperation.create(creditCardColumnNames, tableCreditCardClass, creditCard, CreditCard.class, connection);

            Optional<CreditCard> creditCardFromDB = queryOperation.findBy(tableCreditCardClass, creditCardColumnNames.get(1),
                    creditCard.getCreditCardNumber(), CreditCard.class);

            creditCardFromDB.ifPresent(detail -> bankAccount.setCreditCardId(detail.getId()));

            queryOperation.create(bankAccountColumnNames, tableBankAccountClass, bankAccount, BankAccount.class, connection);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            connection.commit();

        } catch (SQLException e) {
            logger.error("Failed transaction", e);
            connection.rollback();

        } finally {
            logger.trace("Transaction is completed");
            connection.setAutoCommit(true);
        }


    }

    @Override
    public Optional<BankAccount> findBy(String columnName, Object value) {
        return Optional.empty();
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
