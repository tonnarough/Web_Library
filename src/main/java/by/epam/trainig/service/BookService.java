package by.epam.trainig.service;

import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.impl.BookServiceImpl;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface BookService extends EntityService<Book>{

    void updateAuthor(String updColumn, Object updValue, String whereColumn, Object whereValue);

    void updatePublishingHouse(String updColumn, Object updValue, String whereColumn, Object whereValue);

    void updateGenre(String updColumn, Object updValue, String whereColumn, Object whereValue);

    void updateBook(String updColumn, Object updValue, String whereColumn, Object whereValue);

    List<Book> findAllBooks(int currentPage, int recordsPerPage);

    void uploadBook(String title, File book);

    Optional<Book> findBookWithAuthorGenrePublishingHouseById(int id) throws ServiceException;

    int getNumberOfRows();

    Book findBookById(int id) throws ServiceException;

    void deleteBook(Book book);

    List<Author> findAuthorsByBookId(int id);

    List<Genre> findGenresByBookId(int id);

    List<PublishingHouse> findPublishingHouseByBookId(int id);

    S3ObjectInputStream downloadBook(String file);

    List<Book> findBookByTitle(String title);

    static BookServiceImpl getInstance(){
        return BookServiceImpl.INSTANCE;
    }

}
