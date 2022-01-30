package by.epam.trainig.service.impl;

import by.epam.trainig.entity.book.Book;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.BookService;

import java.util.List;

public enum BookServiceImpl implements BookService {
    INSTANCE;

    @Override
    public List<Book> findAll() throws ServiceException {
        return null;
    }

    @Override
    public void create(Book entity) {

    }
}
