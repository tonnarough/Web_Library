package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodUserDetailDAO;
import by.epam.trainig.entity.user.UserDetail;

public interface UserDetailDAO extends EntityDAO<UserDetail> {

    static MethodUserDetailDAO getInstance() {
        return MethodUserDetailDAO.INSTANCE;
    }

}
