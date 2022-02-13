package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.SubscriptionDAOImpl;
import by.epam.trainig.entity.user.Subscription;

import java.util.List;

public interface SubscriptionDAO extends EntityDAO<Subscription>{

    List<Subscription> findAll(int currentPage, int recordsOnPage);

    static SubscriptionDAO getInstance() {
        return SubscriptionDAOImpl.getInstance();
    }

}
