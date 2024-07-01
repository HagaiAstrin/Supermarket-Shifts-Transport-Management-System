package DAL;

import Domain.Obejects.Truck;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;


public class TrucksDAO implements IDAO<Truck>{

    private Connection connection;

    public TrucksDAO(){
        this.connection = DB_Connector.getConnection();
    }

    @Override
    public List<JsonObject> SELECT_ALL()throws SQLException  {

        List<JsonObject> all_trucks = new ArrayList<>();

        JsonObject j = new JsonObject();

        String sql = "SELECT Licence_number, Licence_Level, Net_Weight, " +
                     "Max_Weight, Status FROM Trucks";

        PreparedStatement truck = connection.prepareStatement(sql);

        ResultSet rs = truck.executeQuery();
        while (rs.next()) {
            j.addProperty("Licence number", rs.getString("Licence_number"));
            j.addProperty("Licence Level", rs.getString("Licence_Level"));
            j.addProperty("Net Weight", rs.getString("Net_Weight"));
            j.addProperty("Max Weight", rs.getString("Max_Weight"));
            j.addProperty("Status", rs.getString("Status"));

            all_trucks.add(j);
        }

        return all_trucks;
    }
    @Override
    public void INSERT(JsonObject j) throws SQLException {

        String sql = "INSERT INTO Trucks(Licence_number, Licence_Level, Net_Weight" +
                     ", Max_Weight, Status) VALUES(?, ?, ?, ?, ?)";

        PreparedStatement truck = connection.prepareStatement(sql);

        truck.setString(1, j.get("Licence number").getAsString());
        truck.setString(2, j.get("Licence Level").getAsString());
        truck.setString(3, j.get("Net Weight").getAsString());
        truck.setString(4, j.get("Max Weight").getAsString());
        truck.setString(5, j.get("Status").getAsString());

        truck.executeUpdate();
    }
    @Override
    public void UPDATE(JsonObject j)throws SQLException {

    }
    @Override
    public void DELETE(JsonObject j)throws SQLException {

        String sql = "DELETE FROM Trucks WHERE Licence_number = ?";

        PreparedStatement truck = connection.prepareStatement(sql);

        truck.setString(1, j.get("Licence number").getAsString());
        truck.executeUpdate();
    }
}
