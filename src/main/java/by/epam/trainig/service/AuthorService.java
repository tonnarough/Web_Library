package by.epam.trainig.service;

import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.service.impl.AuthorServiceImpl;

import java.util.List;
import java.util.Optional;

public interface AuthorService extends EntityService<Author> {

    Optional<Author> findBy(String[] columnNames, Object[] values);

    List<Book> findBooksByAuthorId(int authorId);

    static AuthorServiceImpl getInstance(){
        return AuthorServiceImpl.INSTANCE;
    }

}
