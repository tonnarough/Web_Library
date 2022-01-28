package by.epam.trainig.service;

import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.impl.UserServiceImpl;

import java.sql.Date;
import java.util.Optional;

public interface UserService extends EntityService<User> {

    Optional<User> authenticate(String login, String password);

    Optional<User> findBy(String columnName, Object value);

    void registration(String login, String password, String lastName, String firstName,
                             String fatherName, String email, String mobile, Date birthday) throws ServiceException;

    boolean isExists(String login);

    Optional<UserDetail> findByUserId(int id);

    static UserServiceImpl getInstance(){
        return UserServiceImpl.INSTANCE;
    }

}
