package by.epam.trainig.dao.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public final class ConnectionPool {

    DataBaseResourceBundle dataBaseResourceBundle = DataBaseResourceBundle.getDataBaseResourceBundle();
    private final String url = dataBaseResourceBundle.getValue(DataBaseProperties.URL);
    private final String user = dataBaseResourceBundle.getValue(DataBaseProperties.USER);
    private final String password = dataBaseResourceBundle.getValue(DataBaseProperties.PASSWORD);
    private final int poolSize = Integer.parseInt(dataBaseResourceBundle.getValue(DataBaseProperties.POOLSIZE));

    private static final String CONNECTION_POOL_IS_EMPTY = "Connection Pool is empty";
    private static final String TAKEN_CONNECTION_IS_EMPTY = "TakenConnections is empty";

    private final Queue<Connection> availableConnections;
    private final Queue<Connection> takenConnections;

    private static final ConnectionPool connectionPool = new ConnectionPool();

    private ConnectionPool() {
        this.availableConnections = new LinkedBlockingQueue<>();
        this.takenConnections = new ArrayDeque<>();
        this.initConnectionPool();
    }

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    private void initConnectionPool() {
        for (int i = 0; i < poolSize; i++) {
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                Connection connection = ProxyConnection.getProxyConnection(DriverManager
                        .getConnection(url, user, password));
                availableConnections.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        if (!availableConnections.isEmpty()) {
            final Connection connection = availableConnections.remove();
            takenConnections.add(connection);
        } else {
            throw new RuntimeException(CONNECTION_POOL_IS_EMPTY);
        }
        return takenConnections.peek();
    }

    public void releaseConnectionPool() {
        if (!takenConnections.isEmpty()) {
            final Connection connection = takenConnections.poll();
            availableConnections.add(connection);
        } else {
            throw new RuntimeException(TAKEN_CONNECTION_IS_EMPTY);
        }

    }
}