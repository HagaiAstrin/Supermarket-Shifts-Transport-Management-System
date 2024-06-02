package domain;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DataStructManager {
    public static Map<String, Map<String, Map<String, Site>>> manager_Map = new HashMap<>();
    public static ArrayList<Truck> trucks = new ArrayList<>();
    public static ArrayList<Driver> drivers = new ArrayList<>();
    public static ArrayList<Transport> transports = new ArrayList<>();
    public static ArrayList<Document> documents = new ArrayList<>();
    public static Map<Item, Integer> items = new HashMap<>();
    public static Map<Item, Integer> all_items = new HashMap<>();
    public static double current_max_transport;
    private static int count_good_transport = 1000;



//    Driver's methods:

    /**
     * Adding Driver to DataStruct
     * @param j - JsonObject argument
     */
    public static void add_driver(JsonObject j){

        String name = j.get("Name").getAsString();
        String licence = j.get("Licence").getAsString();
        String password = j.get("Password").getAsString();
        Driver new_driver = new Driver(name, licence, password);
        drivers.add(new_driver);

    }
    /**
     * Checking name and password of the driver
     * @param j - JsonObject argument
     * @return String represent of the Driver
     */
    public static String check_driver(JsonObject j){
        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get("Password").getAsString().equals(driver.getPassword())) {
                return driver.getName();
            }
        }
        return null;
    }
    public static StringBuilder print_driver_doc(JsonObject j){
        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get("Password").getAsString().equals(driver.getPassword())) {
                return driver.getList();
            }
        }
        return null;
    }
    /**
     * Updating that the Driver comes back
     * @param j - JsonObject argument
     */
    public static String update_back_driver(JsonObject j){
        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get
                    ("Password").getAsString().equals(driver.getPassword())) {
                if (driver.getTran() != null && !driver.isAvailability()){
                    driver.setAvailability(true);
                    driver.getUsing_truck().setAvailability(true);
                    driver.setTran(null);
                    driver.setList(null);
                    return ("\nWelcome back!\n");
                }
                return ("\nYou can't report back because you didnt made Transportation!\n");
            }
        }
        return ("\nYou are not exist in the system!\n");
    }
    /**
     * Updating that the Driver leaves
     * @param j - JsonObject argument
     */
    public static String update_leaving_driver(JsonObject j){
        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get
                    ("Password").getAsString().equals(driver.getPassword())) {
                if (driver.getTran() == null) {
                    return ("You didnt assigned to any Transportation!");
                }
                else {
                    driver.setAvailability(false);
                    driver.getUsing_truck().setAvailability(false);
                    driver.getUsing_truck().setHold(false);
                    driver.setHold(false);
                    driver.getTran().setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    driver.getTran().setLeaving_time(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    transports.add(driver.getTran());
                    return ("Have a good trip!");
                }
            }
        }
        return ("You are not exist in the system!");
    }


//    Addition methods:
    /**
     * Adding new Shipping_area to manager_Map
     * @param Shipping_area - String argument
     */
    public static void add_Shipping_area(String Shipping_area){
        Map<String, Map<String, Site>> map = new HashMap<>();
        map.put("Store",new HashMap<>());
        map.put("Supplier",new HashMap<>());
        manager_Map.put(Shipping_area, map);
    }
    /**
     * Adding new site in specific Shipping_area to manager_Map
     * @param j - JsonObject argument
     */
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
    /**
     * Adding Truck to DataStruct
     * @param j - JsonObject argument
     */
    public static void add_truck(JsonObject j){

        String n = j.get("Licence number").getAsString();
        String l = j.get("Licence level").getAsString();
        double net = j.get("Net weight").getAsDouble();
        double max = j.get("Max weight").getAsDouble();
        Truck new_truck = new Truck(n, l, net, max);
        trucks.add(new_truck);

    }


