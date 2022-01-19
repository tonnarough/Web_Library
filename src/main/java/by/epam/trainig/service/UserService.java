package by.epam.trainig.service;

import by.epam.trainig.entity.user.User;
import by.epam.trainig.service.impl.UserServiceImpl;

import java.sql.Date;
import java.util.Optional;

public interface UserService extends EntityService<User> {

    Optional<User> authenticate(String login, String password);

    void registration(String login, String password, String lastName, String firstName,
                             String fatherName, String email, String mobile, Date birthday);

    boolean isExists(String login);

    static UserServiceImpl getInstance(){
        return UserServiceImpl.INSTANCE;
    }

}
