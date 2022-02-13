package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.SubscriptionTypeDAOImpl;
import by.epam.trainig.entity.user.SubscriptionType;

public interface SubscriptionTypeDAO extends EntityDAO<SubscriptionType> {

    static SubscriptionTypeDAO getInstance() {
        return SubscriptionTypeDAOImpl.getInstance();
    }

}
