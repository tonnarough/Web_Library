package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.SubscriptionDAOImpl;
import by.epam.trainig.entity.user.Subscription;

public interface SubscriptionDAO extends EntityDAO<Subscription>{

    static SubscriptionDAO getInstance() {
        return SubscriptionDAOImpl.getInstance();
    }

}
