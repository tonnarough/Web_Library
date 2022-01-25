package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.BankAccountDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.BankAccount;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum MethodBankAccountDAO implements BankAccountDAO {
    INSTANCE;

    private final QueryOperation queryOperation = QueryOperation.getInstance();

    private final Class<BankAccount> bankAccountClass = BankAccount.class;
    private final Table tableBankAccountClass = bankAccountClass.getAnnotation(Table.class);
    private final List<String> bankAccountColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableBankAccountClass.name());

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

    }

    @Override
    public List<BankAccount> findAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(String column, Object values) {

    }

    @Override
    public void create(BankAccount bankAccount) {

        queryOperation.create(bankAccountColumnNames, tableBankAccountClass, bankAccount, BankAccount.class);
    }

    @Override
    public Optional<BankAccount> findBy(String columnName, Object value) {
        return Optional.empty();
    }
}
