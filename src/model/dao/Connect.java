package model.dao;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CommunalService";
    private static final String DB_USER = "root";
    private static final  String DB_PASSWORD = "1234";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Can't connect to the database: " + e.getMessage());
        }
    }
}
