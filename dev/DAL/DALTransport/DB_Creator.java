//package DAL.DALTransport;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class DB_Creator {
//
//    public static void copyDatabase(String sourceDbPath, String targetDbPath) {
//
//        Connection sourceConnection = null;
//        Connection targetConnection = null;
//        Statement sourceStatement = null;
//        Statement targetStatement = null;
//
//        try {
//            // Load the SQLite JDBC driver
//            Class.forName("org.sqlite.JDBC");
//
//            // Create a connection to the source database
//            sourceConnection = DriverManager.getConnection("jdbc:sqlite:" + sourceDbPath);
//
//            // Create a new file for the target database
//            Files.copy(Paths.get(sourceDbPath), Paths.get(targetDbPath));
//
//            // Open a connection to the new database
//            targetConnection = DriverManager.getConnection("jdbc:sqlite:" + targetDbPath);
//
//            System.out.println("Database copied successfully.");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (sourceStatement != null) sourceStatement.close();
//                if (targetStatement != null) targetStatement.close();
//                if (sourceConnection != null) sourceConnection.close();
//                if (targetConnection != null) targetConnection.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        String sourceDbPath = "/mnt/data/Haiffa.db";  // Path to the original database
//        String targetDbPath = "newdatabase.db";  // Path to the new database
//
//        // Call the method to copy the database
//        copyDatabase(sourceDbPath, targetDbPath);
//    }
//}
//
//


package DAL.DALTransport;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB_Creator {

    public static void copyDatabase(String sourceDbPath, String targetDbName) {

        Connection sourceConnection = null;
        Connection targetConnection = null;
        Statement sourceStatement = null;
        Statement targetStatement = null;

        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Create a connection to the source database
            sourceConnection = DriverManager.getConnection("jdbc:sqlite:" + sourceDbPath);

            // Extract the directory path from the source database path
            Path sourcePath = Paths.get(sourceDbPath);
            Path targetPath = sourcePath.getParent().resolve(targetDbName + ".db");

            // Create a new file for the target database
            Files.copy(sourcePath, targetPath);

            // Open a connection to the new database
            targetConnection = DriverManager.getConnection("jdbc:sqlite:" + targetPath.toString());

            System.out.println("Database copied successfully to " + targetPath.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (sourceStatement != null) sourceStatement.close();
                if (targetStatement != null) targetStatement.close();
                if (sourceConnection != null) sourceConnection.close();
                if (targetConnection != null) targetConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

