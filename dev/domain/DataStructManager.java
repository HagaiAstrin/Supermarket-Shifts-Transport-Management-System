package domain;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataStructManager {
    public static Map<String, Map<String, Map<String, Site>>> manager_Map = new HashMap<>();
    public static ArrayList<Truck> trucks = new ArrayList<>();
    public static ArrayList<Driver> drivers = new ArrayList<>();
    public static ArrayList<Transport> transports = new ArrayList<>();
    public static ArrayList<Document> documents = new ArrayList<>();
    public static Map<Item, Integer> items = new HashMap<>();



    public static void add_Shipping_area(String Shipping_area){
        Map<String, Map<String, Site>> map = new HashMap<>();
        map.put("Store",new HashMap<>());
        map.put("Supplier",new HashMap<>());
        manager_Map.put(Shipping_area, map);
    }

    public static void add_Store(JsonObject j){

        String name = j.get("name").toString();
        String address = j.get("address").toString();
        int phone = Integer.parseInt(j.get("phone").toString());
        String contact = j.get("contact").toString();
        String Shipping_area = j.get("Shipping area").toString();

        Store store = new Store(name, address, phone, contact, Shipping_area);

        if(!manager_Map.containsKey(Shipping_area)) {
            add_Shipping_area(Shipping_area);
        }
        Map<String, Site> map = manager_Map.get(Shipping_area).get("Store");
        if(!map.containsKey(store.getName())){
            manager_Map.get(Shipping_area).get("Store").put(store.getName(), store);
        }
    }


    public static void add_Supplier(JsonObject j){

        String name = j.get("name").toString();
        String address = j.get("address").toString();
        int phone = Integer.parseInt(j.get("phone").toString());
        String contact = j.get("contact").toString();
        String Shipping_area = j.get("Shipping area").toString();

        Supplier supplier = new Supplier(name, address, phone, contact, Shipping_area);

        if(!manager_Map.containsKey(Shipping_area)){
            add_Shipping_area(Shipping_area);
        }
        Map<String, Site> map = manager_Map.get(Shipping_area).get("Supplier");
        if(!map.containsKey(supplier.getName())){
            manager_Map.get(Shipping_area).get("Supplier").put(supplier.getName(), supplier);
        }
    }

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
    public static void create_items_list(JsonObject j) {

        String name = j.get("name").toString();

        double weight = Double.parseDouble(j.get("weight").toString());
        int amount = Integer.parseInt(j.get("amount").toString());

        Item new_item = new Item(name, weight);

        items.put(new_item, amount);
    }

    public static void create_document(JsonObject j){

        String site = j.get("site").toString();
        String type = j.get("type").toString();
        String area = j.get("area").toString();

        switch (type){
            case "store" -> {
                for (Map.Entry<String, Site> iter : DataStructManager.manager_Map.get(area).get("Supplier").entrySet()){
                    if (iter.toString().equals(site)){
                        Document d = new Document(iter.getValue(), items);
                        items = null;
                        documents.add(d);
                    }
                }
            }
            case "supplier" ->{
                for (Map.Entry<String, Site> iter : DataStructManager.manager_Map.get(area).get("Store").entrySet()){
                    if (iter.toString().equals(site)){
                        Document d = new Document(iter.getValue(), items);
                        items =null;
                        documents.add(d);
                    }
                }
            }
        }
    }

    public static boolean create_transportation(JsonObject j){
        String date = j.get("date").toString();
        String leaving_time = j.get("leaving time").toString();
        String source = j.get("source").toString();

        for (Driver d: drivers){
            if (d.to_String().equals(j.get("driver").toString())){
                for (Truck t: trucks){
                    if (t.to_String().equals(j.get("truck").toString())){
                        Transport new_transport = new Transport(date, leaving_time,t,d,source);
                        return new_transport.is_Weight_Good();
                    }
                }
            }
        }
        return false;
    }

    public static JsonObject choose_truck(){

        JsonObject j = new JsonObject();
        int count = 1;
        for (Truck a:trucks){
            if (a.isAvailability()){
                j.addProperty(String.valueOf(count), a.to_String());
                count++;
            }
        }

        j.addProperty("count", count);
        return j;
    }

    public static JsonObject choose_driver(){

        JsonObject j = new JsonObject();
        int count = 1;
        for (Driver d:drivers){
            if (d.isAvailability()){
                j.addProperty(String.valueOf(count), d.to_String());
                count++;
            }
        }

        j.addProperty("count", count);
        return j;
    }

    public static JsonObject choose_area(){
        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Map<String, Map<String, Site>>> iter : manager_Map.entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getKey());
        }
        return j;
    }
    public static JsonObject choose_supplier(String area){
        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Site> iter : DataStructManager.manager_Map.get(area).get("Supplier").entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getKey());
        }
        return j;
    }

    public static JsonObject choose_store(String area){
        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Site> iter : DataStructManager.manager_Map.get(area).get("Store").entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getKey());
        }
        return j;
    }

}
