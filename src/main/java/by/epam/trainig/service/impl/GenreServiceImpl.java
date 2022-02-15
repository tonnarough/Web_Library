package by.epam.trainig.service.impl;

import by.epam.trainig.dao.GenreDAO;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.service.CommonService;
import by.epam.trainig.service.GenreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public final class GenreServiceImpl extends CommonService<Genre> implements GenreService {

    private static final Logger logger = LogManager.getLogger(GenreServiceImpl.class);

    private final GenreDAO genreDAO;

    GenreServiceImpl(GenreDAO genreDAO) {
        super(genreDAO, logger);
        this.genreDAO = genreDAO;
    }

    public static GenreService getInstance() {
        return GenreServiceImpl.Holder.INSTANCE;
    }

    @Override
    public List<Book> findBooksByGenreId(int genreId) {

        return genreDAO.findBooksByGenreId(genreId);

    }

    private static class Holder {
        public static final GenreService INSTANCE = new GenreServiceImpl(
                GenreDAO.getInstance()
        );
    }

}
