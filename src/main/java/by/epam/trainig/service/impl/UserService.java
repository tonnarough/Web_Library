package by.epam.trainig.service.impl;

import by.epam.trainig.dao.impl.MethodUserDAO;
import by.epam.trainig.entity.user.User;
import by.epam.trainig.service.EntityService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService implements EntityService<User> {

    private final MethodUserDAO methodUserDAO = new MethodUserDAO();

    @Override
    public List<User> findAll() throws SQLException {
        return methodUserDAO.findAll();
    }

    @Override
    public void create(User entity) {
        methodUserDAO.create(entity);
    }

    public Optional<User> authenticate(String login, String password) {

        final Optional<User> user = methodUserDAO.findByLogin(login);

        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                return user;
            }else{
                return Optional.empty();
            }
        }
            return Optional.empty();
    }
}
