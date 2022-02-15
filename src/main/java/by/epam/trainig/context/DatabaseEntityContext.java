package by.epam.trainig.context;

import by.epam.trainig.annotation.Column;
import by.epam.trainig.annotation.Table;
import by.epam.trainig.entity.book.Author;
import by.epam.trainig.entity.book.Book;
import by.epam.trainig.entity.book.Genre;
import by.epam.trainig.entity.book.PublishingHouse;
import by.epam.trainig.entity.user.*;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DatabaseEntityContext {

    private static final String regEx = "^mtm_";
    private final Pattern pattern = Pattern.compile(regEx);

    @SuppressWarnings("rawtypes")
    private final Class[] classArray = {
            User.class,
            UserDetail.class,
            Subscription.class,
            SubscriptionType.class,
            Role.class,
            CreditCard.class,
            Book.class,
            Author.class,
            PublishingHouse.class,
            Genre.class
    };

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

    public List<String> getTableColumn(String tableName) {

        final List<String> tableColumns = new ArrayList<>(entityDatabaseMap.get(tableName));

        tableColumns.removeIf(tableColumn -> pattern.matcher(tableColumn).find());

        return tableColumns;

    }

    public List<String> getManyToManyColumn(String tableName) {

        final List<String> manyToManyColumns = new ArrayList<>(entityDatabaseMap.get(tableName));

        manyToManyColumns.removeIf(tableColumn -> !pattern.matcher(tableColumn).find());

        return manyToManyColumns;

    }

}

