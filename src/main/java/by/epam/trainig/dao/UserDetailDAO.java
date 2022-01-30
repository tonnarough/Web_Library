package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodUserDetailDAO;
import by.epam.trainig.entity.user.UserDetail;

import java.sql.Connection;

public interface UserDetailDAO extends EntityDAO<UserDetail> {

    void create(UserDetail entity, Connection connection);

    static MethodUserDetailDAO getInstance(){
        return MethodUserDetailDAO.INSTANCE;
    }

}
