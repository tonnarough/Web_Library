package by.epam.trainig.service.impl;

import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.EntityDAOFactory;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.service.SubscriptionService;
import liquibase.repackaged.org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum SubscriptionServiceImpl implements SubscriptionService {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final EntityDAO<SubscriptionType> subscriptionTypesDAO = EntityDAOFactory.getInstance().entityDAO(SubscriptionType.class);
    private final EntityDAO<Subscription> subscriptionDAO = EntityDAOFactory.getInstance().entityDAO(Subscription.class);
    private static final String FIND_SUBSCRIPTION_BY_PARAMETER = "user_id";

    private static final String START_SUBSCRIPTION_DATE = "start_date";
    private static final String END_SUBSCRIPTION_DATE = "end_date";

    private static final String USER_ID_COLUMN = "user_id";

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
    public void update(User user, SubscriptionType subscriptionType) {

        subscriptionDAO.update(START_SUBSCRIPTION_DATE, new Date(), USER_ID_COLUMN, user.getId());

        subscriptionDAO.update(
                END_SUBSCRIPTION_DATE,
                DateUtils.addMonths(
                        new Date(),
                        Integer.parseInt(subscriptionType.getDescription().replaceAll("\\D+", ""))),
                USER_ID_COLUMN,
                user.getId());
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

    @Override
    public SubscriptionType findByType(String chosenType) {
        try {
            return subscriptionTypesDAO.findAll().stream()
                    .filter(subType -> (subType.getDescription() + ": " + subType.getPrice()).equals(chosenType))
                    .collect(Collectors.toList()).get(0);
        } catch (SQLException e) {
            e.printStackTrace(); //TODO: logger
        }
        return null;
    }

}
