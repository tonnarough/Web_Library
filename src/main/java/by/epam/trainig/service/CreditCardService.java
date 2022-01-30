package by.epam.trainig.service;

import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.impl.CreditCardServiceImpl;

import java.util.Optional;

public interface BankAccountService extends EntityService<CreditCard> {

    Optional<CreditCard> findCreditCardBy(String findByValue, String creditCardNumber);

    void create(User user, CreditCard entity) throws ServiceException;

    void updateCreditCard(String updColumn, Object updValue, String whereColumn, String whereValue);

    static CreditCardServiceImpl getInstance(){
        return CreditCardServiceImpl.INSTANCE;
    }

}
