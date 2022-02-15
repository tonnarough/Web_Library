package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.UserDetailDAOImpl;
import by.epam.trainig.entity.user.UserDetail;

public interface UserDetailDAO extends EntityDAO<UserDetail> {

    static UserDetailDAO getInstance() {
        return UserDetailDAOImpl.getInstance();
    }

}
