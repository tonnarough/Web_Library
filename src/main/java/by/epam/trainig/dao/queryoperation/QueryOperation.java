package by.epam.trainig.dao.queryoperation;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.dao.queryoperation.Impl.QueryOperationImpl;
import by.epam.trainig.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface QueryOperation {

    void update(Table table, String updColumn, Object updValue, String whereColumn, Object whereValue);

    <T extends Entity> List<T> findAll(Table table, Class<T> type) throws SQLException;

    void delete(Table table, String column, Object values);

    <T extends Entity> void create(List<String> entityColumns, Table table, T entity, Class<T> type);

    <T extends Entity> void create(List<String> entityColumns, Table table, T entity, Class<T> type, Connection connection);

    <T extends Entity> void create(String sqlQuery, int firstId, int secondId, Connection connection);

    <T extends Entity> Optional<T> findBy(Table table, String column, Object value, Class<T> type);

    <T extends Entity> List<T> findByParameter(Table table, String column, Object value, Class<T> type);

    <T extends Entity> List<T> findWithSql(String sqlQuery, Class<T> type);

    int getCountOfRows();

    static QueryOperationImpl getInstance(){
        return QueryOperationImpl.getInstance();
    }

}
