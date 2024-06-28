package Domain;

import com.google.gson.JsonObject;

public class DataController {
    private static int DriverNumber = 10;
    private static int SiteID = 10;

    private static IRepository<Truck> Trucks = new TrucksRepository();
    private static IRepository<Driver> Drivers = new DriverRepository();
    private static IRepository<Site> Sites = new SitesRepository();
    private static IRepository<TransportDocument> Transports = new TransportsRepository();



    /**
     * Adding Driver to DataStruct
     * @param j - JsonObject argument
     */
    public static void AddDriver(JsonObject j){

        String name = j.get("Name").getAsString();
        String licence = j.get("Licence").getAsString();
        String password = j.get("Password").getAsString();

        Driver new_driver = new Driver(name, licence, password,
                "000-00-000", DriverNumber ++, 0, null);

        Drivers.Add(new_driver);

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

        Trucks.Add(new_truck);
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

        Site new_site = new Site(SiteID ++ ,name, address, phone, contact, Shipping_area, type);

        Sites.Add(new_site);
    }
    public static void AddTransportDocument(TransportDocument d){
        Transports.Add(d);
    }

    public static JsonObject ChooseTruck(){
        return Trucks.FindAll("a", "b");
    }
    public static JsonObject ChooseDriver(String truckLicence){
        return Drivers.FindAll(truckLicence, "b");
    }
    public static JsonObject ChooseArea(){
        return Sites.FindMore();
    }
    public static JsonObject ChooseSite(String area, String type){
        return Sites.FindAll(area, type);
    }

    public static Truck getTruck(String s){
        return Trucks.FindByID(s);
    }
    public static Driver getDriver(String s){
        return Drivers.FindByID(s);
    }
    public static Site getSite(String s){
        return Sites.FindByID(s);
    }
    public static TransportDocument getTransportDoc(String s){
        return Transports.FindByID(s);
    }
    public static JsonObject getAllTransports(){
        return Transports.FindAll("a", "b");
    }

    public static int getAmount(String type){
        switch (type){
            case "Transport" -> {
                return Transports.getAmount();
            }
            case "Drivers" -> {
                return Drivers.getAmount();
            }
            case "Trucks" -> {
                return Trucks.getAmount();
            }
            case "Sites" -> {
                return Sites.getAmount();
            }
        }
        return 0;
    }

}
