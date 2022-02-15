package by.epam.trainig.service.impl;

import by.epam.trainig.dao.BookDAO;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.exception.DAOException;
import by.epam.trainig.exception.ServiceException;
import by.epam.trainig.service.BookService;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Optional;

public enum BookServiceImpl implements BookService {
    INSTANCE(BookDAO.getInstance());

    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

    private static final String AWS_ACCESS_KEY = "AKIA5PC3PNARAK5IQTWA";
    private static final String AWS_SECRET_KEY = "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";
    private static final String BUCKET_NAME = "training-epam";

    private final BookDAO bookDAO;

    BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public void update(String updColumn, Object updValue, String whereColumn, Object whereValue) throws ServiceException {

        try {

            bookDAO.update(updColumn, updValue, whereColumn, whereValue);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while updating book", e);
            throw new ServiceException("Sql exception occurred while updating book", e);

        }

    }

    @Override
    public List<Author> findAuthorsByBookId(int bookId) {

        return bookDAO.findAuthorsByBookId(bookId);

    }

    @Override
    public List<Genre> findGenresByBookId(int bookId) {

        return bookDAO.findGenresByBookId(bookId);

    }

    @Override
    public List<PublishingHouse> findPublishingHousesByBookId(int bookId) {

        return bookDAO.findPublishingHousesByBookId(bookId);

    }

    @Override
    public void uploadBook(String title, File book) {

        AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("us-east-1")
                .build();

        System.out.println("Upload an object");
        s3Client.putObject(new PutObjectRequest(BUCKET_NAME, title, book));

    }

    @Override
    public void delete(Book book) throws ServiceException {

        try {

            bookDAO.delete(book.getId());

        } catch (DAOException e) {

            logger.error("Sql exception occurred while deleting book", e);
            throw new ServiceException("Sql exception occurred while deleting book", e);
        }

    }

    @Override
    public S3ObjectInputStream downloadBook(String file) {

        AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("us-east-1")
                .build();

        S3Object fullObject = s3Client.getObject(new GetObjectRequest(BUCKET_NAME, file));
        return fullObject.getObjectContent();

    }

    @Override
    public List<Book> findAll(int currentPage, int recordsPerPage) {

        return bookDAO.findAll(currentPage, recordsPerPage);

    }

    @Override
    public Optional<Book> findBy(String columnName, Object value) {

        return bookDAO.findBy(columnName, value);

    }

    @Override
    public List<Book> findAllWhere(String column, Object value) {

        return bookDAO.findAllWhere(column, value);

    }

    @Override
    public void create(Book entity) throws ServiceException {

        try {

            bookDAO.create(entity);

        } catch (DAOException e) {

            logger.error("Sql exception occurred while creating book", e);
            throw new ServiceException("Sql exception occurred while creating book", e);
        }

    }

    @Override
    public int getNumberOfRows() {

        return bookDAO.getCountOfRows();

    }

}
