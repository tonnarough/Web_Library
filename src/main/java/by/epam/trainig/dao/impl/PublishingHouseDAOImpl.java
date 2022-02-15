package by.epam.trainig.dao.impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.BookDAO;
import by.epam.trainig.dao.CommonDAO;
import by.epam.trainig.dao.PublishingHouseDAO;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.PublishingHouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PublishingHouseDAOImpl extends CommonDAO<PublishingHouse> implements PublishingHouseDAO {

    private static final Logger logger = LogManager.getLogger(PublishingHouseDAOImpl.class);

    private static final String TABLE_MANY_TO_MANY = "publishing_houses_books";

    private static final Class<PublishingHouse> entityClass = PublishingHouse.class;
    private static final Table tablePublishingHouseName = entityClass.getAnnotation(Table.class);
    private static final List<String> columnPublishingHouseNames = DatabaseEntityContext.getDatabaseEntityContext()
            .getTableColumn(tablePublishingHouseName.name());
    private final List<String> publishingHousesBooksColumns = DatabaseEntityContext.getDatabaseEntityContext()
            .getManyToManyColumn(tablePublishingHouseName.name());

    private final BookDAO bookDAO;

    private PublishingHouseDAOImpl(BookDAO bookDAO) {
        super(logger, columnPublishingHouseNames, tablePublishingHouseName);
        this.bookDAO = bookDAO;
    }

    public static PublishingHouseDAO getInstance() {
        return PublishingHouseDAOImpl.Holder.INSTANCE;
    }

    @Override
    public Optional<PublishingHouse> findBy(Object[] values) {

        return super.findBy(new String[]{columnPublishingHouseNames.get(1), columnPublishingHouseNames.get(2)},
                values);

    }

    @Override
    public List<String> getPublishingHousesBooksColumns() {

        return publishingHousesBooksColumns;

    }

    @Override
    public List<Book> findBooksByPublishingHouseId(int publishingHouseId) {

        return bookDAO.findBooksByPublishingHouseId(publishingHouseId, tablePublishingHouseName.name(), columnPublishingHouseNames);

    }

    @Override
    public List<PublishingHouse> findPublishingHousesByBookId(int bookId, String bookTableName, String bookColumnName) {

        return executeStatementForEntities(
                findByManyToManyQuery(tablePublishingHouseName.name(), bookTableName, TABLE_MANY_TO_MANY,
                        columnPublishingHouseNames.get(0), bookColumnName, publishingHousesBooksColumns.get(0), bookId),
                this::extractResultCatchingException,
                null);

    }

    @Override
    public Optional<PublishingHouse> findBy(String columnName, Object value) {

        Optional<PublishingHouse> publishingHouse = super.findBy(columnName, value);

        if (publishingHouse.isPresent()) {

            List<Book> books = bookDAO.findBooksByPublishingHouseId(publishingHouse.get().getId(), tablePublishingHouseName.name(), columnPublishingHouseNames);

            publishingHouse.get().setBooks(books);

        } else {

            return Optional.empty();

        }
        return publishingHouse;
    }

    @Override
    protected PublishingHouse extractResult(ResultSet rs) throws SQLException {

        return new PublishingHouse(
                rs.getInt(columnPublishingHouseNames.get(0)),
                rs.getString(columnPublishingHouseNames.get(1)),
                rs.getInt(columnPublishingHouseNames.get(2))
        );

    }

    @Override
    protected void fillEntity(PreparedStatement statement, PublishingHouse entity) throws SQLException {

        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getTitle());
        statement.setInt(3, entity.getYearOfPublishing());

    }

    private static class Holder {
        public static final PublishingHouseDAO INSTANCE = new PublishingHouseDAOImpl(BookDAO.getInstance());
    }

}
