package by.epam.trainig.service.impl;

import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.EntityDAOFactory;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.service.SubscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum SubscriptionServiceImpl implements SubscriptionService {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final EntityDAO<SubscriptionType> subscriptionTypesDAO = EntityDAOFactory.getInstance().entityDAO(SubscriptionType.class);
    private final EntityDAO<Subscription> subscriptionDAO = EntityDAOFactory.getInstance().entityDAO(Subscription.class);
    private static final String FIND_SUBSCRIPTION_BY_PARAMETER = "user_id";

    @Override
    public List<SubscriptionType> findAllTypes() {
        try {
            return subscriptionTypesDAO.findAll();
        } catch (SQLException e) {
            logger.error("Subscription types are not found", e);
        }
        return null;
    }

    @Override
    public List<Subscription> findAll() {
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
