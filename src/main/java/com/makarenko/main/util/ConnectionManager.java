package com.makarenko.main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.makarenko.main.util.Constants.*;

public final class ConnectionManager {

    public static final String URL_KEY = CONNECTION_URL;
    public static final String USERNAME_KEY = CONNECTION_USER_KEY;
    public static final String PASSWORD_KEY = CONNECTION_PASSWORD_KEY;

    static {
        loadDriver();
    }

    public static Connection open(){
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDriver(){
        try {
            Class.forName(CONNECTION_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(CONNECTION_ERROR);
        }
    }
    private ConnectionManager(){}
}
