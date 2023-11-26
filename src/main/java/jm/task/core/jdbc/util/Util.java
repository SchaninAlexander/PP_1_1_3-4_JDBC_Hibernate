package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbtest";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "admin";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Соединение установлено");
        } catch (ClassNotFoundException  | SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка установки соединения");
        }
        return connection;
    }

}

