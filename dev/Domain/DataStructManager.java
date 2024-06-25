package Domain;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataStructManager {
    private static Map<String, Map<String, Map<String, Site>>> manager_Map = new HashMap<>();
    private static ArrayList<Truck> trucks = new ArrayList<>();
    private static ArrayList<Driver> drivers = new ArrayList<>();
    private static ArrayList<Transportation> transports = new ArrayList<>();
    protected static double current_max_transport;
    private static int count_good_transport = 1000;

    public static Transportation transport;

//  Getters
    /**
     * @return the Manager Map
     */
    public static Map<String, Map<String, Map<String, Site>>> getManager_Map() {
        return manager_Map;
    }
    /**
     * @return all the Trucks in the System
     */
    public static ArrayList<Truck> getTrucks() {
        return trucks;
    }
    public static ArrayList<Driver> getDrivers() {
        return drivers;
    }


    //    Driver's methods:
    /**
     * Checking name and password of the driver
     * @param j - JsonObject argument
     * @return String represent of the Driver
     */
    public static String driver_log_in(JsonObject j){
        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get("Password").getAsString().equals(driver.getPassword())) {
                return driver.getName();
            }
        }
        return null;
    }
    public static String print_driver_doc(JsonObject j){
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
    public static String updateBackDriver(JsonObject j){
        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get
                    ("Password").getAsString().equals(driver.getPassword())) {
                if (driver.getTran() != null && !driver.isAvailability()){
                    driver.setAvailability(true);
                    driver.getUsing_truck().setAvailability(true);
                    driver.setTran(null);
                    driver.setDocuments(null);
                    driver.getTran().setStatus("Delivered!");
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
    public static String updateLeavingDriver(JsonObject j){
        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get
                    ("Password").getAsString().equals(driver.getPassword())) {
                if (driver.getTran() == null) {
                    return ("\nYou didnt assigned to any Transportation!");
                }
                else if(!driver.isAvailability() && !driver.isHold()) {
                    return "\nYou are currently in transit";
                }
                else {
                    driver.setAvailability(false);
                    driver.getUsing_truck().setAvailability(false);
                    driver.getUsing_truck().setHold(false);
                    driver.setHold(false);
                    driver.getTran().setStatus("Out for Delivery..");
//                    driver.getTran().setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//                    driver.getTran().setLeaving_time(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    transports.add(driver.getTran());
                    return ("\nHave a good trip!");
                }
            }
        }
        return ("\nYou are not exist in the system!");
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
     * Add new Shipping area to the System
     */
    public static void add_new_Site(Site s){
        Map<String, Site> map = manager_Map.get(s.getShipping_area()).get(s.getType());
        if (!map.containsKey(s.getName())) {
            manager_Map.get(s.getShipping_area()).get(s.getType()).put(s.getName(), s);
        }
    }
    /**
     * Add new Driver to the System
     */
    public static void add_new_driver(Driver d){
        drivers.add(d);
    }
    /**
     * Add new Truck to the System
     */
    public static void add_new_Truck(Truck t){
        trucks.add(t);
    }
    public static void add_item(JsonObject j, String s){
        transport.add_Item(j, s);
    }
    public static void createTransport(JsonObject j) {

        String source = j.get("Source").getAsString();
        String date = j.get("Date").getAsString();
        String leaving_time = j.get("Leaving time").getAsString();


        for (Driver driver : drivers) {
            if (driver.to_String().equals(j.get("Driver").getAsString())) {
                for (Truck truck : trucks) {
                    if (truck.to_String().equals(j.get("Truck").getAsString())) {
                        transport = new Transportation(truck, driver, source, date, leaving_time);
                    }
                }
            }
        }
    }
    public static int checkTransport() {

        int result = transport.WeightCheck();
        current_max_transport = transport.get_transport_Max_weight();

        if (result == 0) {
            transport.setId(count_good_transport++);
            transport.getDriver().setDocuments(transport.getTargets());
            transport.getDriver().setHold(true);
            transport.getDriver().setList();
            transport.getTruck().setHold(true);
            transport.getDriver().setUsing_truck(transport.getTruck());
            transport.getDriver().setTran(transport);
            transport = null;
            return 0;
        }
        return result;
    }

//    Selection methods:
    /**
     * Choose a Truck from DataStruct
     * @return JsonObject represent the truck
     */
    public static JsonObject chooseTruckFromData(){

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
    public static JsonObject chooseDriverFromData(String truck){

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
    public static JsonObject chooseAreaFromData(){
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
    public static JsonObject chooseSiteFromData(String area, String type){
        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Site> iter : DataStructManager.manager_Map.get(area).get(type).entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getValue().to_string());
        }
        return j;
    }
    public static JsonObject chooseItems(){
        JsonObject j = new JsonObject();
        int count = 1;

        for (Map.Entry<Item, Integer> iter: transport.getAll_transport_items().entrySet()){
            if (iter.getValue() != 0)
                j.addProperty(String.valueOf(count++),iter.getKey().to_string());
        }
        return j;
    }
    /**
     * Return the number of a specific item in the Order
     * @param s - String Type represent the Item name
     */
    public static int amountItems(String s) {

        for (Map.Entry<Item, Integer> iter: transport.getAll_transport_items().entrySet()){
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
    public static JsonObject printAllTransports(){
        if(transports.isEmpty()) return null;
        JsonObject j = new JsonObject();
        int count = 1;
        for(Transportation tran : transports){
            j.addProperty(String.valueOf(count++), tran.to_String());
        }
        return j;
    }
}
