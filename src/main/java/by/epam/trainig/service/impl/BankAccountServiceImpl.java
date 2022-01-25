package by.epam.trainig.service.impl;

import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.EntityDAOFactory;
import by.epam.trainig.entity.user.*;
import by.epam.trainig.service.BankAccountService;
import liquibase.repackaged.org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public enum BankAccountServiceImpl implements BankAccountService {
    INSTANCE;

    private final EntityDAO<CreditCard> creditCardEntityDAO = EntityDAOFactory.getInstance().entityDAO(CreditCard.class);
    private final EntityDAO<User> subscriptionEntityDAO = EntityDAOFactory.getInstance().entityDAO(Subscription.class);

    @Override
    public List<CreditCard> findAll() {
        return null;
    }

    @Override
    public void create(CreditCard entity) {

    }

    @Override
    public void create(User user, CreditCard creditCard) {

        creditCardEntityDAO.create(creditCard, new BankAccount(user.getId(),creditCard.getId()));
    }

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        creditCardEntityDAO.update(updColumn, updValue, whereColumn, whereValue);
    }

    @Override
    public Optional<CreditCard> findBy(String columnName, Object value) {

        return creditCardEntityDAO.findBy(columnName, value);
    }
}
