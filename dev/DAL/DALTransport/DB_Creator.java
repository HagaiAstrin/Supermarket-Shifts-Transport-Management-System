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

