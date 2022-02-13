package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.RoleDAOImpl;
import by.epam.trainig.entity.user.Role;

public interface RoleDAO extends EntityDAO<Role>{

    static RoleDAO getInstance() {
        return RoleDAOImpl.getInstance();
    }

}
