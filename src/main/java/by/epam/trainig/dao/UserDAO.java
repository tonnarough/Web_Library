package by.epam.trainig.dao;

import by.epam.trainig.entity.user.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDAO extends EntityDAO<User> {

    Optional<User> findUserById(Integer id);

    Optional<User> findByEmail(String email);

}
