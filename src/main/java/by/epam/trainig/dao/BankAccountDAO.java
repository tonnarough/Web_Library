package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodBankAccountDAO;
import by.epam.trainig.entity.user.BankAccount;
import by.epam.trainig.entity.user.CreditCard;

import java.sql.SQLException;
import java.util.Optional;

public interface BankAccountDAO extends EntityDAO<BankAccount> {

    void updateCreditCard(String updColumn, Object updValue, String whereColumn, Object whereValue);

    Optional<CreditCard> findCreditCardBy(String columnName, Object value);

    void create(BankAccount bankAccount, CreditCard creditCard) throws SQLException;

    static MethodBankAccountDAO getInstance() {
        return MethodBankAccountDAO.INSTANCE;
    }

}
