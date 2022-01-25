package by.epam.trainig.service.impl;

import by.epam.trainig.dao.BankAccountDAO;
import by.epam.trainig.dao.CreditCardDAO;
import by.epam.trainig.entity.user.*;
import by.epam.trainig.service.BankAccountService;

import java.util.List;
import java.util.Optional;

public enum BankAccountServiceImpl implements BankAccountService {
    INSTANCE(CreditCardDAO.getInstance(), BankAccountDAO.getInstance());

    private final CreditCardDAO creditCardDAO;
    private final BankAccountDAO bankAccountDAO;

    BankAccountServiceImpl(CreditCardDAO creditCardDAO, BankAccountDAO bankAccountDAO) {
        this.creditCardDAO = creditCardDAO;
        this.bankAccountDAO = bankAccountDAO;
    }

    @Override
    public List<CreditCard> findAll() {
        return null;
    }

    @Override
    public void create(CreditCard entity) {

    }

    @Override
    public void create(User user, CreditCard creditCard) {

        creditCardDAO.create(creditCard);
        bankAccountDAO.create(new BankAccount(user.getId(), creditCard.getId()));
    }

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        creditCardDAO.update(updColumn, updValue, whereColumn, whereValue);
    }

    @Override
    public Optional<CreditCard> findBy(String columnName, Object value) {

        return creditCardDAO.findBy(columnName, value);
    }
}
