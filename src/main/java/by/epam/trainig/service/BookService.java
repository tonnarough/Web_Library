package by.epam.trainig.service;

import by.epam.trainig.entity.book.Book;
import by.epam.trainig.service.impl.BookServiceImpl;

public interface BookService extends EntityService<Book>{

    static BookServiceImpl getInstance(){
        return BookServiceImpl.INSTANCE;
    }

}
