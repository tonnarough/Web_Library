package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.CreditCardDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.CreditCard;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum MethodCreditCardDAO implements CreditCardDAO {
    INSTANCE;

    private final QueryOperation queryOperation = QueryOperation.getInstance();

    private final Class<CreditCard> creditCardClass = CreditCard.class;
    private final Table tableCreditCardClass = creditCardClass.getAnnotation(Table.class);
    private final List<String> creditCardColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableCreditCardClass.name());

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        queryOperation.update(tableCreditCardClass, updColumn, updValue, whereColumn, whereValue);
    }

    @Override
    public List<CreditCard> findAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(String column, Object values) {

    }

    @Override
    public void create(CreditCard creditCard) {

        queryOperation.create(creditCardColumnNames, tableCreditCardClass, creditCard, CreditCard.class);
    }

    @Override
    public Optional<CreditCard> findBy(String columnName, Object value) {

        return queryOperation.findBy(tableCreditCardClass, creditCardColumnNames.get(creditCardColumnNames.indexOf(String.format("%s", columnName))),
                value, CreditCard.class);
    }
}
