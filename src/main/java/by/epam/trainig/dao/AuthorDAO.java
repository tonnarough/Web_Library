package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.AuthorDAOImpl;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO extends EntityDAO<Author> {

    Optional<Author> findBy(Object[] values);

    List<Author> findAll(int currentPage, int recordsOnPage);

    List<String> getAuthorsBooksColumns();

    List<Book> findBooksByAuthorId(int authorId);

    List<Author> findAuthorsByBookId(int bookId, String bookTableName, String bookColumnName);

    static AuthorDAO getInstance() {
        return AuthorDAOImpl.getInstance();
    }

}
