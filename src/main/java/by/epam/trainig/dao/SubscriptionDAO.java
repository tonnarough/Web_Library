package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodSubscriptionDAO;
import by.epam.trainig.entity.user.Subscription;

public interface SubscriptionDAO extends EntityDAO<Subscription> {

    static MethodSubscriptionDAO getInstance() {
        return MethodSubscriptionDAO.INSTANCE;
    }

}
