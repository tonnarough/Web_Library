package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.CreditCard;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MethodCreditCardDAO implements EntityDAO<CreditCard> {

    private final Class<CreditCard> creditCardClass = CreditCard.class;
    private final Table tableCreditCardClass = creditCardClass.getAnnotation(Table.class);
    private final QueryOperation queryOperation = QueryOperation.getInstance();
    private final List<String> creditCardColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableCreditCardClass.name());

    @Override
    public void update(String column1, Object value1, String column2, Object value2) {

        queryOperation.update(tableCreditCardClass, column1, value1, column2, value2);
    }

    @Override
    public List<CreditCard> findAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(String column, Object values) {

    }

    @Override
    public void create(CreditCard entity) {

        queryOperation.create(creditCardColumnNames, tableCreditCardClass, entity, CreditCard.class);
    }

    @Override
    public Optional<CreditCard> findBy(String columnName, Object value) {

        return queryOperation.findBy(tableCreditCardClass, creditCardColumnNames.get(creditCardColumnNames.indexOf(String.format("%s", columnName))),
                value, CreditCard.class);
    }
}
