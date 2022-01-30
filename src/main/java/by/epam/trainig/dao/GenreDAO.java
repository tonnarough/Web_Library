package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodGenreDAO;
import by.epam.trainig.entity.book.Genre;

public interface GenreDAO extends EntityDAO<Genre>{

    static MethodGenreDAO getInstance(){
        return MethodGenreDAO.INSTANCE;
    }

}
