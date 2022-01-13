package by.epam.trainig.dao.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ConnectionPool {

    DataBaseResourceBundle dataBaseResourceBundle = DataBaseResourceBundle.getDataBaseResourceBundle();
    private final String url = dataBaseResourceBundle.getValue(DataBaseProperties.URL);
    private final String user = dataBaseResourceBundle.getValue(DataBaseProperties.USER);
    private final String password = dataBaseResourceBundle.getValue(DataBaseProperties.PASSWORD);
    private final int poolSize = Integer.parseInt(dataBaseResourceBundle.getValue(DataBaseProperties.POOLSIZE));
    private final List<Connection> availableConnections;
    private final List<Connection> takenConnections;

    private static final ConnectionPool connectionPool = new ConnectionPool();

    private ConnectionPool() {
        this.availableConnections = new ArrayList<>();
        this.takenConnections = new ArrayList<>();
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
            final Connection connection = availableConnections.remove(0);
            takenConnections.add(connection);
        } else {
            throw new RuntimeException("ConnPool is empty");
        }
        return takenConnections.get(0);
    }

    public void releaseConnectionPool() {
        if (!takenConnections.isEmpty()) {
            final Connection connection = takenConnections.remove(0);
            availableConnections.add(connection);
        } else {
            throw new RuntimeException("taken conn is empty");
        }

    }
}