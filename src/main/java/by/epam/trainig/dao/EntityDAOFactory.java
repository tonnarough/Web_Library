package by.epam.trainig.dao;

import by.epam.trainig.entity.Entity;

public interface EntityDAOFactory {

    <T extends Entity> EntityDAO<T> entityDAO(Class<T> type);

    static DAOFactory getInstance() {
        return DAOFactory.getInstance();
    }

}
