package by.epam.trainig.service.impl;

import by.epam.trainig.dao.AuthorDAO;
import by.epam.trainig.dao.BookDAO;
import by.epam.trainig.dao.GenreDAO;
import by.epam.trainig.dao.PublishingHouseDAO;
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
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Optional;

public enum BookServiceImpl implements BookService {
    INSTANCE(BookDAO.getInstance(), AuthorDAO.getInstance(), GenreDAO.getInstance(), PublishingHouseDAO.getInstance());

    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

    private static final String AWS_ACCESS_KEY = "AKIA5PC3PNARAK5IQTWA";
    private static final String AWS_SECRET_KEY = "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";
    private static final String BUCKET_NAME = "training-epam";
    private static final String TITLE_PARAMETER = "title";

    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;
    private final GenreDAO genreDAO;
    private final PublishingHouseDAO publishingHouseDAO;

    BookServiceImpl(BookDAO bookDAO, AuthorDAO authorDAO, GenreDAO genreDAO, PublishingHouseDAO publishingHouseDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
        this.genreDAO = genreDAO;
        this.publishingHouseDAO = publishingHouseDAO;
    }

    @Override
    public List<Book> findAll() throws ServiceException {

        try {

            return bookDAO.findAll();

        } catch (DAOException e) {

            logger.error("Failed finding of all books", e);
            throw new ServiceException(e);
        }

    }

    @Override
    public void create(Book entity) {

    }

    @Override
    public void updateAuthor(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        authorDAO.update(updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public void updatePublishingHouse(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        publishingHouseDAO.update(updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public void updateGenre(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        genreDAO.update(updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public void updateBook(String updColumn, Object updValue, String whereColumn, Object whereValue) {

        bookDAO.update(updColumn, updValue, whereColumn, whereValue);

    }

    @Override
    public List<Book> findAllBooks(int currentPage, int recordsPerPage) {

        return bookDAO.findAllBooks(currentPage, recordsPerPage);

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
    public Optional<Book> findBookWithAuthorGenrePublishingHouseById(int id) throws ServiceException {
        try {

            return bookDAO.findBookWithAuthorGenrePublishingHouseById(id);

        } catch (DAOException e) {

            logger.error("Failed finding of book");
            throw new ServiceException("Failed finding of book");
        }
    }

    @Override
    public int getNumberOfRows() {

        return bookDAO.getNumberOfRows();

    }

    @Override
    public Book findBookById(int id) throws ServiceException {

        Optional<Book> book = bookDAO.findById(id);

        if (book.isPresent()) {

            return book.get();

        } else {

            throw new ServiceException("Book not found");
        }

    }

    @Override
    public void deleteBook(Book book) {

        bookDAO.delete(book);

    }

    @Override
    public List<Author> findAuthorsByBookId(int id) {

        return authorDAO.findAuthorsByBookId(id);

    }

    @Override
    public List<Genre> findGenresByBookId(int id) {

        return genreDAO.findGenreByBookId(id);

    }

    @Override
    public List<PublishingHouse> findPublishingHouseByBookId(int id) {

        return publishingHouseDAO.findPublishingHouseByBookId(id);

    }

    @Override
    public S3ObjectInputStream downloadBook(String file) {

        AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("us-east-1")
                .build();

        System.out.println("Downloading an object");
        S3Object fullObject = s3Client.getObject(new GetObjectRequest(BUCKET_NAME, file));
        System.out.println("Content-Type: " + fullObject.getObjectMetadata().getContentType());
        System.out.println("Content: ");
        return fullObject.getObjectContent();

    }

    @Override
    public List<Book> findBookByTitle(String title) {

        return bookDAO.findByParameter(TITLE_PARAMETER, title);

    }

}
