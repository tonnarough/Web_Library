package by.epam.trainig.dao.buildentity.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.buildentity.EntityBuilder;
import by.epam.trainig.entity.user.BankAccount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BankAccountBuilder implements EntityBuilder<BankAccount> {

    private final Class<BankAccount> entityClass = BankAccount.class;
    private final Table tableName = entityClass.getAnnotation(Table.class);
    private final List<String> bankAccountColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getDatabaseContext(tableName.name());

    @Override
    public BankAccount buildEntity(ResultSet resultSet) throws SQLException {
        BankAccount creditCard = new BankAccount();
        creditCard.setUserId(resultSet.getInt(bankAccountColumns.get(0)));
        creditCard.setCreditCardId(resultSet.getInt(bankAccountColumns.get(1)));
        return creditCard;
    }

    @Override
    public void buildResultSetByEntity(PreparedStatement preparedStatement, BankAccount entity) throws SQLException {
        preparedStatement.setInt(1, entity.getUserId());
        preparedStatement.setInt(2, entity.getCreditCardId());
    }

}
