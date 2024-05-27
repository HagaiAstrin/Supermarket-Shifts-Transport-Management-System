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

    /**
     * adding a new region to the Map
     * @param Shipping_area - the name of the region
     */
    public static void add_Shipping_area(String Shipping_area){
        Map<String, Map<String, Site>> map = new HashMap<>();
        map.put("Store",new HashMap<>());
        map.put("Supplier",new HashMap<>());
        manager_Map.put(Shipping_area, map);
    }

    /**
     * put a new Store in the Data Structure
     * @param Shipping_area - the Shipping_area of the Store
     * @param store - the Store
     */
    public static void add_Store(String Shipping_area, Store store){
        if(!manager_Map.containsKey(Shipping_area)) {
            add_Shipping_area(Shipping_area);
        }
        Map<String, Site> map = manager_Map.get(Shipping_area).get("Store");
        if(!map.containsKey(store.getName())){
            manager_Map.get(Shipping_area).get("Store").put(store.getName(), store);
        }
    }

    /**
     * put a new Supplier in the Data Structure
     * @param Shipping_area - the region of the Supplier
     * @param sp - the Supplier
     */
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

    /**
     * adding a Driver to the Array List
     * @param dr - Driver type
     */
    public static void add_Driver(Driver dr){
        drivers.add(dr);}

    /**
     * adding Transport to the Array List
     * @param tran - Transport type
     */
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
}
