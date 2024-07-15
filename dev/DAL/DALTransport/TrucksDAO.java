package DAL.DALTransport;
import Domain.DomainTransport.Obejects.Truck;
import com.google.gson.JsonObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TrucksDAO implements IDAO<Truck>{

    private Connection connection;
    private static final int amount_shifts = 2;  // Assuming there are 2 shifts
    private static final int amount_days = 5;

    /**
     * TrucksDAO Constructor
     */
    public TrucksDAO(){
        this.connection = DB_Connector.getTransportationConnection();
    }

    /**
     * Return List of JsonObject that SELECT all the Trucks
     */
    @Override
    public List<JsonObject> SELECT_ALL()throws SQLException  {

        List<JsonObject> all_trucks = new ArrayList<>();

        String sql = "SELECT * FROM Trucks";

        PreparedStatement truck = connection.prepareStatement(sql);

        ResultSet rs = truck.executeQuery();

        while (rs.next()) {
            JsonObject j = new JsonObject();

            j.addProperty("Licence number", rs.getString("Licence_Number"));
            j.addProperty("Licence level", rs.getString("Licence_Level"));
            j.addProperty("Net weight", rs.getString("Net_Weight"));
            j.addProperty("Max weight", rs.getString("Max_Weight"));
            j.addProperty("Status", rs.getString("Status"));

            all_trucks.add(j);
        }

        return all_trucks;
    }
    /**
     * INSERT onto the DB new Truck
     */
    @Override
    public void INSERT(JsonObject j) throws SQLException {

        String sql = "INSERT INTO Trucks(Licence_number, Licence_Level, Net_Weight" +
                     ", Max_Weight, Status) VALUES(?, ?, ?, ?, ?)";

        PreparedStatement truck = connection.prepareStatement(sql);

        truck.setString(1, j.get("Licence number").getAsString());
        truck.setString(2, j.get("Licence level").getAsString());
        truck.setString(3, j.get("Net weight").getAsString());
        truck.setString(4, j.get("Max weight").getAsString());
        truck.setString(5, j.get("Status").getAsString());

        truck.executeUpdate();
    }
    /**
     * Update a Truck in the DB
     */
    @Override
    public void UPDATE(JsonObject j)throws SQLException {

        String sql = "UPDATE Trucks SET Status = ? WHERE Licence_number = ?";

        PreparedStatement truck = connection.prepareStatement(sql);

        truck.setString(1, j.get("Status").getAsString());
        truck.setString(2, j.get("Licence number").getAsString());

        truck.executeUpdate();
    }
    /**
     * DELETE a Truck in the DB
     */
    @Override
    public void DELETE(JsonObject j)throws SQLException {

        String sql = "DELETE FROM Trucks WHERE Licence_number = ?";

        PreparedStatement truck = connection.prepareStatement(sql);

        truck.setString(1, j.get("Licence number").getAsString());
        truck.executeUpdate();
    }

    public static void CREATE_TRUCK_TABLE (String id) throws SQLException {

        String createTableSQL = String.format(
                "CREATE TABLE IF NOT EXISTS \"%s\" (Sun INTEGER, Mon INTEGER, The INTEGER, Wen INTEGER, Thu INTEGER);",
                id
        );

        String insertRowSQL = String.format(
                "INSERT INTO \"%s\" (Sun, Mon, The, Wen, Thu) VALUES (1, 1, 1, 1, 1);",
                id
        );
        Connection connection = DB_Connector.getTrucksConnection();

        Statement statement = connection.createStatement();

        // Create table
        statement.execute(createTableSQL);

        // Insert two rows
        statement.execute(insertRowSQL);
        statement.execute(insertRowSQL);

        System.out.println("Table created and rows inserted for id: " + id);

    }
    public static String UPDATE_TRUCK_TABLE (String[][] preferences, String id) throws SQLException {

        String sql1 = String.format("UPDATE \"%s\" SET Sun=?, Mon=?, The=?, Wen=?, Thu=? WHERE rowid=1", id);
        String sql2 = String.format("UPDATE \"%s\" SET Sun=?, Mon=?, The=?, Wen=?, Thu=? WHERE rowid=2", id);

        Connection connection = DB_Connector.getTrucksConnection();

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
    public static String[][] GET_TRUCK_TABLE (String id) throws SQLException {

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
