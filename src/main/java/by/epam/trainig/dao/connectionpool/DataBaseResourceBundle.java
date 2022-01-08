package by.epam.trainig.dao.connectionpool;

import java.util.Enumeration;
import java.util.ResourceBundle;

public final class DataBaseResourceBundle {

    private static DataBaseResourceBundle dataBaseResourceBundle = new DataBaseResourceBundle();

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    private DataBaseResourceBundle() {
    }

    public static DataBaseResourceBundle getDataBaseResourceBundle(){
        return dataBaseResourceBundle;
    }

    public String getValue(String propertiesName){
        return resourceBundle.getString(propertiesName);
    }


}
