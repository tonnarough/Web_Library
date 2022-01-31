package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodAuthorDAO;
import by.epam.trainig.entity.book.Author;

import java.util.List;

public interface AuthorDAO extends EntityDAO<Author> {

    List<Author> findAuthorsByBookId(int id);

    static MethodAuthorDAO getInstance(){
        return MethodAuthorDAO.INSTANCE;
    }

}
