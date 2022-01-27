package by.epam.trainig.controller.command;

import by.epam.trainig.controller.PropertyContext;
import by.epam.trainig.controller.RequestFactory;
import by.epam.trainig.service.TestService;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import javax.servlet.ServletException;
import java.io.IOException;

public enum MainAuthCommand implements Command {
    INSTANCE(TestService.getInstance(), RequestFactory.getInstance(), PropertyContext.getInstance());

    private static final String MAIN_AUTH = "page.main_auth";

    private final TestService testService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    MainAuthCommand(TestService testService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.testService = testService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws IOException, ServletException {

        return requestFactory.createRedirectResponseWithInputStream(propertyContext.get(MAIN_AUTH), testService.testMethod());
    }
}
