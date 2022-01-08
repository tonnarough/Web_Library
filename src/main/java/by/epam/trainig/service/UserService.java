package by.epam.trainig.service;

import by.epam.trainig.entity.user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getAllUsers() throws SQLException;

}
