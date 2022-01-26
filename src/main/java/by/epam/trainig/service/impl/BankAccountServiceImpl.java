package by.epam.trainig.service.impl;

import by.epam.trainig.dao.BankAccountDAO;
import by.epam.trainig.entity.user.*;
import by.epam.trainig.service.BankAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum BankAccountServiceImpl implements BankAccountService {
    INSTANCE(BankAccountDAO.getInstance());

    private static final Logger logger = LogManager.getLogger(BankAccountServiceImpl.class);

    private final BankAccountDAO bankAccountDAO;

    BankAccountServiceImpl(BankAccountDAO bankAccountDAO) {
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

        try {
            bankAccountDAO.create(new BankAccount(user.getId(), creditCard.getId()), creditCard);
        } catch (SQLException e) {
            logger.error("Creating bank account $ credit card are denied", e);
        }
    }

    @Override
    public void updateCreditCard(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        bankAccountDAO.updateCreditCard(updColumn, updValue, whereColumn, whereValue);
    }

    @Override
    public Optional<CreditCard> findCreditCardBy(String columnName, Object value) {

        return bankAccountDAO.findCreditCardBy(columnName, value);
    }
}
