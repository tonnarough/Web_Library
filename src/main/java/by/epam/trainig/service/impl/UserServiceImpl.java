package by.epam.trainig.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.epam.trainig.dao.*;
import by.epam.trainig.dao.impl.CreditCardDAOImpl;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.DAOException;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.CommonService;
import by.epam.trainig.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static at.favre.lib.crypto.bcrypt.BCrypt.MIN_COST;

public final class UserServiceImpl extends CommonService<User> implements UserService {

    private static final Logger logger = LogManager.getLogger(CreditCardDAOImpl.class);

    private static final String LOGIN = "login";

    private final UserDAO userDAO;
    private final BCrypt.Hasher hasher;
    private final BCrypt.Verifyer verifyer;

    UserServiceImpl(UserDAO userDAO, BCrypt.Hasher hasher, BCrypt.Verifyer verifyer) {
        super(userDAO, logger);
        this.userDAO = userDAO;
        this.hasher = hasher;
        this.verifyer = verifyer;
    }

    public static UserService getInstance() {
        return UserServiceImpl.Holder.INSTANCE;
    }

    @Override
    public Optional<User> authenticate(String login, String password) {

        final Optional<User> user = userDAO.findBy(LOGIN, login);
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
    public void updatePassword(String updColumn, String password, String whereColumn, Object whereValue) throws ServiceException {

        final char[] rawPassword = password.toCharArray();

        final String newPassword = hasher.hashToString(MIN_COST, rawPassword);

        try {

            userDAO.update(updColumn, newPassword, whereColumn, whereValue);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while updating password", e);
            throw new ServiceException("Sql exception occurred while updating password", e);
        }

    }

    @Override
    public boolean verifyPassword(String confirmedPassword, String userPassword) {

        final byte[] enteredPassword = confirmedPassword.getBytes(StandardCharsets.UTF_8);

            final byte[] hashedPassword = userPassword.getBytes(StandardCharsets.UTF_8);

            return verifyer.verify(enteredPassword, hashedPassword).verified;

    }

    @Override
    public void registration(User user) throws ServiceException {

        final char[] rawPassword = user.getPassword().toCharArray();

        user.setPassword(hasher.hashToString(MIN_COST, rawPassword));

        try {

            userDAO.create(user);

        } catch (DAOException e) {

            logger.error("Registration denied", e);
            throw new ServiceException(e);
        }

    }

    @Override
    public boolean isExists(String login) {

        return userDAO.findBy(LOGIN, login).isPresent();

    }

    @Override
    public void createCreditCard(User user, CreditCard creditCard) throws ServiceException {

        try {
            userDAO.create(user, creditCard);
        } catch (DAOException e) {

            logger.error("Failed with creating creditCard", e);
            throw new ServiceException(e);

        }

    }

    private static class Holder {
        public static final UserService INSTANCE = new UserServiceImpl(
                UserDAO.getInstance(), BCrypt.withDefaults(), BCrypt.verifyer()
        );
    }

}
