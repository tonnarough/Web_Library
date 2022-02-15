package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.UserDAOImpl;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.DAOException;

import java.util.List;

public interface UserDAO extends EntityDAO<User> {

    List<User> findAll(int currentPage, int recordsOnPage);

    void create(User user, CreditCard creditCard) throws DAOException;

    static UserDAO getInstance() {
        return UserDAOImpl.getInstance();
    }

}
