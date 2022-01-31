package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodGenreDAO;
import by.epam.trainig.dao.impl.MethodPublishingHouseDAO;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.PublishingHouse;

import java.util.List;

public interface PublishingHouseDAO extends EntityDAO<PublishingHouse> {

    List<PublishingHouse> findPublishingHouseByBookId(int id);

    static MethodPublishingHouseDAO getInstance(){
        return MethodPublishingHouseDAO.INSTANCE;
    }

}
