package by.epam.trainig.context;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.user.*;

import java.util.*;
import java.util.stream.Collectors;

public class  DatabaseEntityContext {

    @SuppressWarnings("rawtypes")
    private final Class[] classArray = {User.class, UserDetail.class, Subscription.class, SubscribtionType.class, Role.class};
    private final Map<String, List<String>> entityDatabaseMap = new HashMap<>();
    private static DatabaseEntityContext databaseEntityContext;

    private DatabaseEntityContext() {
        initDatabaseContext();
    }

    public static DatabaseEntityContext getDatabaseEntityContext() {
        if (databaseEntityContext == null) {
            databaseEntityContext = new DatabaseEntityContext();
        }
        return databaseEntityContext;
    }

    private void initDatabaseContext() {
        String tableName;
        //noinspection rawtypes
        for (Class clazz : classArray) {
            Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
            tableName = tableAnnotation.name();
            List<String> collect = Arrays.stream(clazz.getDeclaredFields()).map(field -> {
                Column columnAnnotation = field.getAnnotation(Column.class);
                return columnAnnotation.name();
            }).collect(Collectors.toList());
            entityDatabaseMap.put(tableName, collect);
        }
    }

    public List<String> getDatabaseContext(String tableName) {
        return entityDatabaseMap.get(tableName);
    }

}

