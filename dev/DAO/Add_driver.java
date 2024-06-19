package DAO;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Add_driver implements IAddition {
    private static final String URL = "C:\\Users\\יהונתן סגל\\OneDrive - post.bgu.ac.il\\שולחן העבודה\\Studies - " +
                                      "degree\\AA - Second Year\\Second Semester\\Analysis and Design of Software " +
                                      "Systems\\Assignments\\Practical Assignment 2\\MySQLite\\mydatabase";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void add(JsonObject j) {

        int id = j.get("id").getAsInt();
        String name = j.get("name").getAsString();
        int worker_num = j.get("worker number").getAsInt();
        String license = j.get("license").getAsString();
        String password = j.get("password").getAsString();


        String sql = "INSERT INTO Trucks (id, name, worker_num, license, password" +
                     "availability, hold, using_truck_licence_number, transport_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, worker_num);
            pstmt.setString(4, license);
            pstmt.setString(5, password);

            pstmt.executeUpdate();
            System.out.println("Driver added successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
