package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.AuthorDAO;
import by.epam.trainig.dao.BookDAO;
import by.epam.trainig.dao.CommonDAO;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AuthorDAOImpl extends CommonDAO<Author> implements AuthorDAO {

    private static final Logger logger = LogManager.getLogger(AuthorDAOImpl.class);

    private static final String TABLE_MANY_TO_MANY = "authors_books";

    private static final Class<Author> entityClass = Author.class;
    private static final Table tableAuthorName = entityClass.getAnnotation(Table.class);
    private static final List<String> columnAuthorNames = DatabaseEntityContext.getDatabaseEntityContext()
            .getTableColumn(tableAuthorName.name());
    private final List<String> authorsBooksColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getManyToManyColumn(tableAuthorName.name());

    private final BookDAO bookDAO;

    private AuthorDAOImpl(BookDAO bookDAO) {
        super(logger, columnAuthorNames, tableAuthorName);
        this.bookDAO = bookDAO;
    }

    public static AuthorDAO getInstance() {
        return AuthorDAOImpl.Holder.INSTANCE;
    }

    @Override
    public Optional<Author> findBy(Object[] values) {

        return super.findBy(new String[]{columnAuthorNames.get(1), columnAuthorNames.get(2)},
                values);

    }

    @Override
    public List<String> getAuthorsBooksColumns() {

        return authorsBooksColumns;

    }

    @Override
    public List<Book> findBooksByAuthorId(int authorId) {

        return bookDAO.findBooksByAuthorId(authorId, tableAuthorName.name(), columnAuthorNames);

    }

    @Override
    public List<Author> findAuthorsByBookId(int bookId, String bookTableName, String bookColumnName) {

        return executeStatementForEntities(
                findByManyToManyQuery(tableAuthorName.name(), bookTableName, TABLE_MANY_TO_MANY,
                        columnAuthorNames.get(0), bookColumnName, authorsBooksColumns.get(0), bookId),
                this::extractResultCatchingException,
                null);

    }

    @Override
    public Optional<Author> findBy(String[] columnNames, Object[] values) {

        Optional<Author> author = super.findBy(columnNames, values);

        if (author.isPresent()) {

            List<Book> books = bookDAO.findBooksByAuthorId(author.get().getId(), tableAuthorName.name(), columnAuthorNames);

            author.get().setBooks(books);

        } else {

            return Optional.empty();

        }
        return author;
    }

    @Override
    protected Author extractResult(ResultSet rs) throws SQLException {

        return new Author(
                rs.getInt(columnAuthorNames.get(0)),
                rs.getString(columnAuthorNames.get(1)),
                rs.getString(columnAuthorNames.get(2)),
                rs.getString(columnAuthorNames.get(3))
        );

    }

    @Override
    protected void fillEntity(PreparedStatement statement, Author entity) throws SQLException {

        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setString(4, entity.getFatherName());

    }

    private static class Holder {
        public static final AuthorDAO INSTANCE = new AuthorDAOImpl(BookDAO.getInstance());
    }

}
