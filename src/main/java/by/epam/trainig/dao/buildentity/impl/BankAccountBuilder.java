package by.epam.trainig.dao.buildentity.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.entity.user.CreditCard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BankAccountBuilder implements EntityBuilder<CreditCard> {

    private final Class<CreditCard> entityClass = CreditCard.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private final List<String> creditCardColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getDatabaseContext(tableName.name());

    @Override
    public CreditCard buildEntity(ResultSet resultSet) throws SQLException {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(resultSet.getInt(creditCardColumns.get(0)));
        creditCard.setCreditCardNumber(resultSet.getString(creditCardColumns.get(1)));
        creditCard.setCardholderName(resultSet.getString(creditCardColumns.get(2)));
        creditCard.setCardExpirationDate(resultSet.getDate(creditCardColumns.get(3)));
        creditCard.setCVV(resultSet.getInt(creditCardColumns.get(4)));
        creditCard.setBalance(resultSet.getBigDecimal(creditCardColumns.get(5)));
        return creditCard;
    }

    @Override
    public void buildResultSetByEntity(PreparedStatement preparedStatement, CreditCard entity) throws SQLException {
        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setString(2, entity.getCreditCardNumber());
        preparedStatement.setString(3, entity.getCardholderName());
        preparedStatement.setDate(4, entity.getCardExpirationDate());
        preparedStatement.setInt(5, entity.getCVV());
        preparedStatement.setBigDecimal(6, entity.getBalance());
    }
}
