package by.epam.trainig.service.impl;

import by.epam.trainig.dao.PublishingHouseDAO;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.service.CommonService;
import by.epam.trainig.service.PublishingHouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public final class PublishingHouseServiceImpl extends CommonService<PublishingHouse> implements PublishingHouseService {

    private static final Logger logger = LogManager.getLogger(PublishingHouseServiceImpl.class);

    private final PublishingHouseDAO publishingHouseDAO;

    PublishingHouseServiceImpl(PublishingHouseDAO publishingHouseDAO) {
        super(publishingHouseDAO, logger);
        this.publishingHouseDAO = publishingHouseDAO;
    }

    public static PublishingHouseService getInstance() {
        return PublishingHouseServiceImpl.Holder.INSTANCE;
    }

    @Override
    public List<Book> findBooksByPublishingHouseId(int publishingHouseId) {

        return publishingHouseDAO.findBooksByPublishingHouseId(publishingHouseId);

    }

    private static class Holder {
        public static final PublishingHouseService INSTANCE = new PublishingHouseServiceImpl(
                PublishingHouseDAO.getInstance()
        );
    }

}
