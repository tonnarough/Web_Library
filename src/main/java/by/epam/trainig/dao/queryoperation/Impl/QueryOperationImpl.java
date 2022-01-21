package by.epam.trainig.dao.queryoperation.Impl;

import by.epam.trainig.annotation.Table;
import by.epam.trainig.dao.buildentity.EntityBuilderFactory;
import by.epam.trainig.dao.connectionpool.ConnectionPool;
import by.epam.trainig.dao.impl.MethodUserDAO;
import by.epam.trainig.dao.queryoperation.QueryOperation;
import by.epam.trainig.dao.queryoperation.QueryOperator;
import by.epam.trainig.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class QueryOperationImpl implements QueryOperation {

    private static final Logger logger = LogManager.getLogger(QueryOperationImpl.class);
    private String query;
    private final EntityBuilderFactory entityBuilderFactory = EntityBuilderFactory.getInstance();

    private QueryOperationImpl() {
    }

    public static QueryOperationImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void update(Table table, String column1, Object value1, String column2, Object value2) {

        query = QueryOperator.UPDATE + " " + table.name() + " " + QueryOperator.SET + " " + column1 +
                " = '" + value1 + "' " + QueryOperator.WHERE + " " + column2 + " = '" + value2 + "' ";

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection();
             final PreparedStatement preparedStatement = connection
                     .prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed updating ", e);
        }
    }

    @Override
    public <T extends Entity> List<T> findAll(Table table, Class<T> type) throws SQLException {

        final List<T> entityList = new ArrayList<>();
        query = QueryOperator.SELECT + " * " + QueryOperator.FROM + " " + table.name();

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection();
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                entityList.add(entityBuilderFactory.entityBuild(type).buildEntity(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Failed finding of all entities ", e);
        }

        return entityList;
    }

    @Override
    public void delete(Table table, String column, Object values) {

        query = QueryOperator.DELETE + " " + QueryOperator.FROM + " " + table.name() + " "
                + QueryOperator.WHERE + " " + column + " = '" + values + "'";

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection();
             final PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed deleting ", e);
        }
    }

    @Override
    public <T extends Entity> void create(List<String> entityColumns, Table table, T entity, Class<T> type) {

        final StringBuilder subquery = new StringBuilder();
        final StringBuilder numberOfValues = new StringBuilder();

        for (String column : entityColumns) {
            subquery.append(column).append(", ");
            numberOfValues.append("?, ");
        }

        subquery.replace(subquery.lastIndexOf(","), subquery.length(), " )");
        numberOfValues.replace(numberOfValues.lastIndexOf(","), numberOfValues.length(), " )");

        query = QueryOperator.INSERT + " " + QueryOperator.INTO + " " + table.name() +
                " ( " + subquery + " " + QueryOperator.VALUES + " ( " + numberOfValues;

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            entityBuilderFactory.entityBuild(type).buildResultSetByEntity(prepareStatement, entity);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed creating ", e);
        }
    }

    @Override
    public <T extends Entity> void create(List<String> entityColumns, Table table, T entity, Class<T> type, Connection connection) {

        final StringBuilder subquery = new StringBuilder();
        final StringBuilder numberOfValues = new StringBuilder();

        for (String column : entityColumns) {
            subquery.append(column).append(", ");
            numberOfValues.append("?, ");
        }

        subquery.replace(subquery.lastIndexOf(","), subquery.length(), " )");
        numberOfValues.replace(numberOfValues.lastIndexOf(","), numberOfValues.length(), " )");

        query = QueryOperator.INSERT + " " + QueryOperator.INTO + " " + table.name() +
                " ( " + subquery + " " + QueryOperator.VALUES + " ( " + numberOfValues;

        try (PreparedStatement prepareStatement = connection.prepareStatement(query)) {
            entityBuilderFactory.entityBuild(type).buildResultSetByEntity(prepareStatement, entity);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Failed creating with transaction ", e);
        }
    }

    @Override
    public <T extends Entity> Optional<T> findBy(Table table, String column,
                                                 Object value, Class<T> type) {

        if (value instanceof String) {

            query = QueryOperator.SELECT + " * " + QueryOperator.FROM + " " + table.name() +
                    " " + QueryOperator.WHERE + " " + column + " = '" + value + "'";
        } else if (value instanceof Integer) {

            query = QueryOperator.SELECT + " * " + QueryOperator.FROM + " " + table.name() +
                    " " + QueryOperator.WHERE + " " + column + " = " + value;
        }

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection();
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(query)) {

            return resultSet.next()
                    ? Optional.ofNullable(entityBuilderFactory
                    .entityBuild(type).buildEntity(resultSet))
                    : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Failed finding by smth ", e);
        }
        return Optional.empty();
    }

    private static class Holder {
        public final static QueryOperationImpl INSTANCE = new QueryOperationImpl();
    }
}
