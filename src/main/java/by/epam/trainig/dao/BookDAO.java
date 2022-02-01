package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodBookDAO;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;

import java.util.List;

public interface BookDAO extends EntityDAO<Book> {

    List<Book> findByParameter(String column, String parameter);

    List<Book> findAllBooks(int currentPage, int recordsOnPage);

    int getNumberOfRows();

    static MethodBookDAO getInstance(){
        return MethodBookDAO.INSTANCE;
    }

}
