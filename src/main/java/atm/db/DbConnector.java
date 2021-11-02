package atm.db;

import java.sql.*;

public class DbConnector {

    private static String URL = "jdbc:mysql://localhost:3306/atm";

    private static String USER = "root";

    private static String PASSWORD = "admin";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return connection;
    }


}