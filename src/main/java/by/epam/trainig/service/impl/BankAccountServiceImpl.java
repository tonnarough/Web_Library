package by.epam.trainig.service.impl;

import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.EntityDAOFactory;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.service.BankAccountService;

import java.util.List;
import java.util.Optional;

public enum BankAccountServiceImpl implements BankAccountService {
    INSTANCE;

    private final EntityDAO<CreditCard> creditCardEntityDAO = EntityDAOFactory.getInstance().entityDAO(CreditCard.class);

    @Override
    public List<CreditCard> findAll() {
        return null;
    }

    @Override
    public void create(CreditCard entity) {

        creditCardEntityDAO.create(entity);
    }

    @Override
    public void update(String column1, Object value1, String column2, Object value2) {

        creditCardEntityDAO.update(column1, value1, column2, value2);
    }

    @Override
    public Optional<CreditCard> findBy(String columnName, Object value) {

        return creditCardEntityDAO.findBy(columnName, value);
    }
}
