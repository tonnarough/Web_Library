package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodBankAccountDAO;
import by.epam.trainig.entity.user.BankAccount;

public interface BankAccountDAO extends EntityDAO<BankAccount> {

    static MethodBankAccountDAO getInstance() {
        return MethodBankAccountDAO.INSTANCE;
    }

}
