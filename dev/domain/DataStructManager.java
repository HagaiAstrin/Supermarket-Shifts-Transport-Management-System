package domain;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DataStructManager {
    private static Map<String, Map<String, Map<String, Site>>> manager_Map = new HashMap<>();
    private static ArrayList<Truck> trucks = new ArrayList<>();
    private static ArrayList<Driver> drivers = new ArrayList<>();
    private static ArrayList<Transport> transports = new ArrayList<>();
    public static ArrayList<Document> documents = new ArrayList<>();
    private static Map<Item, Integer> items = new HashMap<>();
    public static Map<Item, Integer> curr_all_items = new HashMap<>();
    public static double current_max_transport;
    private static int count_good_transport = 1000;

    public static Map<Item, Integer> getItems() {
        return items;
    }

    public static Map<String, Map<String, Map<String, Site>>> getManager_Map() {
        return manager_Map;
    }

    public static ArrayList<Truck> getTrucks() {
        return trucks;
    }

    public static ArrayList<Document> getDocuments() {
        return documents;
    }

    public static void add_new_driver(Driver d){
        drivers.add(d);
    }

    public static void add_new_Truck(Truck t){
        trucks.add(t);
    }

    public static void add_new_doc(Document doc){
        documents.add(doc);
    }

    public static void remove_doc(int i){
        documents.remove(i);
    }

    public static void add_new_Site(Site s){
        Map<String, Site> map = manager_Map.get(s.getShipping_area()).get(s.getType());
        if (!map.containsKey(s.getName())) {
            manager_Map.get(s.getShipping_area()).get(s.getType()).put(s.getName(), s);
        }
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


    public static void clear_cur_items(){
        items.clear();
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
                    curr_all_items.get(new_item);
                    curr_all_items.put(new_item, curr_all_items.get(new_item) + amount);
                }
                catch (Exception e){
                    curr_all_items.put(new_item, amount);
                }
                break;
            }
            case "Store": {
                for (Map.Entry<Item, Integer> iter: curr_all_items.entrySet()){
                    if (iter.getKey().to_string().equals(j.get("Item").getAsString())) {
                        items.put(iter.getKey(), j.get("Amount").getAsInt());
                        iter.setValue(iter.getValue() - j.get("Amount").getAsInt());
                    }
                }
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
                            curr_all_items.clear();
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
    public static JsonObject choose_truck_from_Data(){

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
    public static JsonObject choose_driver_from_Data(String truck){

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
    public static JsonObject choose_area_from_Data(){
        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Map<String, Map<String, Site>>> iter : manager_Map.entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getKey());
        }
        return j;
    }
    /**
     * Choose Supplier or Store from DataStruct
     * @param area - the selected Area
     * @return JsonObject represent the all the Supplier inside the Area
     */
    public static JsonObject choose_supplier_or_store_from_Data(String area, String type){
        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Site> iter : DataStructManager.manager_Map.get(area).get(type).entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getValue().to_string());
        }
        return j;
    }

    public static JsonObject choose_items(){
        JsonObject j = new JsonObject();
        int count = 1;

        for (Map.Entry<Item, Integer> iter: curr_all_items.entrySet()){
            if (iter.getValue() != 0)
                j.addProperty(String.valueOf(count++),iter.getKey().to_string());
        }
        return j;
    }

    public static int amount_items(String s){

        for (Map.Entry<Item, Integer> iter: curr_all_items.entrySet()){
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
    public static JsonObject All_transport_print(){
        if(transports.isEmpty()) return null;
        JsonObject j = new JsonObject();
        int count = 1;
        for(Transport tran : transports){
            j.addProperty(String.valueOf(count++), tran.to_String_tran());
        }
        return j;
    }
}
