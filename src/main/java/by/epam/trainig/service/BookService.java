package by.epam.trainig.service;

import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.service.impl.BookServiceImpl;

import java.util.List;

public interface BookService extends EntityService<Book>{

    List<Book> findByAuthor(Author author);

    List<Book> findByPublishingHouse(PublishingHouse publishingHouse);

    List<Book> findByGenre(Genre genre);

    List<Book> findAllBook();

    static BookServiceImpl getInstance(){
        return BookServiceImpl.INSTANCE;
    }

}
