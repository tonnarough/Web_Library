package by.epam.trainig.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.epam.trainig.dao.CreditCardDAO;
import by.epam.trainig.dao.UserDAO;
import by.epam.trainig.dao.impl.CreditCardDAOImpl;
import by.epam.trainig.entity.user.CreditCard;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.exception.DAOException;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static at.favre.lib.crypto.bcrypt.BCrypt.MIN_COST;

public enum UserServiceImpl implements UserService {
    INSTANCE(UserDAO.getInstance(), CreditCardDAO.getInstance(), BCrypt.withDefaults(), BCrypt.verifyer());

    private static final Logger logger = LogManager.getLogger(CreditCardDAOImpl.class);

    private static final String LOGIN = "login";

    private final UserDAO userDAO;
    private final CreditCardDAO creditCardDAO;
    private final BCrypt.Hasher hasher;
    private final BCrypt.Verifyer verifyer;

    UserServiceImpl(UserDAO userDAO, CreditCardDAO creditCardDAO, BCrypt.Hasher hasher, BCrypt.Verifyer verifyer) {
        this.userDAO = userDAO;
        this.creditCardDAO = creditCardDAO;
        this.hasher = hasher;
        this.verifyer = verifyer;
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
    public Optional<User> findBy(String columnName, Object value) {

            return userDAO.findBy(columnName, value);

    }

    @Override
    public List<User> findAllWhere(String column, Object value) {

        return userDAO.findAllWhere(column, value);

    }

    @Override
    public void create(User entity) throws ServiceException {

        try {

            userDAO.create(entity);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while creating user", e);
            throw new ServiceException("Sql exception occurred while creating user", e);
        }

    }

    @Override
    public int getNumberOfRows() {

        return userDAO.getCountOfRows();

    }

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) throws ServiceException {

        try {

            userDAO.update(updColumn, updValue, whereColumn, whereValue);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while updating user", e);
            throw new ServiceException("Sql exception occurred while updating user", e);
        }

    }

    @Override
    public void delete(User entity) throws ServiceException {

        try {

            userDAO.delete(entity.getId());

        } catch (DAOException e) {

            logger.error("Sql exception occurred while deleting user", e);
            throw new ServiceException("Sql exception occurred while deleting user", e);
        }

    }

    @Override
    public List<User> findAll(int currentPage, int recordOnPage) {

        return userDAO.findAll(currentPage, recordOnPage);

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

    @Override
    public void updateCreditCard(String updColumn, Object updValue, String whereColumn, String whereValue) throws ServiceException {

        try {

            creditCardDAO.update(updColumn, updValue, whereColumn, whereValue);

        } catch (DAOException e) {

            logger.error("Failed with credit card updating", e);
            throw new ServiceException(e);

        }

    }

    @Override
    public Optional<CreditCard> findCreditCardBy(String findByValue, String creditCardNumber) {

        return creditCardDAO.findBy(findByValue, creditCardNumber);

    }

}
