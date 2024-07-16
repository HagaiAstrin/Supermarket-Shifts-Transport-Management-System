package DAL.DALTransport;

import Domain.DomainTransport.Obejects.Driver;
import com.google.gson.JsonObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriversDAO implements IDAO<Driver> {
    private Connection connection;
    private static final int amount_shifts = 2;  // Assuming there are 2 shifts
    private static final int amount_days = 5;


    /**
     * DriversDAO Constructor
     */
    public DriversDAO() {
        this.connection = DB_Connector.getTransportationConnection();
    }

    /**
     * Return List of JsonObject that SELECT all the Drivers
     */
    @Override
    public List<JsonObject> SELECT_ALL() throws SQLException {

        List<JsonObject> all_drivers = new ArrayList<>();

        String sql = "SELECT * FROM Drivers";

        PreparedStatement driver = connection.prepareStatement(sql);

        ResultSet rs = driver.executeQuery();

        while (rs.next()) {
            JsonObject j = new JsonObject();

            j.addProperty("Name", rs.getString("Name"));
            j.addProperty("Licence", rs.getString("Licence"));
            j.addProperty("Password", rs.getString("Password"));
            j.addProperty("Status", rs.getString("Status"));
            j.addProperty("Route", rs.getString("Route"));
            j.addProperty("Transport ID", rs.getString("Transport_ID"));
            j.addProperty("Truck Licence Number", rs.getString("Truck_Licence_Number"));
            j.addProperty("Driver ID", rs.getString("Driver_ID"));

            all_drivers.add(j);
        }

        return all_drivers;
    }

    /**
     * INSERT onto the DB new Driver
     */
    @Override
    public void INSERT(JsonObject j) throws SQLException {
        String sql = "INSERT INTO Drivers(Name, Licence, Password, Status, Route, " +
                "Transport_ID, Truck_Licence_Number, Driver_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement driver = connection.prepareStatement(sql);

        driver.setString(1, j.get("Name").getAsString());
        driver.setString(2, j.get("Licence").getAsString());
        driver.setString(3, j.get("Password").getAsString());
        driver.setString(4, j.get("Status").getAsString());
        driver.setString(5, j.get("Route").getAsString());
        driver.setInt(6, j.get("Transport ID").getAsInt());
        driver.setString(7, j.get("Truck Licence Number").getAsString());
        driver.setInt(8, j.get("Driver ID").getAsInt());

        driver.executeUpdate();
    }

    /**
     * Update a Driver in the DB
     */
    @Override
    public void UPDATE(JsonObject j) throws SQLException {

        String sql = "UPDATE Drivers SET Status = ?, Route = ?, Transport_ID = ?, " +
                "Truck_Licence_Number = ? WHERE Driver_ID = ?";

        PreparedStatement driver = connection.prepareStatement(sql);

        driver.setString(1, j.get("Status").getAsString());
        driver.setString(2, j.get("Route").getAsString());
        driver.setString(3, j.get("Transport ID").getAsString());
        driver.setString(4, j.get("Truck Licence Number").getAsString());
        driver.setString(5, j.get("Driver ID").getAsString());

        driver.executeUpdate();
    }

    /**
     * DELETE a Driver in the DB
     */
    @Override
    public void DELETE(JsonObject j) throws SQLException {

        String sql = "DELETE FROM Drivers WHERE Driver_ID = ?";

        PreparedStatement driver = connection.prepareStatement(sql);

        driver.setInt(1, j.get("Driver ID").getAsInt());
        driver.executeUpdate();
    }


    // Only DriverDAO Methods:
    public static void CREATE_DRIVER_PREFERENCES(int id) throws SQLException {

        String createTableSQL = String.format(
                "CREATE TABLE IF NOT EXISTS \"%s\" (Sun INTEGER, Mon INTEGER, The INTEGER, Wen INTEGER, Thu INTEGER);",
                id
        );

        String insertRowSQL = String.format(
                "INSERT INTO \"%s\" (Sun, Mon, The, Wen, Thu) VALUES (1, 1, 1, 1, 1);",
                id
        );
        Connection connection = DB_Connector.getDriversConnection();

        Statement statement = connection.createStatement();

        // Create table
        statement.execute(createTableSQL);

        // Insert two rows
        statement.execute(insertRowSQL);
        statement.execute(insertRowSQL);

        System.out.println("Table created and rows inserted for id: " + id);

    }

    public static String UPDATE_DRIVER_PREFERENCES(String[][] preferences, int id) throws SQLException {

        String sql1 = String.format("UPDATE \"%s\" SET Sun=?, Mon=?, The=?, Wen=?, Thu=? WHERE rowid=1", id);
        String sql2 = String.format("UPDATE \"%s\" SET Sun=?, Mon=?, The=?, Wen=?, Thu=? WHERE rowid=2", id);

        Connection connection = DB_Connector.getDriversConnection();

        PreparedStatement pstmtMorning = connection.prepareStatement(sql1);
        PreparedStatement pstmtEvening = connection.prepareStatement(sql2);

        // Update morning preferences (row 0)
        pstmtMorning.setString(1, preferences[0][0]);
        pstmtMorning.setString(2, preferences[0][1]);
        pstmtMorning.setString(3, preferences[0][2]);
        pstmtMorning.setString(4, preferences[0][3]);
        pstmtMorning.setString(5, preferences[0][4]);
        pstmtMorning.executeUpdate();

        // Update evening preferences (row 1)
        pstmtEvening.setString(1, preferences[1][0]);
        pstmtEvening.setString(2, preferences[1][1]);
        pstmtEvening.setString(3, preferences[1][2]);
        pstmtEvening.setString(4, preferences[1][3]);
        pstmtEvening.setString(5, preferences[1][4]);
        pstmtEvening.executeUpdate();

        return "Your preferences list has been updated";
    }

    public static String[][] GET_DRIVER_PREFERENCES(int id) throws SQLException {

        String[][] preferences = new String[amount_shifts][amount_days];

        String getPreferencesSQL = String.format("SELECT Sun, Mon, The, Wen, Thu FROM \"%s\"", id);

        Connection connection = DB_Connector.getDriversConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(getPreferencesSQL);

        int row = 0;

        while (resultSet.next() && row < amount_shifts) {
            preferences[row][0] = resultSet.getString("Sun");
            preferences[row][1] = resultSet.getString("Mon");
            preferences[row][2] = resultSet.getString("The");
            preferences[row][3] = resultSet.getString("Wen");
            preferences[row][4] = resultSet.getString("Thu");
            row++;
        }
        return preferences;
    }
}
