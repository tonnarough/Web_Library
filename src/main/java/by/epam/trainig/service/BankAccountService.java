package by.epam.trainig.service;

import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.service.impl.BankAccountServiceImpl;

import java.util.Optional;

public interface BankAccountService extends EntityService<CreditCard> {

    void update(String column1, Object value1, String column2, Object value2);

    Optional<CreditCard> findBy(String columnName, Object value);

    static BankAccountServiceImpl getInstance(){
        return BankAccountServiceImpl.INSTANCE;
    }
}
