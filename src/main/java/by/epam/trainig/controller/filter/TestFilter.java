package by.epam.trainig.controller.filter;

import by.epam.trainig.dao.DAOFactory;
import by.epam.trainig.dao.EntityDAO;
import by.epam.trainig.dao.EntityDAOFactory;
import by.epam.trainig.entity.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestFilter implements Filter {

    private final DAOFactory daoFactory = DAOFactory.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        List<User> userList = new ArrayList<>();
        try {
           userList = daoFactory.entityDAO(User.class).findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {

    }
}
