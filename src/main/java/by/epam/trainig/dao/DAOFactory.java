package by.epam.trainig.dao;

import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.dao.impl.MethodUserDAO;
import by.epam.trainig.entity.Entity;
import by.epam.trainig.entity.user.User;

public class DAOFactory implements EntityDAOFactory{

    private EntityDAO<User> userDAO;

    private DAOFactory() {
    }

    @Override
    public <T extends Entity> EntityDAO<T> entityDAO(Class<T> type) {
        EntityDAO<T> entityDAO = null;

        if (User.class.isAssignableFrom(type)) {
            if(userDAO == null){
                userDAO = new MethodUserDAO();
            }
            entityDAO = (EntityDAO<T>) userDAO;
        }
        return entityDAO;
    }

    public static DAOFactory getInstance(){
        return Holder.INSTANCE;
    }

    private static class Holder{
        private static final DAOFactory INSTANCE = new DAOFactory();
    }
}
