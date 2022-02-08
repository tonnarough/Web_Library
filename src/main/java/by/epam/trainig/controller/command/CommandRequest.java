package by.epam.trainig.controller.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface CommandRequest {

    void addAttributeToJsp(String name, Object attribute);

    String getParameter(String name);

    Collection<Part> getParts() throws ServletException, IOException;

    String getRequestURL();

    ServletContext getServletContext();

    Object getAttribute(String name);

    void deleteFromSession(String name);

    boolean sessionExists();

    boolean addToSession(String name, Object value);

    Optional<Object> retrieveFromSession(String name);

    void clearSession();

    void createSession();

}
