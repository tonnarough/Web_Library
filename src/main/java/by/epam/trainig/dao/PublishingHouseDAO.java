package by.epam.trainig.dao;

import by.epam.trainig.dao.impl.MethodGenreDAO;
import by.epam.trainig.dao.impl.MethodPublishingHouseDAO;
import by.epam.trainig.entity.book.PublishingHouse;

public interface PublishingHouseDAO extends EntityDAO<PublishingHouse> {

    static MethodPublishingHouseDAO getInstance(){
        return MethodPublishingHouseDAO.INSTANCE;
    }

}
