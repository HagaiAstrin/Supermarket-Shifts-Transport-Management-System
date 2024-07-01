package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connector {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Update the path to your .db file
                String url = "jdbc:sqlite:dev/DB/";
                connection = DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}