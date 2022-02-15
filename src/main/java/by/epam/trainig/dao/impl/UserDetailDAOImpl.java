package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.CommonDAO;
import by.epam.trainig.dao.UserDetailDAO;
import by.epam.trainig.entity.user.UserDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class UserDetailDAOImpl extends CommonDAO<UserDetail> implements UserDetailDAO {

    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);

    private static final Class<UserDetail> userDetailClass = UserDetail.class;
    private static final Table tableUserDetailName = userDetailClass.getAnnotation(Table.class);
    private static final List<String> columnUserDetailNames = DatabaseEntityContext
            .getDatabaseEntityContext().getTableColumn(tableUserDetailName.name());

    private UserDetailDAOImpl() {
        super(logger , columnUserDetailNames, tableUserDetailName);
    }

    public static UserDetailDAO getInstance() {
        return UserDetailDAOImpl.Holder.INSTANCE;
    }

    @Override
    protected UserDetail extractResult(ResultSet rs) throws SQLException {

        return new UserDetail(
                rs.getInt(columnUserDetailNames.get(0)),
                rs.getString(columnUserDetailNames.get(1)),
                rs.getString(columnUserDetailNames.get(2)),
                rs.getString(columnUserDetailNames.get(3)),
                rs.getString(columnUserDetailNames.get(4)),
                rs.getString(columnUserDetailNames.get(5)),
                rs.getDate(columnUserDetailNames.get(6))
        );
    }

    @Override
    protected void fillEntity(PreparedStatement statement, UserDetail entity) throws SQLException {

        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getLastName());
        statement.setString(3, entity.getFirstName());
        statement.setString(4, entity.getFatherName());
        statement.setString(5, entity.getEmail());
        statement.setString(6, entity.getMobile());
        statement.setDate(7, entity.getBirthday());

    }

    private static class Holder {
        public static final UserDetailDAO INSTANCE = new UserDetailDAOImpl();
    }

}
