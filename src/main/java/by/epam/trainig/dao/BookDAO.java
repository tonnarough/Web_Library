package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.BookDAOImpl;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;

import java.util.List;

public interface BookDAO extends EntityDAO<Book> {

    List<Book> findAll(int currentPage, int recordsOnPage);

    List<Author> findAuthorsByBookId(int bookId);

    List<Genre> findGenresByBookId(int bookId);

    List<PublishingHouse> findPublishingHousesByBookId(int bookId);

    List<Book> findBooksByAuthorId(int authorId, String tableAuthorName, List<String> columnAuthorNames);

    List<Book> findBooksByGenreId(int genreId, String tableGenreName, List<String> columnGenreNames);

    List<Book> findBooksByPublishingHouseId(int publishingHouseId, String tableGenreName, List<String> columnGenreNames);

    static BookDAO getInstance() {
        return BookDAOImpl.getInstance();
    }

}
