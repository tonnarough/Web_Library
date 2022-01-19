package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodUserDAO;
import by.epam.trainig.dao.impl.MethodUserDetailDAO;
import by.epam.trainig.entity.Entity;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;

public class DAOFactory implements EntityDAOFactory {

    private EntityDAO<User> userDAO;
    private EntityDAO<UserDetail> userDetailDAO;

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
        }if (UserDetail.class.isAssignableFrom(type)) {
            if (userDetailDAO == null) {
                userDetailDAO = new MethodUserDetailDAO();
            }
            entityDAO = (EntityDAO<T>) userDetailDAO;
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
