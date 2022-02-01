package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.UserDetailDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.entity.user.UserDetail;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public enum MethodUserDetailDAO implements UserDetailDAO {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger(MethodUserDAO.class);

    private final QueryOperation queryOperation = QueryOperation.getInstance();

    private final Class<UserDetail> userDetailClass = UserDetail.class;
    private final Table tableUserDetail = userDetailClass.getAnnotation(Table.class);
    private final List<String> userDetailColumnNames = DatabaseEntityContext
            .getDatabaseEntityContext().getDatabaseContext(tableUserDetail.name());

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        queryOperation.update(tableUserDetail, updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public List<UserDetail> findAll() throws DAOException {

        try {

            return queryOperation.findAll(tableUserDetail, UserDetail.class);

        } catch (SQLException e) {

            logger.error("Failed finding all subscription", e);
            throw new DAOException(e);
        }

    }

    @Override
    public void delete(UserDetail userDetail) {

        queryOperation.delete(tableUserDetail, userDetailColumnNames.get(0), userDetail);

    }

    @Override
    public void create(UserDetail entity) {

    }

    @Override
    public void create(UserDetail entity, Connection connection) {

        queryOperation.create(userDetailColumnNames, tableUserDetail, entity, UserDetail.class, connection);

    }

    @Override
    public Optional<UserDetail> findBy(String columnName, Object value) {

        return queryOperation.findBy(tableUserDetail, columnName, value, UserDetail.class);

    }

    @Override
    public Optional<UserDetail> findById(int id) {

        return findBy(userDetailColumnNames.get(0), id);

    }
}
