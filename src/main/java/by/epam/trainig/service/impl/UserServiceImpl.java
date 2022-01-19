package by.epam.trainig.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.epam.trainig.dao.impl.MethodUserDAO;
import by.epam.trainig.dao.impl.MethodUserDetailDAO;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;
import by.epam.trainig.service.EntityService;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static at.favre.lib.crypto.bcrypt.BCrypt.MIN_COST;

public class UserService implements EntityService<User> {

    private final MethodUserDAO methodUserDAO = new MethodUserDAO();
    private final MethodUserDetailDAO methodUserDetailDAO = new MethodUserDetailDAO();

    private final BCrypt.Hasher hasher = BCrypt.withDefaults();
    private final BCrypt.Verifyer verifyer = BCrypt.verifyer();

    @Override
    public List<User> findAll() throws SQLException {
        return methodUserDAO.findAll();
    }

    @Override
    public void create(User entity) {
        methodUserDAO.create(entity);
    }

    public Optional<User> authenticate(String login, String password) {

        if (login == null || password == null) {
            return Optional.empty();
        }

        final Optional<User> user = methodUserDAO.findByLogin(login);
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

    public void registration(String login, String password, String lastName, String firstName,
                             String fatherName, String email, String mobile, Date birthday) {

        final char[] rawPassword = password.toCharArray();
        String hashedPassword = hasher.hashToString(MIN_COST,rawPassword);

        final User user = new User(login, hashedPassword);
        final UserDetail userDetail = new UserDetail(lastName, firstName, fatherName, email, mobile, birthday);

        try {
            methodUserDAO.create(user, userDetail);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isExists(String login) {
        Optional<User> user = methodUserDAO.findByLogin(login);
        return user.isPresent();
    }
}
