package by.epam.trainig.dao;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.dao.connectionpool.ConnectionPool;
import by.epam.trainig.entity.Entity;
import by.epam.trainig.exception.DAOException;
import by.epam.trainig.exception.EntityExtractionFailedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class CommonDAO<T extends Entity> implements EntityDAO<T> {

    private static final Logger logger = LogManager.getLogger(CommonDAO.class);

    private static final String SPACE = " ";
    private static final String COMMA = ", ";
    private static final String QUOTATION_MARK = "'";
    private static final String EQUALS = " = ";
    private static final String QUESTION_MARK = "? ";
    private static final String LEFT_BRACKET = "( ";
    private static final String RIGHT_BRACKET = " )";
    private static final String STAR_SYMBOL = " * ";
    private static final String DOT = ".";

    protected abstract T extractResult(ResultSet rs) throws SQLException;

    protected abstract void fillEntity(PreparedStatement statement, T entity) throws SQLException;

    protected int executePreparedUpdate(String sql, StatementPreparator statementPreparation) {

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            if (statementPreparation != null) {
                statementPreparation.accept(statement);
            }

            return statement.executeUpdate();

        } catch (SQLException e) {

            logger.error("Sql exception occurred", e);

        }

        return 0;

    }

    protected int executePreparedUpdateWithTransaction(String sql, StatementPreparator statementPreparation, Connection connection) {

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {

            if (statementPreparation != null) {
                statementPreparation.accept(statement);
            }

            return statement.executeUpdate();

        } catch (SQLException e) {

            logger.error("Sql exception occurred", e);

        }
        return 0;
    }

    protected List<T> executeStatementForEntities(String sql, ResultSetExtractor<T> extractor,
                                                  StatementPreparator statementPreparation) {

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            if (statementPreparation != null) {
                statementPreparation.accept(statement);
            }

            final ResultSet resultSet = statement.executeQuery();
            return extractor.extractAll(resultSet);

        } catch (SQLException e) {

            logger.error("Sql exception occurred", e);

        } catch (EntityExtractionFailedException e) {

            logger.error("Could not extract entity", e);

        }
        return Collections.emptyList();
    }

    protected Optional<T> executeStatementForSpecificEntity(String sql, ResultSetExtractor<T> extractor,
                                                            StatementPreparator statementPreparation) {

        try (final Connection connection = ConnectionPool.getConnectionPool().getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            if (statementPreparation != null) {
                statementPreparation.accept(statement);
            }

            final ResultSet resultSet = statement.executeQuery();
            return resultSet.next()
                    ? Optional.ofNullable(extractor.extract(resultSet))
                    : Optional.empty();

        } catch (SQLException e) {

            logger.error("Sql exception occurred", e);

        } catch (EntityExtractionFailedException e) {

            logger.error("Could not extract entity", e);

        }
        return Optional.empty();
    }

    protected T extractResultCatchingException(ResultSet rs) throws EntityExtractionFailedException {

        try {

            return extractResult(rs);

        } catch (SQLException e) {

            logger.error("Sql exception occurred extracting entity from ResultSet", e);
            throw new EntityExtractionFailedException("Could not extract entity", e);
        }
    }

    protected String updateQuery(String tableName, String updColumn, Object updValue, String whereColumn, Object whereValue){

        if (whereValue instanceof String) {

            return QueryOperator.UPDATE + SPACE + tableName + SPACE + QueryOperator.SET + SPACE + updColumn +
                    EQUALS + QUOTATION_MARK + updValue + QUOTATION_MARK + EQUALS +
                    QueryOperator.WHERE + SPACE + whereColumn + EQUALS + QUOTATION_MARK + whereValue + QUOTATION_MARK;

        } else if (whereValue instanceof Integer) {

            return QueryOperator.UPDATE + SPACE + tableName + SPACE + QueryOperator.SET + SPACE + updColumn +
                    EQUALS + QUOTATION_MARK + updValue + QUOTATION_MARK + EQUALS +
                    QueryOperator.WHERE + SPACE + whereColumn + EQUALS + whereValue;

        }
        return null;
    }

    protected String deleteQuery(String tableName, String column, Object values){

        if (values instanceof String) {

            return QueryOperator.DELETE + SPACE + QueryOperator.FROM + SPACE + tableName + SPACE +
                    QueryOperator.WHERE + SPACE + column + EQUALS + QUOTATION_MARK + values + QUOTATION_MARK;

        } else if (values instanceof Integer) {

            return QueryOperator.DELETE + SPACE + QueryOperator.FROM + SPACE + tableName + SPACE +
                    QueryOperator.WHERE + SPACE + column + EQUALS + values;
        }
        return null;
    }

    protected String createQuery(List<String> columns, String tableName){

        final StringBuilder subquery = new StringBuilder();
        final StringBuilder numberOfValues = new StringBuilder();

        for (String column : columns) {

            subquery.append(column).append(COMMA);
            numberOfValues.append(QUESTION_MARK + COMMA);
        }

        subquery.replace(subquery.lastIndexOf(COMMA), subquery.length(), RIGHT_BRACKET);
        numberOfValues.replace(numberOfValues.lastIndexOf(COMMA), numberOfValues.length(), RIGHT_BRACKET);

        return QueryOperator.INSERT + SPACE + QueryOperator.INTO + SPACE + tableName + SPACE +
                LEFT_BRACKET + subquery + SPACE + QueryOperator.VALUES + SPACE + LEFT_BRACKET + numberOfValues;
    }

    protected String createManyToManyTableQuery(String firstColumn, String secondColumn, String tableName){

        return QueryOperator.INSERT + SPACE + QueryOperator.INTO + SPACE + tableName + SPACE +
                LEFT_BRACKET + firstColumn + COMMA + secondColumn + RIGHT_BRACKET + SPACE + QueryOperator.VALUES + SPACE +
                LEFT_BRACKET + QUESTION_MARK+ COMMA + QUESTION_MARK + RIGHT_BRACKET;

    }

    protected String findAllQuery(String tableName){

        return QueryOperator.SELECT + STAR_SYMBOL + QueryOperator.FROM + SPACE + tableName;

    }

    protected String findAllPaginationQuery(String tableName, int currentPage, int recordsOnPage){

        return QueryOperator.SELECT + STAR_SYMBOL + QueryOperator.FROM + SPACE + tableName + SPACE +
                currentPage + COMMA + recordsOnPage;

    }

    protected String findByQuery(String tableName, String column, Object value){

        if (value instanceof String) {

            return QueryOperator.SELECT + STAR_SYMBOL + QueryOperator.FROM + SPACE + tableName +
                    SPACE + QueryOperator.WHERE + SPACE + column + EQUALS + QUOTATION_MARK + value + QUOTATION_MARK;

        } else if (value instanceof Integer) {

            return QueryOperator.SELECT + STAR_SYMBOL + QueryOperator.FROM + SPACE + tableName +
                    SPACE + QueryOperator.WHERE + SPACE + column + EQUALS + value;
        }
        return null;
    }

    protected String findByManyToManyQuery(String tableNameFrom, String tableNameWhere, String tableNameManyToMany,
                                           String id, String idManyToManyFrom, String idManyToManyWhere, int whereValue){

        return QueryOperator.SELECT + STAR_SYMBOL + QueryOperator.FROM + SPACE + tableNameFrom + SPACE +
                QueryOperator.INNER + SPACE + QueryOperator.JOIN + SPACE + tableNameManyToMany + SPACE + QueryOperator.ON + SPACE +
                tableNameFrom + DOT + id + EQUALS + tableNameManyToMany + DOT + idManyToManyFrom + SPACE +
                QueryOperator.INNER + SPACE + QueryOperator.JOIN + SPACE + tableNameWhere + SPACE + QueryOperator.ON +
                SPACE + tableNameManyToMany + DOT + idManyToManyWhere + EQUALS + tableNameWhere + DOT + id + SPACE +
                QueryOperator.WHERE + SPACE + tableNameWhere + DOT + id + EQUALS + whereValue;

    }

    protected void rollback(Connection connection, Logger logger) throws DAOException {

        if (connection != null) {

            try {

                connection.rollback();

            } catch (SQLException e) {

                logger.error("Unable to rollback transaction", e);
                throw new DAOException("Unable to rollback transaction", e);

            }
        }
    }

    protected void reliaseConnection(Connection connection, Logger logger) throws DAOException {

        if (connection != null) {

            try {

                connection.setAutoCommit(true);
                connection.close();

            } catch (SQLException e) {

                logger.error("Unable to return connection to connection pool", e);
                throw new DAOException("Unable to return connection to connection pool", e);

            }
        }
    }

}
