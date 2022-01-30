package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodCreditCardDAO;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.DAOException;

import java.util.Optional;

public interface CreditCardDAO extends EntityDAO<CreditCard> {

    void updateCreditCard(String updColumn, Object updValue, String whereColumn, Object whereValue);

    Optional<CreditCard> findCreditCardBy(String columnName, Object value);

    void create(User user, CreditCard creditCard) throws DAOException;

    static MethodCreditCardDAO getInstance() {
        return MethodCreditCardDAO.INSTANCE;
    }

}
