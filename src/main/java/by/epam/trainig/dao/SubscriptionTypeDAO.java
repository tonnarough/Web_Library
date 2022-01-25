package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodSubscriptionTypeDAO;
import by.epam.trainig.entity.user.SubscriptionType;

public interface SubscriptionTypeDAO extends EntityDAO<SubscriptionType> {

    static MethodSubscriptionTypeDAO getInstance() {
        return MethodSubscriptionTypeDAO.INSTANCE;
    }

}
