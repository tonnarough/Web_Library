package by.epam.trainig.controller.command;

import by.epam.trainig.controller.command.CommandRequest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public class WrappingCommandRequest implements CommandRequest {

    private final HttpServletRequest request;
    private ServletContext servletContext;

    public WrappingCommandRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void addAttributeToJsp(String name, Object attribute) {
        request.setAttribute(name, attribute);
    }

    public Object getAttribute(String name) {
        return request.getAttribute(name);
    }

    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }

    @Override
    public Collection<Part> getParts() throws ServletException, IOException {
        return request.getParts();
    }

    @Override
    public String getRequestURL() {
        return String.valueOf(request.getRequestURL());
    }

    @Override
    public ServletContext getServletContext() {
        return request.getServletContext();
    }

    @Override
    public boolean sessionExists() {
        return request.getSession(false) != null;
    }

    @Override
    public boolean addToSession(String name, Object value) {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(name, value);
            return true;
        }
        return false;
    }

    @Override
    public void deleteFromSession(String name){
        final HttpSession session = request.getSession(false);
        if (session != null){
            session.removeAttribute(name);
        }
    }

    @Override
    public Optional<Object> retrieveFromSession(String name) {
        return Optional.ofNullable(request.getSession(false))
                .map(httpSession -> httpSession.getAttribute(name));
    }

    @Override
    public void clearSession() {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public void createSession() {
        request.getSession(true);
    }
}
