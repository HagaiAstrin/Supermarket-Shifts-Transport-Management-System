package DAL.DALTransport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connector {
    private static Connection TransportationConnection;
    private static Connection StoreConnection;
    private static Connection DriversConnection;


    /**
     * Starting the Connection to the DB
     * @return Connection
     */
    public static Connection getTransportationConnection() {
        if (TransportationConnection == null) {
            try {
                // Update the path to your .db file
                String url = "jdbc:sqlite:dev/Data/Transportation.db";
                TransportationConnection = DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return TransportationConnection;
    }
    public static Connection getStoreConnection(String name){
        try {
            // Update the path to your .db file
            String url = "jdbc:sqlite:dev/Data/" + name + ".db";
            StoreConnection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return StoreConnection;
    }
    public static Connection getDriversConnection(){
        try {
            // Update the path to your .db file
            String url = "jdbc:sqlite:dev/Data/Drivers.db";
            DriversConnection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DriversConnection;
    }
}