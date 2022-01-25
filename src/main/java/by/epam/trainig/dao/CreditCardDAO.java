package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodCreditCardDAO;
import by.epam.trainig.entity.user.BankAccount;
import by.epam.trainig.entity.user.CreditCard;

public interface CreditCardDAO extends EntityDAO<CreditCard> {

    static MethodCreditCardDAO getInstance() {
        return MethodCreditCardDAO.INSTANCE;
    }

}
