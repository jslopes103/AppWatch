package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {


    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION = "jdbc:mysql://127.0.0.1:3306/appwatch";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

   
    public static Connection getConnection() throws SQLException {
    	Connection connection = null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            return connection;
        }


        try {
            connection = DriverManager.getConnection(CONNECTION, USER, PASSWORD);
            return connection;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return connection;
    }
}

