package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.CommonDAO;
import by.epam.trainig.dao.SubscriptionTypeDAO;
import by.epam.trainig.entity.user.SubscriptionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubscriptionTypeDAOImpl extends CommonDAO<SubscriptionType> implements SubscriptionTypeDAO {

    private static final Logger logger = LogManager.getLogger(SubscriptionTypeDAOImpl.class);

    private static final Class<SubscriptionType> entityClass = SubscriptionType.class;
    private static final Table tableSubscriptionTypeName = entityClass.getAnnotation(Table.class);
    private static final List<String> columnSubscriptionTypeNames = DatabaseEntityContext.getDatabaseEntityContext()
            .getTableColumn(tableSubscriptionTypeName.name());

    private SubscriptionTypeDAOImpl() {
        super(logger, columnSubscriptionTypeNames, tableSubscriptionTypeName);
    }

    public static SubscriptionTypeDAO getInstance() {
        return SubscriptionTypeDAOImpl.Holder.INSTANCE;
    }

    @Override
    protected SubscriptionType extractResult(ResultSet rs) throws SQLException {

        return new SubscriptionType(
                rs.getInt(columnSubscriptionTypeNames.get(0)),
                rs.getString(columnSubscriptionTypeNames.get(1)),
                rs.getBigDecimal(columnSubscriptionTypeNames.get(2))
        );

    }

    @Override
    protected void fillEntity(PreparedStatement statement, SubscriptionType entity) throws SQLException {

        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getDescription());
        statement.setBigDecimal(3, entity.getPrice());

    }

    private static class Holder {
        public static final SubscriptionTypeDAO INSTANCE = new SubscriptionTypeDAOImpl();
    }

}
