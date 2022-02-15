package by.epam.trainig.service.impl;

import by.epam.trainig.dao.AuthorDAO;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.service.AuthorService;
import by.epam.trainig.service.CommonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public final class AuthorServiceImpl extends CommonService<Author> implements AuthorService {

    private static final Logger logger = LogManager.getLogger(AuthorServiceImpl.class);

    private final AuthorDAO authorDAO;

    AuthorServiceImpl(AuthorDAO authorDAO) {
        super(authorDAO, logger);
        this.authorDAO = authorDAO;
    }

    public static AuthorService getInstance() {
        return AuthorServiceImpl.Holder.INSTANCE;
    }

    @Override
    public Optional<Author> findBy(String[] columnNames, Object[] values) {

        return authorDAO.findBy(columnNames, values);

    }

    @Override
    public List<Book> findBooksByAuthorId(int authorId) {

        return authorDAO.findBooksByAuthorId(authorId);

    }

    private static class Holder {
        public static final AuthorService INSTANCE = new AuthorServiceImpl(
                AuthorDAO.getInstance()
        );
    }

}