//    Creation methods:
    /**
     * Create new Item list to order
     * @param j - JsonObject argument
     */
    public static void create_items_list(JsonObject j, String s) {

        switch (s){
            case "Supplier": {

                String name = j.get("Name").getAsString();

                double weight = j.get("Weight").getAsDouble();
                int amount = j.get("Amount").getAsInt();

                Item new_item = new Item(name, weight);
                items.put(new_item, amount);

                try{
                    all_items.get(new_item);
                    all_items.put(new_item, all_items.get(new_item) + amount);
                }
                catch (Exception e){
                    all_items.put(new_item, amount);
                }
                break;
            }
            case "Store": {
                for (Map.Entry<Item, Integer> iter: all_items.entrySet()){
                    if (iter.getKey().to_string().equals(j.get("Item").getAsString())) {
                        items.put(iter.getKey(), j.get("Amount").getAsInt());
                        iter.setValue(iter.getValue() - j.get("Amount").getAsInt());
                    }
                }
            }
        }
    }
    /**
     * Create new Document
     * @param j - JsonObject argument
     */
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
    /**
     * Create Transportation
     * @param j - JsonObject argument
     * @return 0 if the transportation is good to go, 1 if there is still Items in the Truck, 2 if there is Over Weight
     */
    public static int create_transportation(JsonObject j){

        String source = j.get("Source").getAsString();

        for (Driver d: drivers){
            if (d.to_String().equals(j.get("Driver").getAsString())){
                for (Truck t: trucks){
                    if (t.to_String().equals(j.get("Truck").getAsString())){
                        Transport new_transport = new Transport(t, d, source, documents);
                        int result = new_transport.Is_Over_Weight();
                        current_max_transport = new_transport.get_transport_Max_weight();
                        if (result == 0) {
                            new_transport.setId(count_good_transport++);
                            all_items.clear();
                            d.setList(documents);
                            documents.clear();
                            d.setHold(true);
                            t.setHold(true);
                            d.setUsing_truck(t);
                            d.setTran(new_transport);
                            return 0;
                        }
                        return result;
                    }
                }
            }
        }
        return 2;
    }
//    Selection methods:
    /**
     * Choose a Truck from DataStruct
     * @return JsonObject represent the truck
     */
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
    /**
     * Choose a Driver from DataStruct
     * @param truck - String argument represent the selected Truck
     * @return JsonObject of all the drivers who can drive in that Truck
     */
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
    /**
     * Choose Area from DataStruct
     * @return JsonObject of all the area in the DataStruct
     */
    public static JsonObject choose_area(){
        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Map<String, Map<String, Site>>> iter : manager_Map.entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getKey());
        }
        return j;
    }
    /**
     * Choose Supplier from DataStruct
     * @param area - the selected Area
     * @return JsonObject represent the all the Supplier inside the Area
     */
    public static JsonObject choose_supplier(String area){
        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Site> iter : DataStructManager.manager_Map.get(area).get("Supplier").entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getValue().to_string());
        }
        return j;
    }
    /**
     * Choose Store from DataStruct
     * @param area - the selected Area
     * @return JsonObject represent the all the Store inside the Area
     */
    public static JsonObject choose_store(String area){
        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Site> iter : DataStructManager.manager_Map.get(area).get("Store").entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getValue().to_string());
        }
        return j;
    }
    public static JsonObject choose_items(){
        JsonObject j = new JsonObject();
        int count = 1;

        for (Map.Entry<Item, Integer> iter: all_items.entrySet()){
            if (iter.getValue() != 0)
                j.addProperty(String.valueOf(count++),iter.getKey().to_string());
        }
        return j;
    }
    public static int amount_items(String s){

        for (Map.Entry<Item, Integer> iter: all_items.entrySet()){
            if (iter.getKey().to_string().equals(s))
                return iter.getValue();
        }
        return 0;
    }


//     Print method:
    /**
     * Transport represent
     * @return JsonObject represent all the transportation in Database
     */
    public static JsonObject All_transport(){
        if(transports.isEmpty()) return null;
        JsonObject j = new JsonObject();
        int count = 1;
        for(Transport tran : transports){
            j.addProperty(String.valueOf(count++), tran.to_String_tran());
        }
        return j;
    }

    public static JsonObject All_Stores(){    ////////////// adding
        JsonObject j = new JsonObject();
        int count = 1;
        for(Document d : documents){
            if(d.getTarget().getType().equals("Store")){
                j.addProperty(String.valueOf(count++), d.to_string());
            }
        }
        return j;
    }
}
