package by.epam.trainig.service.impl;

import by.epam.trainig.dao.BookDAO;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.service.BookService;
import by.epam.trainig.service.CommonService;
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

public final class BookServiceImpl extends CommonService<Book> implements BookService {

    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

    private static final String AWS_ACCESS_KEY = "AKIA5PC3PNARAK5IQTWA";
    private static final String AWS_SECRET_KEY = "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";
    private static final String BUCKET_NAME = "training-epam";
    private static final String REGION = "us-east-1";

    private final BookDAO bookDAO;

    BookServiceImpl(BookDAO bookDAO) {
        super(bookDAO, logger);
        this.bookDAO = bookDAO;
    }

    public static BookService getInstance() {
        return BookServiceImpl.Holder.INSTANCE;
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
                .withRegion(REGION)
                .build();

        s3Client.putObject(new PutObjectRequest(BUCKET_NAME, title, book));

    }

    @Override
    public S3ObjectInputStream downloadBook(String file) {

        AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(REGION)
                .build();

        S3Object fullObject = s3Client.getObject(new GetObjectRequest(BUCKET_NAME, file));
        return fullObject.getObjectContent();

    }

    private static class Holder {
        public static final BookService INSTANCE = new BookServiceImpl(
                BookDAO.getInstance()
        );
    }

}
