package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.CreditCardDAOImpl;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface CreditCardDAO extends EntityDAO<CreditCard> {

    List<String> getCreditCardUserColumns();

    List<CreditCard> findCreditCardByUserId(int userId, String userTableName, List<String> userColumnName);

    boolean create(CreditCard entity, Connection connection) throws DAOException;

    static CreditCardDAO getInstance() {
        return CreditCardDAOImpl.getInstance();
    }

}
