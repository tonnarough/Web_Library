package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodBookDAO;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface BookDAO extends EntityDAO<Book> {

    List<Book> findByParameter(String column, String parameter);

    List<Book> findAllBooks(int currentPage, int recordsOnPage);

    int getNumberOfRows();

    Optional<Book> findBookWithAuthorGenrePublishingHouseById(int id) throws DAOException;

    static MethodBookDAO getInstance(){
        return MethodBookDAO.INSTANCE;
    }

}
