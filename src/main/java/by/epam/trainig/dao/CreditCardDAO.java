package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.CreditCardDAOImpl;
import by.epam.trainig.entity.user.CreditCard;
import java.util.List;

public interface CreditCardDAO extends EntityDAO<CreditCard> {

    List<String> getCreditCardUserColumns();

    List<CreditCard> findCreditCardByUserId(int userId, String userTableName, List<String> userColumnName);

    static CreditCardDAO getInstance() {
        return CreditCardDAOImpl.getInstance();
    }

}
