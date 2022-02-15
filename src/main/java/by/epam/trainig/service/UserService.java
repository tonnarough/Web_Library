package by.epam.trainig.service;

import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.impl.UserServiceImpl;

import java.util.Optional;

public interface UserService extends EntityService<User> {

    Optional<User> authenticate(String login, String password);

    void updatePassword(String updColumn, String password, String whereColumn, Object whereValue) throws ServiceException;

    boolean verifyPassword(String confirmedPassword, String userPassword);

    void createCreditCard(User user, CreditCard creditCard) throws ServiceException;

    void registration(User user) throws ServiceException;

    boolean isExists(String login);

    static UserService getInstance(){
        return UserServiceImpl.getInstance();
    }

}
