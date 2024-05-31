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
    public static Map<Item, Integer> all_items = new HashMap<>();
    public static double current_max_transport;


    public static void add_Shipping_area(String Shipping_area){
        Map<String, Map<String, Site>> map = new HashMap<>();
        map.put("Store",new HashMap<>());
        map.put("Supplier",new HashMap<>());
        manager_Map.put(Shipping_area, map);
    }
    public static void add_Site(JsonObject j) {

        String name = j.get("Name").getAsString();
        String address = j.get("Address").getAsString();
        String phone = j.get("Phone number").getAsString();
        String contact = j.get("Contact").getAsString();
        String Shipping_area = j.get("Shipping area").getAsString();
        String type = j.get("Type").getAsString();

        if (!manager_Map.containsKey(Shipping_area)) {
            add_Shipping_area(Shipping_area);
        }
        if (type.equals("Store")) {

            Site store = new Site(name, address, phone, contact, Shipping_area, "Store");

            Map<String, Site> map = manager_Map.get(Shipping_area).get("Store");
            if (!map.containsKey(store.getName())) {
                manager_Map.get(Shipping_area).get("Store").put(store.getName(), store);
            }
        } else {
            Site supplier = new Site(name, address, phone, contact, Shipping_area, "Supplier");

            Map<String, Site> map = manager_Map.get(Shipping_area).get("Supplier");
            if (!map.containsKey(supplier.getName())) {
                manager_Map.get(Shipping_area).get("Supplier").put(supplier.getName(), supplier);

            }
        }
    }
    public static String check_driver(JsonObject j){
        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get("Password").getAsString().equals(driver.getPassword())) {
                return driver.getName();
            }
        }
        return null;
    }
    public static String update_back_driver(JsonObject j){
        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get
                    ("Password").getAsString().equals(driver.getPassword())) {
                if (driver.getUsing_truck() != null && !driver.isAvailability()){
                    driver.setAvailability(true);
                    driver.getUsing_truck().setAvailability(true);
                    return ("\nWelcome back!\n");
                }
                return ("\nYou can't report because you are not make Transportation!\n");
            }
        }
        return ("\nYou are not exist in the system!\n");
    }
    public static String update_leaving_driver(JsonObject j){
        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get
                    ("Password").getAsString().equals(driver.getPassword())) {
                if (driver.getUsing_truck() != null && driver.isHold()){
                    driver.setAvailability(false);
                    driver.getUsing_truck().setAvailability(false);
                    driver.getUsing_truck().setHold(false);
                    driver.setHold(false);
                    return ("Have a good trip!");
                }
            }
            return ("You can't report because you are not make Transportation!");
        }
        return ("You are not exist in the system!");
    }
    public static void add_driver(JsonObject j){

        String name = j.get("Name").getAsString();
        String licence = j.get("Licence").getAsString();
        String password = j.get("Password").getAsString();

        Driver new_driver = new Driver(name, licence, password);

        drivers.add(new_driver);
    }
    public static void add_truck(JsonObject j){

        String n = j.get("Licence number").getAsString();
        String l = j.get("Licence level").getAsString();
        double net = j.get("Net weight").getAsDouble();
        double max = j.get("Max weight").getAsDouble();

        Truck new_truck = new Truck(n, l, net, max);

        trucks.add(new_truck);
    }
    public static void create_items_list(JsonObject j) {

        String name = j.get("Name").getAsString();

        double weight = j.get("Weight").getAsDouble();
        int amount = j.get("Amount").getAsInt();

        Item new_item = new Item(name, weight);

        items.put(new_item, amount);
        all_items.put(new_item, amount);

    }
    public static void create_document(JsonObject j){

        String site = j.get("Site").getAsString();
        String type = j.get("Type").getAsString();
        String area = j.get("Area").getAsString();

        for (Map.Entry<String, Site> iter : DataStructManager.manager_Map.get(area).get(type).entrySet()){
            if (iter.getValue().to_string().equals(site)){
                Map<Item, Integer> new_map = new HashMap<>(items);
                Document d = new Document(iter.getValue(), new_map);
                items.clear();
                documents.add(d);
            }
        }
    }
    public static boolean create_transportation(JsonObject j){

        String date = j.get("Date").getAsString();
        String leaving_time = j.get("Leaving time").getAsString();
        String source = j.get("Source").getAsString();

        for (Driver d: drivers){
            if (d.to_String().equals(j.get("Driver").getAsString())){
                for (Truck t: trucks){
                    if (t.to_String().equals(j.get("Truck").getAsString())){
                        Transport new_transport = new Transport(date, leaving_time, t, d, source, documents);
                        boolean result = new_transport.Is_Over_Weight();
                        current_max_transport = new_transport.get_transport_Max_weight();
                        if (result) {
                            documents.clear();
                            d.setHold(true);
                            t.setHold(true);
                            d.setUsing_truck(t);
                            transports.add(new_transport);
                            return true;
                        }
                        return false;
                    }
                }
            }
        }
        return false;
    }
    public static JsonObject choose_truck(){

        JsonObject j = new JsonObject();
        int count = 1;
        for (Truck t:trucks){
            if (t.isAvailability() && !t.isHold()){
                j.addProperty(String.valueOf(count), t.to_String());
                count++;
            }
        }
        return j;
    }
    public static JsonObject choose_driver(String truck){

        JsonObject j = new JsonObject();
        int count = 1;
        for (Truck t:trucks){
            if (truck.equals(t.to_String())){
                for (Driver d:drivers){
                    if (d.isAvailability() && !d.isHold()){
                        if (d.getLicense() >= t.getLicence_level()){
                            j.addProperty(String.valueOf(count), d.to_String());
                            count++;
                        }
                    }
                }
                break;
            }
        }
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
            j.addProperty(String.valueOf(count++), iter.getValue().to_string());
        }
        return j;
    }
    public static JsonObject choose_store(String area){
        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Site> iter : DataStructManager.manager_Map.get(area).get("Store").entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getValue().to_string());
        }
        return j;
    }
    public static void drop_documents(JsonObject j){
        for(int i = 1; i <= j.size(); i++){
            for(Document d : documents){
                if(d.to_string().equals(j.get(String.valueOf(i)).getAsString())){
                    documents.remove(d);
                    break;
                }
            }
        }
    }
    public static void replace_documents(String a){
        int count = 0;
        for(Document d : documents){
            if(d.to_string().equals(a)){
                documents.remove(d);
                Document doc = documents.remove(documents.size() - 1);
                documents.add(count, doc);
                break;
            }
            count++;
        }
    }
    public static void drop_Items(String a){
        boolean bool = false;
        for(Document d : documents) {
            for (Map.Entry<Item, Integer> entry : d.getItem_map().entrySet()) {
                if (d.item_String(entry.getKey()).equals(a)) {
                    d.drop_Item(entry.getKey());
                    if(d.getItem_map().isEmpty()) documents.remove(d);
                    all_items.remove(entry.getKey());
                    bool = true;
                    break;
                }
            }
            if(bool) break;
        }
    }
    public static void drop_Site(String a){
        for(Document d : documents){
            if(d.to_string().equals(a)){
                for(Map.Entry<Item, Integer> entry : d.getItem_map().entrySet()){
                    items.remove(entry.getKey());
                }
                documents.remove(d);
                break;
            }
        }
    }
    public static JsonObject all_transport(){
        if(transports.isEmpty()) return null;
        JsonObject j = new JsonObject();
        int count = 1;
        for(Transport tran : transports){
            j.addProperty(String.valueOf(count++), tran.to_String_tran());
        }
        return j;
    }
}
