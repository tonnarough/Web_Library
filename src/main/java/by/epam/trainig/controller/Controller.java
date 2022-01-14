package by.epam.trainig.controller;

import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.controller.command.Command;
import by.epam.trainig.controller.command.CommandProvider;
import by.epam.trainig.dao.connectionpool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final CommandProvider commandProvider = new CommandProvider();

    public Controller() {
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        ConnectionPool.getConnectionPool().getConnection();
        DatabaseEntityContext.getDatabaseEntityContext();
        super.service(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String commandName = req.getParameter("command");

        Command command = commandProvider.getCommand(commandName);
        command.execute(req, resp);


    }


}
