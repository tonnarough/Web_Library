package by.epam.trainig.controller.impl;

import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.controller.command.CommandRequest;
import by.epam.trainig.controller.command.CommandResponse;
import by.epam.trainig.controller.command.WrappingCommandRequest;
import by.epam.trainig.controller.command.WrappingCommandResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum RequestFactoryImpl implements RequestFactory {
    INSTANCE;

    private final Map<String, CommandResponse> forwardResponseCache = new ConcurrentHashMap<>();
    private final Map<String, CommandResponse> redirectResponseCache = new ConcurrentHashMap<>();

    @Override
    public CommandRequest createRequest(HttpServletRequest request) {
        return new WrappingCommandRequest(request);
    }

    @Override
    public CommandResponse createForwardResponse(String path) {
        return forwardResponseCache.computeIfAbsent(path, WrappingCommandResponse::new);
    }

    @Override
    public CommandResponse createRedirectResponse(String path) {
        return redirectResponseCache.computeIfAbsent(path, p -> new WrappingCommandResponse(true,p));
    }

}
