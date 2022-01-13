package by.epam.trainig.context;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.user.User;

import java.util.*;
import java.util.stream.Collectors;

public class  DatabaseEntityContext {

    private Class[] classArray = {User.class};
    private Map<String, List<String>> entityDatabaseMap = new HashMap<>();
    private List<String> entityColumns = new ArrayList<>();
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
        for (int i = 0; i < classArray.length; i++) {
            Class clazz = classArray[i];
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
        entityColumns = entityDatabaseMap.get(tableName);
        return entityColumns;
    }

}

