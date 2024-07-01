package DAL;

import Domain.Obejects.TransportDocument;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransportsDAO implements IDAO<TransportDocument>{
    private Connection connection;

    public TransportsDAO(){
        this.connection = DB_Connector.getConnection();
    }


    @Override
    public List<JsonObject> SELECT_ALL()throws SQLException  {

        List<JsonObject> all_transports = new ArrayList<>();

        JsonObject j = new JsonObject();

        String sql = "SELECT Transportation_ID, Details, Status FROM Transportations";

        PreparedStatement transport = connection.prepareStatement(sql);

        ResultSet rs = transport.executeQuery();
        while (rs.next()) {
            j.addProperty("Transportation ID", rs.getString("Transportation_ID"));
            j.addProperty("Details", rs.getString("Details"));
            j.addProperty("Status", rs.getString("Status"));

            all_transports.add(j);
        }

        return all_transports;
    }
    @Override
    public void INSERT(JsonObject j) throws SQLException {

        String sql = "INSERT INTO Transportations (Transportation_ID, Details, Status) VALUES(?, ?, ?)";

        PreparedStatement TransportDocument = connection.prepareStatement(sql);

        TransportDocument.setInt(1, j.get("Transportation ID").getAsInt());
        TransportDocument.setString(2, j.get("Details").getAsString());
        TransportDocument.setString(3, j.get("Status").getAsString());

        TransportDocument.executeUpdate();
    }
    @Override
    public void UPDATE(JsonObject j)throws SQLException {

    }
    @Override
    public void DELETE(JsonObject j)throws SQLException {

        String sql = "DELETE FROM Transportations WHERE Transportation_ID = ?";

        PreparedStatement TransportDocument = connection.prepareStatement(sql);

        TransportDocument.setInt(1, j.get("Transportation ID").getAsInt());
        TransportDocument.executeUpdate();
    }

}
