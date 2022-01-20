package by.epam.trainig.service.impl;

import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.EntityDAOFactory;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.service.SubscriptionService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum SubscriptionServiceImpl implements SubscriptionService {
    INSTANCE;

    private final EntityDAO<Subscription> subscriptionDAO = EntityDAOFactory.getInstance().entityDAO(Subscription.class);
    private static final String FIND_SUBSCRIPTION_BY_PARAMETER = "user_id";

    @Override
    public List<Subscription> findAll() throws SQLException {
        return null;
    }

    @Override
    public void create(Subscription entity) {

    }

    @Override
    public Optional<Subscription> findByUserId(Integer id) {
        return subscriptionDAO.findBy(FIND_SUBSCRIPTION_BY_PARAMETER, id);
    }

}
