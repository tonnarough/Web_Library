package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.*;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GenreDAOImpl extends CommonDAO<Genre> implements GenreDAO {

    private static final Logger logger = LogManager.getLogger(GenreDAOImpl.class);

    private static final String TABLE_MANY_TO_MANY = "genres_books";

    private static final Class<Genre> entityClass = Genre.class;
    private static final Table tableGenreName = entityClass.getAnnotation(Table.class);
    private static final List<String> columnGenreNames = DatabaseEntityContext.getDatabaseEntityContext()
            .getTableColumn(tableGenreName.name());
    private final List<String> genresBooksColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getManyToManyColumn(tableGenreName.name());

    private final BookDAO bookDAO;

    private GenreDAOImpl(BookDAO bookDAO) {
        super(logger, columnGenreNames, tableGenreName);
        this.bookDAO = bookDAO;
    }

    public static GenreDAO getInstance() {
        return GenreDAOImpl.Holder.INSTANCE;
    }

    @Override
    public Optional<Genre> findBy(Object value) {

        return super.findBy(columnGenreNames.get(1), value);

    }

    @Override
    public List<String> getGenresBooksColumns() {

        return genresBooksColumns;

    }

    @Override
    public List<Book> findBooksByGenreId(int genreId) {

        return bookDAO.findBooksByGenreId(genreId, tableGenreName.name(), columnGenreNames);

    }

    @Override
    public List<Genre> findGenresByBookId(int bookId, String bookTableName, String bookColumnName) {

        return executeStatementForEntities(
                findByManyToManyQuery(tableGenreName.name(), bookTableName, TABLE_MANY_TO_MANY,
                        columnGenreNames.get(0), bookColumnName, genresBooksColumns.get(0), bookId),
                this::extractResultCatchingException,
                null);

    }

    @Override
    public Optional<Genre> findBy(String columnName, Object value) {

        Optional<Genre> genre = super.findBy(columnName, value);

        if (genre.isPresent()) {

            List<Book> books = bookDAO.findBooksByGenreId(genre.get().getId(), tableGenreName.name(), columnGenreNames);

            genre.get().setBooks(books);

        } else {

            return Optional.empty();

        }
        return genre;
    }

    @Override
    protected Genre extractResult(ResultSet rs) throws SQLException {

        return new Genre(
                rs.getInt(columnGenreNames.get(0)),
                rs.getString(columnGenreNames.get(1))
        );

    }

    @Override
    protected void fillEntity(PreparedStatement statement, Genre entity) throws SQLException {

        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getTitle());


    }

    private static class Holder {
        public static final GenreDAO INSTANCE = new GenreDAOImpl(BookDAO.getInstance());
    }

}
