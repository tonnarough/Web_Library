package by.epam.trainig.service;

import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.service.impl.BookServiceImpl;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.File;
import java.util.List;

public interface BookService extends EntityService<Book> {

    List<Author> findAuthorsByBookId(int bookId);

    List<Genre> findGenresByBookId(int bookId);

    List<PublishingHouse> findPublishingHousesByBookId(int bookId);

    void uploadBook(String title, File book);

    S3ObjectInputStream downloadBook(String file);

    static BookServiceImpl getInstance(){
        return BookServiceImpl.INSTANCE;
    }

}
