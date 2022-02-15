package by.epam.trainig.service;

import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.service.impl.PublishingHouseServiceImpl;

import java.util.List;

public interface PublishingHouseService extends EntityService<PublishingHouse> {

    List<Book> findBooksByPublishingHouseId(int publishingHouseId);

    static PublishingHouseService getInstance(){
        return PublishingHouseServiceImpl.getInstance();
    }

}
