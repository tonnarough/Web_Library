package by.epam.trainig.controller.command;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

import javax.servlet.http.Cookie;

public interface CommandResponse {

    boolean isRedirect();

    String getPath();

    Cookie getCookie();

    S3ObjectInputStream getInputStream();

}
