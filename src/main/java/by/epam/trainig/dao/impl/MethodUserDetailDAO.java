package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.queryoperation.Impl.QueryOperationImpl;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MethodUserDetailDAO implements EntityDAO<UserDetail> {

    private final Class<UserDetail> userDetailClass = UserDetail.class;
    private final Table tableName = userDetailClass.getAnnotation(Table.class);
    private final QueryOperation queryOperation = QueryOperation.getInstance();
    private final List<String> userDetailColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableName.name());

    @Override
    public void update(String column1, Object value1, String column2, Object value2) {

    }

    @Override
    public List<UserDetail> findAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(String column, Object values) {

    }

    @Override
    public void create(UserDetail entity) {

        queryOperation.create(userDetailColumnNames, tableName, entity, UserDetail.class);
    }

    @Override
    public Optional<UserDetail> findBy(String columnName, Object value) {
        return queryOperation.findBy(tableName, userDetailColumnNames.get(userDetailColumnNames.indexOf(String.format("%s", columnName))),
                value, UserDetail.class);
    }
}
