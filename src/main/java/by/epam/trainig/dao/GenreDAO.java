package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodGenreDAO;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Genre;

import java.util.List;

public interface GenreDAO extends EntityDAO<Genre>{

    List<Genre> findGenreByBookId(int id);

    static MethodGenreDAO getInstance(){
        return MethodGenreDAO.INSTANCE;
    }

}
