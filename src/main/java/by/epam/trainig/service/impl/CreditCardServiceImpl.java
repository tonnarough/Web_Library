package by.epam.trainig.service.impl;

import by.epam.trainig.dao.CreditCardDAO;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.DAOException;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.CreditCardService;

import java.util.List;
import java.util.Optional;

public enum CreditCardServiceImpl implements CreditCardService {
    INSTANCE(CreditCardDAO.getInstance());

    private final CreditCardDAO creditCardDAO;

    CreditCardServiceImpl(CreditCardDAO creditCardDAO) {
        this.creditCardDAO = creditCardDAO;
    }

    @Override
    public List<CreditCard> findAll() throws ServiceException {
        return null;
    }

    @Override
    public void create(CreditCard entity) {

    }

    @Override
    public void create(User user, CreditCard entity) throws ServiceException {

        try {
            creditCardDAO.create(user, entity);
        } catch (DAOException e) {

            //TODO logger
            throw new ServiceException(e);

        }

    }

    @Override
    public void updateCreditCard(String updColumn, Object updValue, String whereColumn, String whereValue) {

        creditCardDAO.updateCreditCard(updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public Optional<CreditCard> findCreditCardBy(String findByValue, String creditCardNumber) {

        return creditCardDAO.findCreditCardBy(findByValue, creditCardNumber);

    }
}
