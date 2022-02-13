package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.*;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class SubscriptionDAOImpl extends CommonDAO<Subscription> implements SubscriptionDAO {

    private static final Logger logger = LogManager.getLogger(SubscriptionDAOImpl.class);

    private static final String ID = "id";

    private final UserDAO userDAO;
    private final SubscriptionTypeDAO subscriptionTypeDAO;

    private final Class<Subscription> SubscriptionClass = Subscription.class;
    private final Table tableSubscription = SubscriptionClass.getAnnotation(Table.class);
    private final List<String> subscriptionColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getTableColumn(tableSubscription.name());

    private SubscriptionDAOImpl(UserDAO userDAO, SubscriptionTypeDAO subscriptionTypeDAO) {
        this.userDAO = userDAO;
        this.subscriptionTypeDAO = subscriptionTypeDAO;
    }

    public static SubscriptionDAO getInstance() {
        return SubscriptionDAOImpl.Holder.INSTANCE;
    }

    @Override
    public boolean update(String updColumn, Object updValue, String whereColumn, Object whereValue) throws DAOException {

        final int result = executePreparedUpdate(
                updateQuery(tableSubscription.name(), updColumn, updValue, whereColumn, whereValue),
                null);

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while updating");
            throw new DAOException("Sql exception occurred while updating");

        }

    }

    @Override
    public List<Subscription> findAll() {

        return executeStatementForEntities(
                findAllQuery(tableSubscription.name()),
                this::extractResultCatchingException,
                null);

    }

    @Override
    public List<Subscription> findAll(int currentPage, int recordsOnPage) {

        List<Subscription> subscriptions = executeStatementForEntities(
                findAllPaginationQuery(tableSubscription.name(), currentPage, recordsOnPage),
                this::extractResultCatchingException,
                null);

        for (Subscription subscription : subscriptions) {

            userDAO.findBy(ID, subscription.getUser().getId()).ifPresent(subscription::setUser);
            subscriptionTypeDAO.findBy(ID, subscription.getSubscriptionType().getId()).ifPresent(subscription::setSubscriptionType);

        }
        return subscriptions;

    }

    @Override
    public boolean delete(Subscription entity) throws DAOException {

        final int result = executePreparedUpdate(
                deleteQuery(tableSubscription.name(), subscriptionColumnNames.get(0), entity.getId()),
                null);

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while deleting");
            throw new DAOException("Sql exception occurred while deleting");

        }

    }

    @Override
    public boolean create(Subscription entity) throws DAOException {

        final int result = executePreparedUpdate(
                createQuery(subscriptionColumnNames, tableSubscription.name()),
                statement -> fillEntity(statement, entity));

        if (result > 0) {

            return true;

        } else {

            logger.error("Sql exception occurred while creating");
            throw new DAOException("Sql exception occurred while creating");

        }

    }

    @Override
    public Optional<Subscription> findBy(String columnName, Object value) {

        Optional<Subscription> subscription = executeStatementForSpecificEntity(
                findByQuery(tableSubscription.name(), columnName, value),
                this::extractResultCatchingException,
                null);

        if (subscription.isPresent()) {

            userDAO.findBy(ID, subscription.get().getUser().getId()).ifPresent(subscription.get()::setUser);
            subscriptionTypeDAO.findBy(ID, subscription.get().getSubscriptionType().getId()).ifPresent(subscription.get()::setSubscriptionType);

        }
        return subscription;
    }

    @Override
    protected Subscription extractResult(ResultSet rs) throws SQLException {

        return new Subscription(
                rs.getInt(subscriptionColumnNames.get(0)),
                new User(rs.getInt(subscriptionColumnNames.get(1))),
                new SubscriptionType(rs.getInt(subscriptionColumnNames.get(2))),
                rs.getDate(subscriptionColumnNames.get(3)),
                rs.getDate(subscriptionColumnNames.get(4))
        );

    }

    @Override
    protected void fillEntity(PreparedStatement statement, Subscription entity) throws SQLException {

        statement.setInt(1, entity.getId());
        statement.setInt(2, entity.getUser().getId());
        statement.setInt(3, entity.getSubscriptionType().getId());
        statement.setDate(4, entity.getStartDate());
        statement.setDate(5, entity.getEndDate());

    }

    private static class Holder {
        public static final SubscriptionDAO INSTANCE = new SubscriptionDAOImpl(UserDAO.getInstance(), SubscriptionTypeDAO.getInstance());
    }

}
