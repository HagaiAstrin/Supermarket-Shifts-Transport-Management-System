package DAL.DALTransport;

import Domain.DomainTransport.Obejects.Site;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SitesDAO implements IDAO<Site>{
    private Connection connection;


    /**
     * SitesDAO Constructor
     */
    public SitesDAO(){
        this.connection = DB_Connector.getTransportationConnection();
    }

    /**
     * Return List of JsonObject that SELECT all the Sites
     */
    @Override
    public List<JsonObject> SELECT_ALL()throws SQLException  {

        List<JsonObject> all_sites = new ArrayList<>();

        String sql = "SELECT * FROM Sites";

        PreparedStatement site = connection.prepareStatement(sql);

        ResultSet rs = site.executeQuery();

        while (rs.next()) {
            JsonObject j = new JsonObject();

            j.addProperty("Name", rs.getString("Name"));
            j.addProperty("Address", rs.getString("Address"));
            j.addProperty("Phone number", rs.getString("Phone_number"));
            j.addProperty("Contact", rs.getString("Contact"));
            j.addProperty("Shipping area", rs.getString("Shipping_area"));
            j.addProperty("Type", rs.getString("Type"));
            j.addProperty("Site ID", rs.getString("Site_ID"));

            all_sites.add(j);
        }

        return all_sites;
    }
    /**
     * INSERT into the DB new Site
     */
    @Override
    public void INSERT(JsonObject j) throws SQLException {

        String sql = "INSERT INTO Sites(Name, Address, Phone_number, Contact, " +
                     "Shipping_area, Type, Site_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement site = connection.prepareStatement(sql);

        site.setString(1, j.get("Name").getAsString());
        site.setString(2, j.get("Address").getAsString());
        site.setString(3, j.get("Phone number").getAsString());
        site.setString(4, j.get("Contact").getAsString());
        site.setString(5, j.get("Shipping area").getAsString());
        site.setString(6, j.get("Type").getAsString());
        site.setInt(7, j.get("Site ID").getAsInt());

        site.executeUpdate();

        if (j.get("Type").getAsString().equals("Store")){
            DB_Creator.copyDatabase("dev/Data/Haiffa.db", j.get("Name").getAsString());
        }
    }
    @Override
    public void UPDATE(JsonObject j)throws SQLException {
    }
    /**
     * DELETE a Site in the DB
     */
    @Override
    public void DELETE(JsonObject j)throws SQLException {

        String sql = "DELETE FROM Sites WHERE Site_ID = ?";

        PreparedStatement site = connection.prepareStatement(sql);

        site.setInt(1, j.get("Site ID").getAsInt());
        site.executeUpdate();
    }


    // Only SiteDAO Methods:

    /**
     * Return True if there is a Stocker in the Store, on the Specific day and in the Specific time
     */
    public static boolean IS_STORE_KEEPER(String name, String day, String time) throws SQLException {

        Connection connection_store = DB_Connector.getStoreConnection(name);

        String sql = "SELECT * FROM template";

        PreparedStatement site = connection_store.prepareStatement(sql);

        ResultSet rs = site.executeQuery();

        if (time.equals("2")) rs.next();

        List<List<String>> rowArray = new ArrayList<>();
        for (String column : new String[]{"Sun", "Mon", "Tue", "Wed", "Thu"}) {
            List<String> cellArray = new ArrayList<>();
            String cellValue = rs.getString(column);
            if (cellValue != null && !cellValue.isEmpty()) {
                String[] values = cellValue.split(",");
                for (String value : values) {
                    cellArray.add(value); // Add the split values to the list
                }
            }
            rowArray.add(cellArray); // Add the cellArray to the rowArray
        }

        // Convert the day to an integer
        int cur_day = Integer.parseInt(day);

        // Check if the specified day is within the valid range
        if (cur_day < 1 || cur_day > rowArray.size()) {
            System.out.println("Invalid day: " + cur_day);
            return false;
        }

        // Get the list of scheduled keepers for the specified day
        List<String> check_stock_keeper = rowArray.get(cur_day - 1);

        // Check if there are any keepers scheduled for the specified day and time
        return check_stock_keeper != null && !check_stock_keeper.isEmpty();
    }
}
