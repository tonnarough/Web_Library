package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.PublishingHouseDAOImpl;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.PublishingHouse;

import java.util.List;
import java.util.Optional;

public interface PublishingHouseDAO extends EntityDAO<PublishingHouse> {

    Optional<PublishingHouse> findBy(Object[] values);

    List<String> getPublishingHousesBooksColumns();

    List<Book> findBooksByPublishingHouseId(int publishingHouseId);

    List<PublishingHouse> findPublishingHousesByBookId(int bookId, String bookTableName, String bookColumnName);

    static PublishingHouseDAO getInstance() {
        return PublishingHouseDAOImpl.getInstance();
    }

}
