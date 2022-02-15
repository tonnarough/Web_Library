package by.epam.trainig.service;

import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.service.impl.GenreServiceImpl;

import java.util.List;

public interface GenreService extends EntityService<Genre> {

    List<Book> findBooksByGenreId(int genreId);

    static GenreService getInstance(){
        return GenreServiceImpl.getInstance();
    }

}
