package domain;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataStructManager {
    public static Map<String, Map<String, Map<String, Site>>> manager_Map = new HashMap<>();
    public static ArrayList<Truck> trucks;
    public static ArrayList<Driver> drivers;
    public static ArrayList<Transport> transports;


    public static void add_Shipping_area(String Shipping_area){
        Map<String, Map<String, Site>> map = new HashMap<>();
        map.put("Store",new HashMap<>());
        map.put("Supplier",new HashMap<>());
        manager_Map.put(Shipping_area, map);
    }


    public static void add_Store(String Shipping_area, Store store){
        if(!manager_Map.containsKey(Shipping_area)) {
            add_Shipping_area(Shipping_area);
        }
        Map<String, Site> map = manager_Map.get(Shipping_area).get("Store");
        if(!map.containsKey(store.getName())){
            manager_Map.get(Shipping_area).get("Store").put(store.getName(), store);
        }
    }


    public static void add_Supplier(String Shipping_area, Supplier sp){
        if(!manager_Map.containsKey(Shipping_area)){
            add_Shipping_area(Shipping_area);
        }
        Map<String, Site> map = manager_Map.get(Shipping_area).get("Supplier");
        if(!map.containsKey(sp.getName())){
            manager_Map.get(Shipping_area).get("Supplier").put(sp.getName(), sp);
        }
    }

    public static void add_Truck(Truck tr){
        trucks.add(tr);}

    public static void add_Driver(Driver dr){
        drivers.add(dr);}

    public static void add_Transport(Transport tran){
        transports.add(tran);}

    public static String check_driver(JsonObject j){
        for (Driver driver : drivers) {
            if (j.get("name").toString().equals(driver.getName()) && j.get("password").toString().equals(driver.getPassword())) {
                return driver.getName();
            }
        }
        return null;
    }
    public static void update_back_driver(JsonObject j){
        for (Driver driver : drivers) {
            if (j.get("name").toString().equals(driver.getName()) && j.get("password").toString().equals(driver.getPassword())) {
                driver.setAvailability(true);
                driver.getUsing_truck().setAvailability(true);
            }
        }
    }

    public static void update_leaving_driver(JsonObject j){
        for (Driver driver : drivers) {
            if (j.get("name").toString().equals(driver.getName()) && j.get("password").toString().equals(driver.getPassword())) {
                driver.setAvailability(false);
                driver.getUsing_truck().setAvailability(false);
            }
        }
    }
    public static void add_driver(JsonObject j){

        String name = j.get("name").toString();
        String licence = j.get("licence").toString();
        String password = j.get("password").toString();

        Driver new_driver = new Driver(name, licence, password);

        drivers.add(new_driver);
    }
    public static void add_truck(JsonObject j){

        String licence_level = j.get("licence level").toString();

        char l = licence_level.charAt(0);
        int n = Integer.parseInt(j.get("licence number").toString());
        double net = Double.parseDouble(j.get("net weight").toString());
        double max = Double.parseDouble(j.get("max weight").toString());

        Truck new_truck = new Truck(n, l, net, max);

        trucks.add(new_truck);
    }
    public static void add_store(JsonObject j){

        String name = j.get("name").toString();
        String licence = j.get("licence").toString();
        String password = j.get("password").toString();

        Driver new_driver = new Driver(name, licence, password);

        drivers.add(new_driver);
    }
    public static void add_supplier(JsonObject j){

        String name = j.get("name").toString();
        String licence = j.get("licence").toString();
        String password = j.get("password").toString();

        Driver new_driver = new Driver(name, licence, password);

        drivers.add(new_driver);
    }
    public static JsonObject choose_truck(){

        JsonObject j = new JsonObject();
        int count = 1;
        for (Truck a:trucks){
            if (a.isAvailability()){
                String s = "Press '" + count + "' for - " + a.getLicence_number() + ".";
                j.addProperty(String.valueOf(count), s);
            }
        }
        return j;
    }
    public static void create_document(JsonObject j){

        String name = j.get("name").toString();

        double weight = Double.parseDouble(j.get("weight").toString());
        int amount = Integer.parseInt(j.get("amount").toString());

        Item new_item = new Item(name,weight);
    }
}
