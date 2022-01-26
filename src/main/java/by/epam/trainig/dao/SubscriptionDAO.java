package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodSubscriptionDAO;
import by.epam.trainig.entity.user.Subscription;
import by.epam.trainig.entity.user.SubscriptionType;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDAO extends EntityDAO<Subscription> {

    List<SubscriptionType> findAllTypes() throws SQLException;

    static MethodSubscriptionDAO getInstance() {
        return MethodSubscriptionDAO.INSTANCE;
    }

}
