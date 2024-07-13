//package DAL.DALTransport;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DB_Connector {
//    private static Connection connection;
//
//
//    /**
//     * Starting the Connection to the DB
//     * @return Connection
//     */
//    public static Connection getConnection() {
//        if (connection == null) {
//            try {
//                // Update the path to your .db file
//                String url = "jdbc:sqlite:dev/Data/TransportationDataBase.db";
//                connection = DriverManager.getConnection(url);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return connection;
//    }
//}

package DAL.DALTransport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connector {
    private static Connection connection;
    private static Connection connection_store;

    /**
     * Starting the Connection to the DB
     * @return Connection
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Update the path to your .db file
                String url = "jdbc:sqlite:dev/Data/Transportation.db";
                connection = DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }



    public static Connection getStoreConnection(String name){
        try {
            // Update the path to your .db file
            String url = "jdbc:sqlite:dev/Data/" + name + ".db";
            connection_store = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection_store;
    }
}