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

    private static final Class<User> entityClass = User.class;
    private static final Table tableUserName = entityClass.getAnnotation(Table.class);
    private static final List<String> columnUserNames = DatabaseEntityContext.getDatabaseEntityContext()
            .getTableColumn(tableUserName.name());
    private final List<String> usersCreditCardsColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getManyToManyColumn(tableUserName.name());

    private final UserDetailDAO userDetailDAO;
    private final RoleDAO roleDAO;
    private final CreditCardDAO creditCardDAO;

    private UserDAOImpl(UserDetailDAO userDetailDAO, RoleDAO roleDAO, CreditCardDAO creditCardDAO) {
        super(logger, columnUserNames, tableUserName);
        this.userDetailDAO = userDetailDAO;
        this.roleDAO = roleDAO;
        this.creditCardDAO = creditCardDAO;
    }

    public static UserDAO getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void create(User user) throws DAOException {

        Connection connection = ConnectionPool.getConnectionPool().getConnection();

        try {

            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            userDetailDAO.create(user.getUserDetail(), connection);

            userDetailDAO.findBy(EMAIL, user.getUserDetail().getEmail()).ifPresent(user::setUserDetail);

            create(user, connection);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.commit();

        } catch (SQLException e) {

            logger.error("Failed transaction", e);
            rollback(connection);

        } finally {

            reliaseConnection(connection);

        }
    }

    @Override
    public void create(User user, CreditCard creditCard) throws DAOException {

        Connection connection = ConnectionPool.getConnectionPool().getConnection();

        try {

            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            creditCardDAO.create(creditCard, connection);

            Optional<CreditCard> newCreditCardFromDB = creditCardDAO.findBy(CREDIT_CARD_NUMBER, creditCard.getCreditCardNumber());

            if (newCreditCardFromDB.isEmpty()) {

                logger.error("Something went wrong with credit card creating");
                throw new DAOException("Something went wrong with credit card creating");

            }

            super.create(creditCardDAO.getCreditCardUserColumns().get(0), usersCreditCardsColumns.get(0), TABLE_MANY_TO_MANY,
                    statement -> {
                        statement.setInt(1, user.getId());
                        statement.setInt(2, newCreditCardFromDB.get().getId());
                    }, connection);


            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.commit();

        } catch (SQLException e) {

            logger.error("Failed transaction", e);
            rollback(connection);

        } finally {

            reliaseConnection(connection);

        }

    }

    @Override
    public List<User> findAll(int currentPage, int recordsOnPage) {

        List<User> users = executeStatementForEntities(
                findAllPaginationQuery(tableUserName.name(), currentPage, recordsOnPage),
                this::extractResultCatchingException,
                null);

        for (User user : users) {

            userDetailDAO.findBy(ID, user.getUserDetail().getId()).ifPresent(user::setUserDetail);
            roleDAO.findBy(ID, user.getRole().getId()).ifPresent(user::setRole);

        }
        return users;
    }

    @Override
    public Optional<User> findBy(String columnName, Object value) {

        Optional<User> user = super.findBy(columnName, value);

        if (user.isPresent()) {

            userDetailDAO.findBy(ID, user.get().getUserDetail().getId()).ifPresent(user.get()::setUserDetail);
            roleDAO.findBy(ID, user.get().getRole().getId()).ifPresent(user.get()::setRole);

        }

        return user;

    }

    @Override
    protected User extractResult(ResultSet rs) throws SQLException {

        return new User(
                rs.getInt(columnUserNames.get(0)),
                new Role(rs.getInt(columnUserNames.get(1))),
                new UserDetail(rs.getInt(columnUserNames.get(2))),
                rs.getString(columnUserNames.get(3)),
                rs.getString(columnUserNames.get(4))
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
