package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.*;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.entity.user.User;
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

    private static final Class<Subscription> SubscriptionClass = Subscription.class;
    private static final Table tableSubscriptionName = SubscriptionClass.getAnnotation(Table.class);
    private static final List<String> columnSubscriptionNames = DatabaseEntityContext
            .getDatabaseEntityContext().getTableColumn(tableSubscriptionName.name());

    private SubscriptionDAOImpl(UserDAO userDAO, SubscriptionTypeDAO subscriptionTypeDAO) {
        super(logger, columnSubscriptionNames, tableSubscriptionName);
        this.userDAO = userDAO;
        this.subscriptionTypeDAO = subscriptionTypeDAO;
    }

    public static SubscriptionDAO getInstance() {
        return SubscriptionDAOImpl.Holder.INSTANCE;
    }

    @Override
    public List<Subscription> findAll(int currentPage, int recordsOnPage) {

        List<Subscription> subscriptions = executeStatementForEntities(
                findAllPaginationQuery(tableSubscriptionName.name(), currentPage, recordsOnPage),
                this::extractResultCatchingException,
                null);

        for (Subscription subscription : subscriptions) {

            userDAO.findBy(ID,
                    subscription.getUser().getId())
                    .ifPresent(subscription::setUser);

            subscriptionTypeDAO.findBy(ID,
                    subscription.getSubscriptionType().getId())
                    .ifPresent(subscription::setSubscriptionType);

        }
        return subscriptions;

    }

    @Override
    public Optional<Subscription> findBy(String columnName, Object value) {

        Optional<Subscription> subscription = super.findBy(columnName, value);

        if (subscription.isPresent()) {

            userDAO.findBy(ID,
                    subscription.get().getUser().getId())
                    .ifPresent(subscription.get()::setUser);

            subscriptionTypeDAO.findBy(ID,
                    subscription.get().getSubscriptionType().getId())
                    .ifPresent(subscription.get()::setSubscriptionType);

        }
        return subscription;
    }

    @Override
    protected Subscription extractResult(ResultSet rs) throws SQLException {

        return new Subscription(
                rs.getInt(columnSubscriptionNames.get(0)),
                new User(rs.getInt(columnSubscriptionNames.get(1))),
                new SubscriptionType(rs.getInt(columnSubscriptionNames.get(2))),
                rs.getDate(columnSubscriptionNames.get(3)),
                rs.getDate(columnSubscriptionNames.get(4))
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
