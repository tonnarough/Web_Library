package by.epam.trainig.service;

import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.impl.UserServiceImpl;

import java.util.Optional;

public interface UserService extends EntityService<User> {

    Optional<User> authenticate(String login, String password);

    void createCreditCard(User user, CreditCard creditCard) throws ServiceException;

    void updateCreditCard(String updColumn, Object updValue, String whereColumn, String whereValue) throws ServiceException;

    Optional<CreditCard> findCreditCardBy(String findByValue, String creditCardNumber);

    void registration(User user) throws ServiceException;

    boolean isExists(String login);

    static UserServiceImpl getInstance(){
        return UserServiceImpl.INSTANCE;
    }

}
