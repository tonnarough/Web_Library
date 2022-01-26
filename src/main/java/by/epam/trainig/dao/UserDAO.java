package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodUserDAO;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDAO extends EntityDAO<User> {

    void create(User user, UserDetail userDetail) throws SQLException;

    Optional<UserDetail> findByUserDetail(String columnName, Object value);

    static MethodUserDAO getInstance() {
        return MethodUserDAO.INSTANCE;
    }

}
