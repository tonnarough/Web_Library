package by.epam.trainig.controller;

import by.epam.trainig.controller.command.CommandRequest;
import by.epam.trainig.controller.command.CommandResponse;
import by.epam.trainig.controller.impl.RequestFactoryImpl;

import javax.servlet.http.HttpServletRequest;

public interface RequestFactory {

    CommandRequest createRequest(HttpServletRequest request);

    CommandResponse createForwardResponse(String path);

    CommandResponse createRedirectResponse(String path);

    static RequestFactory getInstance() {
        return RequestFactoryImpl.INSTANCE;
    }

}
