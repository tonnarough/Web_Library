package by.epam.trainig.service.impl;

import by.epam.trainig.dao.SubscriptionDAO;
import by.epam.trainig.dao.SubscriptionTypeDAO;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.DAOException;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.CommonService;
import by.epam.trainig.service.SubscriptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class SubscriptionServiceImpl extends CommonService<Subscription> implements SubscriptionService {

    private static final String FIND_SUBSCRIPTION_BY_PARAMETER = "user_id";

    private static final String START_SUBSCRIPTION_DATE = "start_date";
    private static final String END_SUBSCRIPTION_DATE = "end_date";

    private static final String USER_ID_COLUMN = "user_id";

    private static final Logger logger = LogManager.getLogger(SubscriptionServiceImpl.class);

    private final SubscriptionDAO subscriptionDAO;
    private final SubscriptionTypeDAO subscriptionTypeDAO;

    SubscriptionServiceImpl(SubscriptionDAO subscriptionDAO, SubscriptionTypeDAO subscriptionTypeDAO) {
        super(subscriptionDAO, logger);
        this.subscriptionDAO = subscriptionDAO;
        this.subscriptionTypeDAO = subscriptionTypeDAO;
    }

    public static SubscriptionService getInstance() {
        return SubscriptionServiceImpl.Holder.INSTANCE;
    }

    @Override
    public List<SubscriptionType> findAllTypes() {

        return subscriptionTypeDAO.findAll();

    }

    @Override
    public void update(User user, SubscriptionType subscriptionType) throws ServiceException {

        try {

            subscriptionDAO.update(
                    START_SUBSCRIPTION_DATE,
                    Date.valueOf(LocalDateTime
                            .now()
                            .format(DateTimeFormatter
                                    .ofPattern("yyyy-MM-dd"))),
                    USER_ID_COLUMN,
                    user.getId());


            subscriptionDAO.update(
                    END_SUBSCRIPTION_DATE,
                    Date.valueOf(LocalDateTime
                            .now()
                            .plusMonths(Long.parseLong(subscriptionType.getDescription().replaceAll("\\D+", "")))
                            .format(DateTimeFormatter
                                    .ofPattern("yyyy-MM-dd"))),
                    USER_ID_COLUMN,
                    user.getId());

        } catch (DAOException e) {

            logger.error("Filed subscription updating", e);
            throw new ServiceException(e);

        }

    }

    @Override
    public void create(Subscription entity) throws ServiceException {

        entity.setStartDate(Date
                .valueOf(LocalDateTime
                        .now()
                        .format(DateTimeFormatter
                                .ofPattern("yyyy-MM-dd"))));

        entity.setEndDate(Date
                .valueOf(LocalDateTime
                        .now()
                        .plusMonths(Long.parseLong(entity.getSubscriptionType().getDescription().replaceAll("\\D+", "")))
                        .format(DateTimeFormatter
                                .ofPattern("yyyy-MM-dd"))));

        super.create(entity);

    }

    @Override
    public Optional<Subscription> findByUserId(Integer id) {

        return super.findBy(FIND_SUBSCRIPTION_BY_PARAMETER, id);

    }

    @Override
    public SubscriptionType findByType(String chosenType) {

        return subscriptionTypeDAO
                .findAll()
                .stream()
                .filter(subType -> (subType.getDescription() + ": " + subType.getPrice()).equals(chosenType))
                .collect(Collectors.toList())
                .get(0);

    }

    private static class Holder {
        public static final SubscriptionService INSTANCE = new SubscriptionServiceImpl(
                SubscriptionDAO.getInstance(), SubscriptionTypeDAO.getInstance()
        );
    }

}
