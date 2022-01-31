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
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public enum BookServiceImpl implements BookService {
    INSTANCE(BookDAO.getInstance(), AuthorDAO.getInstance(), GenreDAO.getInstance(), PublishingHouseDAO.getInstance());

    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

    private static final String ACCESS_KEY_PARAMETER = "aws.accessKeyId";
    private static final String ACCESS_KEY_ATTRIBUTE = "AKIA5PC3PNARA4GDLEUZ";
    private static final String SECRET_KEY_PARAMETER = "aws.secretKey";
    private static final String SECRET_KEY_ATTRIBUTE = "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";
    private static final String BUCKET_NAME = "training-epam";

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
    public List<Book> findByAuthor(Author author) {
        return null;
    }

    @Override
    public List<Book> findByPublishingHouse(PublishingHouse publishingHouse) {
        return null;
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        return null;
    }

    @Override
    public List<Book> findAllBook() {
        return null;
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

        System.setProperty(ACCESS_KEY_PARAMETER, ACCESS_KEY_ATTRIBUTE);
        System.setProperty(SECRET_KEY_PARAMETER, SECRET_KEY_ATTRIBUTE);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion("us-east-1")
                .build();

        System.out.println("Downloading an object");
        S3Object fullObject = s3Client.getObject(new GetObjectRequest(BUCKET_NAME, file));
        System.out.println("Content-Type: " + fullObject.getObjectMetadata().getContentType());
        System.out.println("Content: ");
        return fullObject.getObjectContent();

    }
}
