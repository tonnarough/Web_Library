package by.epam.trainig.service.impl;

import by.epam.trainig.dao.DAOFactory;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private DAOFactory daoFactory = DAOFactory.getInstance();

    public UserServiceImpl() {
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return daoFactory.entityDAO(User.class).findAll();
    }
}
