package by.epam.trainig.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.EntityDAOFactory;
import by.epam.trainig.dao.impl.MethodUserDAO;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;
import by.epam.trainig.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static at.favre.lib.crypto.bcrypt.BCrypt.MIN_COST;

public enum UserServiceImpl implements UserService {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final BCrypt.Hasher hasher = BCrypt.withDefaults();
    private final BCrypt.Verifyer verifyer = BCrypt.verifyer();
    private final EntityDAO<User> userDAO = EntityDAOFactory.getInstance().entityDAO(User.class);
    private static final String FIND_USER_BY_PARAMETER = "login";

    @Override
    public List<User> findAll() {
        try {
            return userDAO.findAll();
        } catch (SQLException e) {
            logger.error("Users are not found", e);
        }
        return null;
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

        final Optional<User> user = userDAO.findBy(FIND_USER_BY_PARAMETER, login);
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
                             String fatherName, String email, String mobile, Date birthday) {

        logger.trace("Registration");

        final char[] rawPassword = password.toCharArray();
        String hashedPassword = hasher.hashToString(MIN_COST, rawPassword);

        final User user = new User(login, hashedPassword);
        final UserDetail userDetail = new UserDetail(lastName, firstName, fatherName, email, mobile, birthday);

        try {
            userDAO.create(user, userDetail);
        } catch (SQLException e) {
            logger.error("Registration denied", e);
        }
    }

    @Override
    public boolean isExists(String login) {
        return userDAO.findBy(FIND_USER_BY_PARAMETER, login).isPresent();
    }

}
