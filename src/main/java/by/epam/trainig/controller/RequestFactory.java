package by.epam.trainig.controller;

import by.epam.trainig.controller.command.CommandRequest;
import by.epam.trainig.controller.command.CommandResponse;
import by.epam.trainig.controller.impl.RequestFactoryImpl;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public interface RequestFactory {

    CommandRequest createRequest(HttpServletRequest request);

    CommandResponse createForwardResponse(String path);

    CommandResponse createRedirectResponseWithInputStream(String path, S3ObjectInputStream inputStream);

    CommandResponse createRedirectResponse(String path);

    CommandResponse createRedirectResponseWithCookie(String path, Cookie cookie);

    static RequestFactory getInstance() {
        return RequestFactoryImpl.INSTANCE;
    }

}
