package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String url = System.getenv("ENV_DB_URL");
    private static final String userName = System.getenv("ENV_DB_USERNAME");
    private static final String password = System.getenv("ENV_DB_PASSWORD");
    private Connection connection;

    public DataBaseConnection() {
        try {
            this.connection = DriverManager.getConnection(url, userName, password);

            System.out.println("Connected to Microsoft DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        return connection;
    }
}
