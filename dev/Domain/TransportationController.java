package Domain;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransportationController {
    private static Map<String, Map<String, Map<String, Site>>> AllSites = new HashMap<>();
    private static ArrayList<Truck> AllTrucks = new ArrayList<>();
    private static ArrayList<Driver> AllDrivers = new ArrayList<>();
    public static Transportation Transport;
    private static Map<Integer, String> allTransportations =  new HashMap<>();


//  Getters
    /**
     * @return the Manager Map
     */
    public static Map<String, Map<String, Map<String, Site>>> getAllSites() {
        return AllSites;
    }
    /**
     * @return all the Trucks in the System
     */
    public static ArrayList<Truck> getAllTrucks() {
        return AllTrucks;
    }
    public static ArrayList<Driver> getAllDrivers() {
        return AllDrivers;
    }
    public static int getNumberId(){
        if (allTransportations.isEmpty())
            return 1000;
        return allTransportations.size() + 1000;
    }
    //    Driver's methods:
    /**
     * Checking name and password of the driver
     * @param j - JsonObject argument
     * @return String represent of the Driver
     */



//    Addition methods:
    /**
     * Adding new Shipping_area to manager_Map
     * @param Shipping_area - String argument
     */
    public static void add_Shipping_area(String Shipping_area){
        Map<String, Map<String, Site>> map = new HashMap<>();
        map.put("Store",new HashMap<>());
        map.put("Supplier",new HashMap<>());
        AllSites.put(Shipping_area, map);
    }
    /**
     * Add new Shipping area to the System
     */
    public static void add_new_Site(Site s){
        Map<String, Site> map = AllSites.get(s.getShipping_area()).get(s.getType());
        if (!map.containsKey(s.getName())) {
            AllSites.get(s.getShipping_area()).get(s.getType()).put(s.getName(), s);
        }
    }
    /**
     * Add new Driver to the System
     */
    public static void add_new_driver(Driver d){
        AllDrivers.add(d);
    }
    /**
     * Add new Truck to the System
     */
    public static void add_new_Truck(Truck t){
        AllTrucks.add(t);
    }
    public static void add_item(JsonObject j, String s){
        Transport.add_Item(j, s);
    }


    public static void createTransport(JsonObject j) {

        String source = j.get("Source").getAsString();
        String date = j.get("Date").getAsString();
        String leaving_time = j.get("Leaving time").getAsString();


        for (Driver driver : AllDrivers) {
            if (driver.to_String().equals(j.get("Driver").getAsString())) {
                for (Truck truck : AllTrucks) {
                    if (truck.to_String().equals(j.get("Truck").getAsString())) {
                        Transport = new Transportation(truck, driver, source, date, leaving_time);
                    }
                }
            }
        }
    }
    public static int checkTransport() {

        int result = Transport.WeightCheck();

        if (result == 0) {
            Transport.setId(getNumberId() + 1);
            Transport.getDriver().setDocuments(Transport.getTargets());
            Transport.getDriver().setStatus("Waiting");
            Transport.getTruck().setStatus("Waiting");
            Transport.getDriver().showRoute();
            Transport.getDriver().setTruck(Transport.getTruck().getLicence_number());
            Transport.getDriver().setTransport(Transport);
            allTransportations.put(Transport.getId(), Transport.to_String());
            Transport = null;
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
        for (Truck t: AllTrucks){
            if (t.getStatus().equals("available")){
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
        for (Truck t: AllTrucks){
            if (truck.equals(t.to_String())){
                for (Driver d: AllDrivers){
                    if (d.getStatus().equals("available")){
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
        for (Map.Entry<String, Map<String, Map<String, Site>>> iter : AllSites.entrySet()) {
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
        for (Map.Entry<String, Site> iter : TransportationController.AllSites.get(area).get(type).entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getValue().to_string());
        }
        return j;
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
    public static int amountItems(String s) {

        for (Map.Entry<Item, Integer> iter: Transport.getAll_transport_items().entrySet()){
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
        if(allTransportations.isEmpty()) return null;
        JsonObject j = new JsonObject();
        int count = 1;
        for (int i = 0; i < allTransportations.size(); i++){
            j.addProperty(String.valueOf(count++), allTransportations.get(i));
        }
        return j;
    }
}
