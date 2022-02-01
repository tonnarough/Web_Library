package by.epam.trainig.service;

import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.impl.BookServiceImpl;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.util.List;

public interface BookService extends EntityService<Book>{

    List<Book> findByAuthor(Author author);

    List<Book> findByPublishingHouse(PublishingHouse publishingHouse);

    List<Book> findByGenre(Genre genre);

    List<Book> findAllBooks(int currentPage, int recordsPerPage);

    int getNumberOfRows();

    Book findBookById(int id) throws ServiceException;

    List<Author> findAuthorsByBookId(int id);

    List<Genre> findGenresByBookId(int id);

    List<PublishingHouse> findPublishingHouseByBookId(int id);

    S3ObjectInputStream downloadBook(String file);

    List<Book> findBookByTitle(String title);

    static BookServiceImpl getInstance(){
        return BookServiceImpl.INSTANCE;
    }

}
