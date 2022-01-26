package by.epam.trainig.service.impl;

import by.epam.trainig.dao.SubscriptionDAO;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.service.SubscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum SubscriptionServiceImpl implements SubscriptionService {
    INSTANCE(SubscriptionDAO.getInstance());

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final SubscriptionDAO subscriptionDAO;
    private static final String FIND_SUBSCRIPTION_BY_PARAMETER = "user_id";

    private static final String START_SUBSCRIPTION_DATE = "start_date";
    private static final String END_SUBSCRIPTION_DATE = "end_date";

    private static final String USER_ID_COLUMN = "user_id";

    SubscriptionServiceImpl(SubscriptionDAO subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }

    @Override
    public List<SubscriptionType> findAllTypes() {
        try {
            return subscriptionDAO.findAllTypes();
        } catch (SQLException e) {
            logger.error("Subscription types are not found", e);
        }
        return null;
    }

    @Override
    public void update(User user, SubscriptionType subscriptionType) {

        subscriptionDAO.update(
                START_SUBSCRIPTION_DATE,
                Date.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
                USER_ID_COLUMN, user.getId());

        subscriptionDAO.update(
                END_SUBSCRIPTION_DATE,
                Date
                  .valueOf(LocalDateTime
                                .now()
                                .plusMonths(Long.parseLong(subscriptionType.getDescription().replaceAll("\\D+", "")))
                                .format(DateTimeFormatter
                                        .ofPattern("yyyy-MM-dd"))),
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
    public void create(Subscription entity, SubscriptionType subscriptionType) {

        entity.setStartDate(Date.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        entity.setEndDate(Date
                .valueOf(LocalDateTime
                        .now()
                        .plusMonths(Long.parseLong(subscriptionType.getDescription().replaceAll("\\D+", "")))
                        .format(DateTimeFormatter
                                .ofPattern("yyyy-MM-dd"))));

        subscriptionDAO.create(entity);
    }

    @Override
    public Optional<Subscription> findByUserId(Integer id) {
        return subscriptionDAO.findBy(FIND_SUBSCRIPTION_BY_PARAMETER, id);
    }

    @Override
    public SubscriptionType findByType(String chosenType) {
        try {
            return subscriptionDAO.findAllTypes().stream()
                    .filter(subType -> (subType.getDescription() + ": " + subType.getPrice()).equals(chosenType))
                    .collect(Collectors.toList()).get(0);
        } catch (SQLException e) {
            e.printStackTrace(); //TODO: logger
        }
        return null;
    }

}
