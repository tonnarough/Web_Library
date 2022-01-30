package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodAuthorDAO;
import by.epam.trainig.entity.book.Author;

public interface AuthorDAO extends EntityDAO<Author> {

    static MethodAuthorDAO getInstance(){
        return MethodAuthorDAO.INSTANCE;
    }

}
