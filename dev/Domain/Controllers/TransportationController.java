package Domain.Controllers;

import Domain.Controllers.DataController;
import Domain.Obejects.*;
import com.google.gson.JsonObject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TransportationController {
    public static Transportation Transport;

    public static int getNumberId(){
        if ((DataController.getAmount("Transport")) == 0)
            return 1000;

        return (DataController.getAmount("Transport")) + 1000;
    }


    public static void AddItem(JsonObject j, String s){
        Transport.add_Item(j, s);
    }
    public static void AddDocument(JsonObject j) throws SQLException {

        String site = j.get("Site").getAsString();
        String type = j.get("Type").getAsString();
        String area = j.get("Area").getAsString();

        Site s = DataController.getSite(site);

        Map<Item, Integer> new_map = new HashMap<>(Transport.getItems());
        if (!new_map.isEmpty()) {
            Document d = new Document(s, new_map);

            Transport.clear_Items();
            Transport.add_Document(d);
        }
    }


    public static void createTransport(JsonObject j) throws SQLException {

        String source = j.get("Source").getAsString();
        String date = j.get("Date").getAsString();
        String leaving_time = j.get("Leaving time").getAsString();

        Truck truck = DataController.getTruck(j.get("Truck").getAsString());
        Driver driver = DataController.getDriver(j.get("Driver").getAsString());

        Transport = new Transportation(truck, driver, source, date, leaving_time);
    }
    public static int checkTransport() throws SQLException {

        int result = Transport.WeightCheck();

        if (result == 0) {
            Transport.setId(getNumberId() + 1);
            Transport.getDriver().setStatus("Waiting");
            Transport.getDriver().createRoute(Transport.getTargets());
            Transport.getDriver().setTruckLicenceNumber(Transport.getTruck().getLicence_number());
            Transport.getDriver().setTransportID(Transport.getId());
            Transport.getTruck().setStatus("Waiting");
            TransportDocument t = new TransportDocument(Transport.getStatus(), Transport.getDetails(),Transport.getId());
            DataController.AddTransportDocument(t);
            Transport = null;
            return 0;
        }
        return result;
    }

    public static JsonObject chooseItems(){
        JsonObject j = new JsonObject();
        int count = 1;

        for (Map.Entry<Item, Integer> iter: Transport.getAll_transport_items().entrySet()){
            if (iter.getValue() != 0)
                j.addProperty(String.valueOf(count++),iter.getKey().to_string());
        }
        return j;
    }
    /**
     * Return the number of a specific item in the Order
     * @param s - String Type represent the Item name
     */
    public static int amountOfItems(String s) {

        for (Map.Entry<Item, Integer> iter: Transport.getAll_transport_items().entrySet()){
            if (iter.getKey().to_string().equals(s))
                return iter.getValue();
        }
        return 0;
    }

}
