package by.epam.trainig.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.epam.trainig.dao.UserDAO;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;
import by.epam.trainig.exception.DAOException;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static at.favre.lib.crypto.bcrypt.BCrypt.MIN_COST;

public enum UserServiceImpl implements UserService {
    INSTANCE(UserDAO.getInstance(), BCrypt.withDefaults(), BCrypt.verifyer());

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO;
    private final BCrypt.Hasher hasher;
    private final BCrypt.Verifyer verifyer;

    private static final String FIND_USER_BY_LOGIN_PARAMETER = "login";
    private static final String FIND_USER_BY_ID_PARAMETER = "id";


    UserServiceImpl(UserDAO userDAO, BCrypt.Hasher hasher, BCrypt.Verifyer verifyer) {
        this.userDAO = userDAO;
        this.hasher = hasher;
        this.verifyer = verifyer;
    }

    @Override
    public List<User> findAll() throws ServiceException {

        try {

            return userDAO.findAll();

        } catch (DAOException e) {

            logger.error("Users are not found", e);
            throw new ServiceException(e);

        }
    }

    @Override
    public void create(User entity) {
        userDAO.create(entity);
    }

    public Optional<User> authenticate(String login, String password) {

        logger.trace("Authorization");

        if (login == null || password == null) {

            return Optional.empty();

        }

        final Optional<User> user = userDAO.findBy(FIND_USER_BY_LOGIN_PARAMETER, login);
        final byte[] enteredPassword = password.getBytes(StandardCharsets.UTF_8);

        if (user.isPresent()) {

            final byte[] hashedPassword = user.get()
                    .getPassword()
                    .getBytes(StandardCharsets.UTF_8);

            return verifyer.verify(enteredPassword, hashedPassword).verified
                    ? user
                    : Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findBy(String columnName, Object value) {
        return userDAO.findBy(columnName, value);
    }

    @Override
    public void registration(String login, String password, String lastName, String firstName,
                             String fatherName, String email, String mobile, Date birthday) throws ServiceException {

        logger.trace("Registration");

        final char[] rawPassword = password.toCharArray();
        String hashedPassword = hasher.hashToString(MIN_COST, rawPassword);

        final User user = new User(login, hashedPassword);
        final UserDetail userDetail = new UserDetail(lastName, firstName, fatherName, email, mobile, birthday);

        try {
            userDAO.create(user, userDetail);
        } catch (DAOException e) {
            logger.error("Registration denied", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isExists(String login) {
        return userDAO.findBy(FIND_USER_BY_LOGIN_PARAMETER, login).isPresent();
    }

    @Override
    public Optional<UserDetail> findByUserId(int id) {

        return userDAO.findByUserDetail(FIND_USER_BY_ID_PARAMETER, id);

    }

}
