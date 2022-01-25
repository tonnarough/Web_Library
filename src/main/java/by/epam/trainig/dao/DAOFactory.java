package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.*;
import by.epam.trainig.entity.Entity;
import by.epam.trainig.entity.user.*;

public class DAOFactory implements EntityDAOFactory {

    private EntityDAO<User> userDAO;
    private EntityDAO<UserDetail> userDetailDAO;
    private EntityDAO<Subscription> subscriptionEntityDAO;
    private EntityDAO<SubscriptionType> subscriptionTypeEntityDAO;
    private EntityDAO<CreditCard> creditCardEntityDAO;

    private DAOFactory() {
    }

    @Override
    public <T extends Entity> EntityDAO<T> entityDAO(Class<T> type) {
        EntityDAO<T> entityDAO = null;

        if (User.class.isAssignableFrom(type)) {
            if (userDAO == null) {
                userDAO = new MethodUserDAO();
            }
            entityDAO = (EntityDAO<T>) userDAO;
        }else if (UserDetail.class.isAssignableFrom(type)) {
            if (userDetailDAO == null) {
                userDetailDAO = new MethodUserDetailDAO();
            }
            entityDAO = (EntityDAO<T>) userDetailDAO;
        }else if (Subscription.class.isAssignableFrom(type)) {
            if (subscriptionEntityDAO == null) {
                subscriptionEntityDAO = new MethodSubscriptionDAO();
            }
            entityDAO = (EntityDAO<T>) subscriptionEntityDAO;
        }else if (SubscriptionType.class.isAssignableFrom(type)) {
            if (subscriptionTypeEntityDAO == null) {
                subscriptionTypeEntityDAO = new MethodSubscriptionTypeDAO();
            }
            entityDAO = (EntityDAO<T>) subscriptionTypeEntityDAO;
        }else if (CreditCard.class.isAssignableFrom(type)) {
            if (creditCardEntityDAO == null) {
                creditCardEntityDAO = new MethodCreditCardDAO();
            }
            entityDAO = (EntityDAO<T>) creditCardEntityDAO;
        }
        return entityDAO;
    }

    public static DAOFactory getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final DAOFactory INSTANCE = new DAOFactory();
    }
}
