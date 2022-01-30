package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodBookDAO;
import by.epam.trainig.entity.book.Book;

public interface BookDAO extends EntityDAO<Book> {

    static MethodBookDAO getInstance(){
        return MethodBookDAO.INSTANCE;
    }

}
