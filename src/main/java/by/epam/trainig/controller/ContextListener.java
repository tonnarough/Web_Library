package by.epam.trainig.controller;

import by.epam.trainig.context.DatabaseEntityContext;
import by.epam.trainig.dao.connectionpool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getConnectionPool();
        DatabaseEntityContext.getDatabaseEntityContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
