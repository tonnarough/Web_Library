package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.*;
import by.epam.trainig.dao.connectionpool.ConnectionPool;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookDAOImpl extends CommonDAO<Book> implements BookDAO {

    private static final Logger logger = LogManager.getLogger(BookDAOImpl.class);

    private static final String ID = "id";

    private static final String GENRES_BOOKS = "genres_books";
    private static final String AUTHORS_BOOKS = "authors_books";
    private static final String PUBLISHING_HOUSES_BOOKS = "publishing_houses_books";

    private static final Class<Book> entityClass = Book.class;
    private static final Table tableBookName = entityClass.getAnnotation(Table.class);
    private static final List<String> columnBookNames = DatabaseEntityContext.getDatabaseEntityContext()
            .getTableColumn(tableBookName.name());
    private final List<String> booksMTMColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getManyToManyColumn(tableBookName.name());

    private final AuthorDAO authorDAO;
    private final GenreDAO genreDAO;
    private final PublishingHouseDAO publishingHouseDAO;

    public BookDAOImpl(AuthorDAO authorDAO, GenreDAO genreDAO, PublishingHouseDAO publishingHouseDAO) {
        super(logger, columnBookNames, tableBookName);
        this.authorDAO = authorDAO;
        this.genreDAO = genreDAO;
        this.publishingHouseDAO = publishingHouseDAO;
    }

    public static BookDAO getInstance() {
        return BookDAOImpl.Holder.INSTANCE;
    }

    @Override
    public List<Author> findAuthorsByBookId(int bookId) {

        return authorDAO.findAuthorsByBookId(bookId, tableBookName.name(), booksMTMColumns.get(0));

    }

    @Override
    public List<Genre> findGenresByBookId(int bookId) {

        return genreDAO.findGenresByBookId(bookId, tableBookName.name(), booksMTMColumns.get(1));

    }

    @Override
    public List<PublishingHouse> findPublishingHousesByBookId(int bookId) {

        return publishingHouseDAO.findPublishingHousesByBookId(bookId, tableBookName.name(), booksMTMColumns.get(2));

    }

    @Override
    public List<Book> findBooksByAuthorId(int authorId, String tableAuthorName, List<String> columnAuthorNames) {

        return executeStatementForEntities(
                findByManyToManyQuery(tableBookName.name(), tableAuthorName, AUTHORS_BOOKS,
                        columnBookNames.get(0), columnAuthorNames.get(0), booksMTMColumns.get(0), authorId),
                this::extractResultCatchingException,
                null);

    }

    @Override
    public List<Book> findBooksByGenreId(int genreId, String tableGenreName, List<String> columnGenreNames) {

        return executeStatementForEntities(
                findByManyToManyQuery(tableBookName.name(), tableGenreName, GENRES_BOOKS,
                        columnBookNames.get(0), columnGenreNames.get(0), booksMTMColumns.get(1), genreId),
                this::extractResultCatchingException,
                null);

    }

    @Override
    public List<Book> findBooksByPublishingHouseId(int publishingHouseId, String tableGenreName, List<String> columnGenreNames) {

        return executeStatementForEntities(
                findByManyToManyQuery(tableBookName.name(), tableGenreName, PUBLISHING_HOUSES_BOOKS,
                        columnBookNames.get(0), columnGenreNames.get(0), booksMTMColumns.get(2), publishingHouseId),
                this::extractResultCatchingException,
                null);

    }

    @Override
    public Optional<Book> findBy(String columnName, Object value) {

        Optional<Book> book = super.findBy(columnName, value);

        if (book.isPresent()) {

            List<Author> authors = authorDAO.findAuthorsByBookId(book.get().getId(), tableBookName.name(), booksMTMColumns.get(0));
            List<Genre> genres = genreDAO.findGenresByBookId(book.get().getId(), tableBookName.name(), booksMTMColumns.get(1));
            List<PublishingHouse> publishingHouses = publishingHouseDAO.findPublishingHousesByBookId(book.get().getId(), tableBookName.name(), booksMTMColumns.get(2));

            book.get().setAuthors(authors);
            book.get().setGenres(genres);
            book.get().setPublishingHouses(publishingHouses);

        } else {

            return Optional.empty();

        }
        return book;

    }

    @Override
    public void create(Book book) throws DAOException {

        Connection connection = ConnectionPool.getConnectionPool().getConnection();

        try {

            connection.setAutoCommit(false);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            super.create(book, connection);

            Optional<Book> newBookFromDB = super.findBy(columnBookNames.get(5), book.getFile());

            if (newBookFromDB.isEmpty()){

                logger.error("Something went wrong with book creating");
                throw new DAOException("Something went wrong with book creating");

            }

            for (Author author : book.getAuthors()) {

                Optional<Author> authorFromDB = authorDAO.findBy(
                        new Object[]{author.getFirstName(), author.getLastName()}
                );

                if (authorFromDB.isEmpty()) {

                    authorDAO.create(author, connection);

                    Optional<Author> newAuthorFromDB = authorDAO.findBy(
                            new Object[]{author.getFirstName(), author.getLastName()});

                    if (newAuthorFromDB.isEmpty()) {

                        logger.error("Something went wrong with author creating");
                        throw new DAOException("Something went wrong with author creating");

                    }

                    super.create(authorDAO.getAuthorsBooksColumns().get(0), booksMTMColumns.get(0), AUTHORS_BOOKS,
                            statement -> {
                                statement.setInt(1, newBookFromDB.get().getId());
                                statement.setInt(2, newAuthorFromDB.get().getId());
                            }, connection);

                } else {

                    super.create(authorDAO.getAuthorsBooksColumns().get(0), booksMTMColumns.get(0), AUTHORS_BOOKS,
                            statement -> {
                                statement.setInt(1, newBookFromDB.get().getId());
                                statement.setInt(2, authorFromDB.get().getId());
                            }, connection);

                }
            }

            for (Genre genre : book.getGenres()) {

                Optional<Genre> genreFromDB = genreDAO.findBy(genre.getTitle());

                if (genreFromDB.isEmpty()) {

                    genreDAO.create(genre, connection);

                    Optional<Genre> newGenreFromDB = genreDAO.findBy(genre.getTitle());

                    if (newGenreFromDB.isEmpty()) {

                        logger.error("Something went wrong with genre creating");
                        throw new DAOException("Something went wrong with genre creating");

                    }

                    super.create(genreDAO.getGenresBooksColumns().get(0), booksMTMColumns.get(1), GENRES_BOOKS,
                            statement -> {
                                statement.setInt(1, newBookFromDB.get().getId());
                                statement.setInt(2, newGenreFromDB.get().getId());
                            }, connection);

                } else {

                    super.create(genreDAO.getGenresBooksColumns().get(0), booksMTMColumns.get(1), GENRES_BOOKS,
                            statement -> {
                                statement.setInt(1, newBookFromDB.get().getId());
                                statement.setInt(2, genreFromDB.get().getId());
                            }, connection);

                }
            }

            for (PublishingHouse publishingHouse : book.getPublishingHouses()) {

                Optional<PublishingHouse> publishingHouseFromDB = publishingHouseDAO.findBy(
                        new Object[]{publishingHouse.getTitle(), publishingHouse.getYearOfPublishing()}
                );

                if (publishingHouseFromDB.isEmpty()) {

                    publishingHouseDAO.create(publishingHouse, connection);

                    Optional<PublishingHouse> newPublishingHouseFromDB = publishingHouseDAO.findBy(
                            new Object[]{publishingHouse.getTitle(), publishingHouse.getYearOfPublishing()}
                    );

                    if (newPublishingHouseFromDB.isEmpty()) {

                        logger.error("Something went wrong with publishing house creating");
                        throw new DAOException("Something went wrong with publishing house creating");

                    }

                    super.create(publishingHouseDAO.getPublishingHousesBooksColumns().get(0), booksMTMColumns.get(2), PUBLISHING_HOUSES_BOOKS,
                            statement -> {
                                statement.setInt(1, newBookFromDB.get().getId());
                                statement.setInt(2, newPublishingHouseFromDB.get().getId());
                            }, connection);

                } else {

                    super.create(publishingHouseDAO.getPublishingHousesBooksColumns().get(0),booksMTMColumns.get(2), PUBLISHING_HOUSES_BOOKS,
                            statement -> {
                                statement.setInt(1, newBookFromDB.get().getId());
                                statement.setInt(2, publishingHouseFromDB.get().getId());
                            }, connection);

                }
            }

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.commit();

        } catch (SQLException e) {

            logger.error("Failed transaction", e);
            rollback(connection);

        } finally {

            reliaseConnection(connection);

        }
    }

    @Override
    protected Book extractResult(ResultSet rs) throws SQLException {

        return new Book(
                rs.getInt(columnBookNames.get(0)),
                rs.getString(columnBookNames.get(1)),
                rs.getString(columnBookNames.get(2)),
                rs.getString(columnBookNames.get(3)),
                rs.getInt(columnBookNames.get(4)),
                rs.getString(columnBookNames.get(5)),
                rs.getString(columnBookNames.get(6))
        );

    }

    @Override
    protected void fillEntity(PreparedStatement statement, Book entity) throws SQLException {

        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getTitle());
        statement.setString(3, entity.getDescription());
        statement.setString(4, entity.getAgeLimit());
        statement.setInt(5, entity.getNumberOfPages());
        statement.setString(6, entity.getFile());
        statement.setString(7, entity.getPicture());

    }

    private static class Holder {
        public static final BookDAO INSTANCE = new BookDAOImpl(AuthorDAO.getInstance(), GenreDAO.getInstance(), PublishingHouseDAO.getInstance());
    }
}

