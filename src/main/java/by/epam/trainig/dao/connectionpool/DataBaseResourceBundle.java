package by.epam.trainig.dao.connectionpool;

import java.util.Enumeration;
import java.util.ResourceBundle;

public final class DataBaseResourceBundle {

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("liquibase");

    private DataBaseResourceBundle() {
    }

    public static DataBaseResourceBundle getDataBaseResourceBundle() {
        return Holder.INSTANCE;
    }

    public String getValue(String propertiesName) {
        return resourceBundle.getString(propertiesName);
    }

    private static class Holder {
        private static final DataBaseResourceBundle INSTANCE = new DataBaseResourceBundle();
    }
}
