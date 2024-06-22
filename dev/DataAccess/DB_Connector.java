package DataAccess;

import java.sql.*;

public class DB_Connector {
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String USER = "root";
    public static final String PASS = "root";

    private static final DB_Connector instance = new DB_Connector();

    //private constructor to avoid client applications to use constructor
    public static DB_Connector getInstance(){
        return instance;
    }

    private DB_Connector() {

    }
    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //return DriverManager.getConnection(URL, USER, PASS);
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root", "root");

            return conn;
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
    /**
     * Test Connection
     */


}
