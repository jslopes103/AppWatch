package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//database
public class Database {

	//connection strings
    private static final String CONNECTION = "jdbc:mysql://sql4.freesqldatabase.com:3306/sql4402504";
    private static final String USER = "sql4402504";
    private static final String PASSWORD = "NLxwdWC7az";

    //get connection
    public static Connection getConnection() throws SQLException {
    	Connection connection = null;

        try {
            connection = DriverManager.getConnection(CONNECTION, USER, PASSWORD);
            return connection;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return connection;
    }
}

