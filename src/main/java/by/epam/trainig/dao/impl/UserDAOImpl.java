package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.*;
import by.epam.trainig.dao.connectionpool.ConnectionPool;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.Role;
import by.epam.trainig.entity.user.User;
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

public final class UserDAOImpl extends CommonDAO<User> implements UserDAO {

    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);

    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String CREDIT_CARD_NUMBER = "credit_card_number";

    private static final String TABLE_MANY_TO_MANY = "bank_account";

    private final Class<User> entityClass = User.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private final List<String> userColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getTableColumn(tableName.name());
    private final List<String> userCreditCardColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getManyToManyColumn(tableName.name());

    private final UserDetailDAO userDetailDAO;
    private final RoleDAO roleDAO;
    private final CreditCardDAO creditCardDAO;

    private UserDAOImpl(UserDetailDAO userDetailDAO, RoleDAO roleDAO, CreditCardDAO creditCardDAO) {
        this.userDetailDAO = userDetailDAO;
        this.roleDAO = roleDAO;
        this.creditCardDAO = creditCardDAO;
    }

    public static UserDAO getInstance() {
        return Holder.INSTANCE;
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
    public boolean create(User user, Connection connection) throws DAOException {

        final int result = executePreparedUpdateWithTransaction(
                createQuery(userColumns, tableName.name()),
                statement -> fillEntity(statement, user),
                connection);

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while creating");
            throw new DAOException("Sql exception occurred while creating");

        }
    }

    @Override
    public boolean create(User user) throws DAOException {

        boolean result = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();

        try {

            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            result = userDetailDAO.create(user.getUserDetail(), connection);

            userDetailDAO.findBy(EMAIL, user.getUserDetail().getEmail()).ifPresent(user::setUserDetail);

            create(user, connection);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.commit();

        } catch (SQLException e) {

            logger.error("Failed transaction", e);
            rollback(connection, logger);

        } finally {

            reliaseConnection(connection, logger);

        }
        return result;
    }

    @Override
    public void create(User user, CreditCard creditCard) throws DAOException {

        Connection connection = ConnectionPool.getConnectionPool().getConnection();

        try {

            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            creditCardDAO.create(creditCard, connection);

            Optional<CreditCard> creditCardFromDB = creditCardDAO.findBy(CREDIT_CARD_NUMBER, creditCard.getCreditCardNumber());

            executePreparedUpdate(
                    createManyToManyTableQuery(userCreditCardColumns.get(0), creditCardDAO.getCreditCardUserColumns().get(0), TABLE_MANY_TO_MANY),
                    statement -> {
                        statement.setInt(1, user.getId());
                        statement.setInt(2, creditCardFromDB.get().getId());
                    }
            );

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.commit();

        } catch (SQLException e) {

            logger.error("Failed transaction", e);
            rollback(connection, logger);

        } finally {

            reliaseConnection(connection, logger);

        }

    }

    @Override
    public List<User> findAll() {

        return executeStatementForEntities(
                findAllQuery(tableName.name()),
                this::extractResultCatchingException,
                null);

    }

    @Override
    public List<User> findAll(int currentPage, int recordsOnPage) {

        List<User> users = executeStatementForEntities(
                findAllPaginationQuery(tableName.name(), currentPage, recordsOnPage),
                this::extractResultCatchingException,
                null);

        for (User user : users) {

            userDetailDAO.findBy(ID, user.getUserDetail().getId()).ifPresent(user::setUserDetail);
            roleDAO.findBy(ID, user.getRole().getId()).ifPresent(user::setRole);

            for (CreditCard creditCard : creditCardDAO.findCreditCardByUserId(user.getId(), tableName.name(), userCreditCardColumns)) {

                if (creditCard != null) {
                    user.setCreditCard(creditCard);
                }

            }

        }
        return users;
    }

    @Override
    public boolean delete(User entity) throws DAOException {

        final int result = executePreparedUpdate(
                deleteQuery(tableName.name(), userColumns.get(0), entity.getId()),
               null);

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while deleting");
            throw new DAOException("Sql exception occurred while deleting");

        }

    }

    @Override
    public Optional<User> findBy(String columnName, Object value) {

        Optional<User> user = executeStatementForSpecificEntity(
                findByQuery(tableName.name(), columnName, value),
                this::extractResultCatchingException,
                null);

        if (user.isPresent()) {

            userDetailDAO.findBy(ID, user.get().getUserDetail().getId()).ifPresent(user.get()::setUserDetail);
            roleDAO.findBy(ID, user.get().getRole().getId()).ifPresent(user.get()::setRole);

            for (CreditCard creditCard : creditCardDAO.findCreditCardByUserId(user.get().getId(), tableName.name(), userCreditCardColumns)) {

                if (creditCard != null) {
                    user.get().setCreditCard(creditCard);
                }
            }
        }

        return user;

    }

    @Override
    protected User extractResult(ResultSet rs) throws SQLException {

        return new User(
                rs.getInt(userColumns.get(0)),
                new Role(rs.getInt(userColumns.get(1))),
                new UserDetail(rs.getInt(userColumns.get(2))),
                rs.getString(userColumns.get(3)),
                rs.getString(userColumns.get(4))
        );
    }

    @Override
    protected void fillEntity(PreparedStatement statement, User entity) throws SQLException {

        statement.setInt(1, entity.getId());
        statement.setInt(2, entity.getRole().getId());
        statement.setInt(3, entity.getUserDetail().getId());
        statement.setString(4, entity.getLogin());
        statement.setString(5, entity.getPassword());

    }

    private static class Holder {
        public static final UserDAO INSTANCE = new UserDAOImpl(UserDetailDAO.getInstance(),
                RoleDAO.getInstance(), CreditCardDAO.getInstance());
    }

}
