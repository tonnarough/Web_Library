package by.epam.trainig.service;

import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.SubscriptionType;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.service.impl.BankAccountServiceImpl;
import liquibase.pro.packaged.T;

import java.util.Optional;

public interface BankAccountService extends EntityService<CreditCard> {

    void update(String updColumn, Object updValue, String whereColumn, Object whereValue);

    void create(User user, CreditCard creditCard);

    Optional<CreditCard> findBy(String columnName, Object value);

    static BankAccountServiceImpl getInstance(){
        return BankAccountServiceImpl.INSTANCE;
    }
}
