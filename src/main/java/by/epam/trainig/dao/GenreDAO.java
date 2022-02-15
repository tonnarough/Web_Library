package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.GenreDAOImpl;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDAO extends EntityDAO<Genre> {

    Optional<Genre> findBy(Object value);

    List<String> getGenresBooksColumns();

    List<Book> findBooksByGenreId(int genreId);

    List<Genre> findGenresByBookId(int bookId, String bookTableName, String bookColumnName);

    static GenreDAO getInstance() {
        return GenreDAOImpl.getInstance();
    }

}
