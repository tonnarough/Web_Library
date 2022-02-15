package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.CommonDAO;
import by.epam.trainig.dao.CreditCardDAO;
import by.epam.trainig.entity.user.CreditCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class CreditCardDAOImpl extends CommonDAO<CreditCard> implements CreditCardDAO {

    private static final Logger logger = LogManager.getLogger(CreditCardDAOImpl.class);

    private static final String TABLE_MANY_TO_MANY = "bank_account";

    private static final Class<CreditCard> entityClass = CreditCard.class;
    private static final Table tableCreditCardName = entityClass.getAnnotation(Table.class);
    private static final List<String> columnCreditCardNames = DatabaseEntityContext.getDatabaseEntityContext()
            .getTableColumn(tableCreditCardName.name());
    private final List<String> creditCardsUsersColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getManyToManyColumn(tableCreditCardName.name());

    private CreditCardDAOImpl() {
        super(logger, columnCreditCardNames, tableCreditCardName);
    }

    public static CreditCardDAO getInstance() {
        return CreditCardDAOImpl.Holder.INSTANCE;
    }

    public List<String> getCreditCardUserColumns() {
        return creditCardsUsersColumns;
    }

    @Override
    public List<CreditCard> findCreditCardByUserId(int userId, String userTableName, List<String> userColumns) {

        return executeStatementForEntities(
                findByManyToManyQuery(tableCreditCardName.name(), userTableName, TABLE_MANY_TO_MANY,
                        columnCreditCardNames.get(0), creditCardsUsersColumns.get(0), userColumns.get(0), userId),
                this::extractResultCatchingException,
                null);

    }

    @Override
    protected CreditCard extractResult(ResultSet rs) throws SQLException {

        return new CreditCard(
        rs.getInt(columnCreditCardNames.get(0)),
        rs.getString(columnCreditCardNames.get(1)),
        rs.getString(columnCreditCardNames.get(2)),
        rs.getDate(columnCreditCardNames.get(3)),
        rs.getInt(columnCreditCardNames.get(4)),
        rs.getBigDecimal(columnCreditCardNames.get(5))
        );

    }

    @Override
    protected void fillEntity(PreparedStatement statement, CreditCard entity) throws SQLException {

        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getCreditCardNumber());
        statement.setString(3, entity.getCardholderName());
        statement.setDate(4, entity.getCardExpirationDate());
        statement.setInt(5, entity.getCVV());
        statement.setBigDecimal(6, entity.getBalance());

    }

    private static class Holder {
        public static final CreditCardDAO INSTANCE = new CreditCardDAOImpl();
    }

}
