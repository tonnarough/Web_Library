package by.epam.trainig.service;

import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.impl.BankAccountServiceImpl;

import java.util.Optional;

public interface BankAccountService extends EntityService<CreditCard> {

    void updateCreditCard(String updColumn, Object updValue, String whereColumn, Object whereValue);

    void create(User user, CreditCard creditCard) throws ServiceException;

    Optional<CreditCard> findCreditCardBy(String columnName, Object value);

    static BankAccountServiceImpl getInstance(){
        return BankAccountServiceImpl.INSTANCE;
    }
}
