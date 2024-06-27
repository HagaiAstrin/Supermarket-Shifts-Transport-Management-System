package Domain;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import static Domain.TransportationController.Transport;

public class DataController {

    /**
     * Adding Driver to DataStruct
     * @param j - JsonObject argument
     */
    public static void AddDriver(JsonObject j){

        String name = j.get("Name").getAsString();
        String licence = j.get("Licence").getAsString();
        String password = j.get("Password").getAsString();
        Driver new_driver = new Driver(name, licence, password, "000-00-000");
        TransportationController.add_new_driver(new_driver);

    }
    /**
     * Adding Truck to DataStruct
     * @param j - JsonObject argument
     */
    public static void AddTruck(JsonObject j){

        String n = j.get("Licence number").getAsString();
        String l = j.get("Licence level").getAsString();
        double net = j.get("Net weight").getAsDouble();
        double max = j.get("Max weight").getAsDouble();
        Truck new_truck = new Truck(n, l, net, max);
        TransportationController.add_new_Truck(new_truck);

    }

    /**
     * Adding new site in specific Shipping_area to manager_Map
     * @param j - JsonObject argument
     */
    public static void AddSite(JsonObject j) {

        String name = j.get("Name").getAsString();
        String address = j.get("Address").getAsString();
        String phone = j.get("Phone number").getAsString();
        String contact = j.get("Contact").getAsString();
        String Shipping_area = j.get("Shipping area").getAsString();
        String type = j.get("Type").getAsString();

        if (!TransportationController.getAllSites().containsKey(Shipping_area)) {
            TransportationController.add_Shipping_area(Shipping_area);
        }

        Site new_site = new Site(name, address, phone, contact, Shipping_area, type);
        TransportationController.add_new_Site(new_site);

    }
}
