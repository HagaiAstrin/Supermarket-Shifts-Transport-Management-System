package Domain.Controllers;

import DAL.*;
import Domain.Obejects.Driver;
import Domain.Obejects.Site;
import Domain.Obejects.TransportDocument;
import Domain.Obejects.Truck;
import Domain.Repositories.*;
import com.google.gson.JsonObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DataController {
    private static int DriverNumber = 10;
    private static int SiteID = 10;

    private static IRepository<Truck> Trucks = new TrucksRepository();
    private static IRepository<Driver> Drivers = new DriverRepository();
    private static SitesRepository Sites = new SitesRepository();
    private static IRepository<TransportDocument> Transports = new TransportsRepository();

    private static IDAO<Truck> DB_Trucks = new TrucksDAO();
    private static IDAO<Driver> DB_Drivers = new DriversDAO();
    private static IDAO<Site> DB_Sites = new SitesDAO();
    private static IDAO<TransportDocument> DB_Transports = new TransportsDAO();




    /**
     * Adding Driver to DataStruct
     * @param j - JsonObject argument
     */
    public static void AddDriver(JsonObject j) throws SQLException {

        String name = j.get("Name").getAsString();
        String licence = j.get("Licence").getAsString();
        String password = j.get("Password").getAsString();

        Driver new_driver = new Driver(name, licence, password,
                "000-00-000",0 , "Not Have");

        Drivers.Add(new_driver);

        DB_Drivers.INSERT(new_driver);

    }
    /**
     * Adding Truck to DataStruct
     * @param j - JsonObject argument
     */
    public static void AddTruck(JsonObject j) throws SQLException {

        String n = j.get("Licence number").getAsString();
        String l = j.get("Licence level").getAsString();
        double net = j.get("Net weight").getAsDouble();
        double max = j.get("Max weight").getAsDouble();

        Truck new_truck = new Truck(n, l, net, max);

        Trucks.Add(new_truck);

        DB_Trucks.INSERT(new_truck);
    }
    /**
     * Adding new site in specific Shipping_area to manager_Map
     * @param j - JsonObject argument
     */
    public static void AddSite(JsonObject j) throws SQLException {

        String name = j.get("Name").getAsString();
        String address = j.get("Address").getAsString();
        String phone = j.get("Phone number").getAsString();
        String contact = j.get("Contact").getAsString();
        String Shipping_area = j.get("Shipping area").getAsString();
        String type = j.get("Type").getAsString();

        Site new_site = new Site(name, address, phone, contact, Shipping_area, type);

        Sites.Add(new_site);
        DB_Sites.INSERT(new_site);
    }
    public static void AddTransportDocument(TransportDocument d) throws SQLException {
        Transports.Add(d);
        DB_Transports.INSERT(d);
    }

    public static JsonObject ChooseTruck(){
        return Trucks.ChooseAll("a", "b");
    }
    public static JsonObject ChooseDriver(String truckLicence){
        return Drivers.ChooseAll(truckLicence, "b");
    }
    public static JsonObject ChooseArea(){
        return Sites.FindShippingArea();
    }
    public static JsonObject ChooseSite(String area, String type){
        return Sites.ChooseAll(area, type);
    }

    public static Truck getTruck(String s){
        if (Trucks.getAmount() == 0)
            DB_Trucks.GET_ALL();
        return Trucks.FindByID(s);
    }
    public static Driver getDriver(String s){
        if (Drivers.getAmount() == 0)
            DB_Drivers.GET_ALL();
        return Drivers.FindByID(s);
    }
    public static Site getSite(String s){
        if (Sites.getAmount() == 0 )
            DB_Sites.GET_ALL();
        return Sites.FindByID(s);
    }
    public static TransportDocument getTransportDoc(String s){
        if (Transports.getAmount() == 0)
            DB_Transports.GET_ALL();
        return Transports.FindByID(s);
    }

    public static ArrayList<Truck> getAllTrucks(){
        if (Trucks.getAmount() == 0)
            DB_Trucks.GET_ALL();
        return Trucks.FindAll();
    }
    public static ArrayList<Driver> getAllDrivers(){
        if (Drivers.getAmount() == 0)
            DB_Drivers.GET_ALL();
        return Drivers.FindAll();
    }
    public static ArrayList<TransportDocument> getAllTransportsDoc(){
        if (Transports.getAmount() == 0)
            DB_Transports.GET_ALL();
        return Transports.FindAll();
    }
    public static Map<String, Map<String, Map<String, Site>>> getAllSites(){
        if (Sites.getAmount() == 0 )
            DB_Sites.GET_ALL();
        return Sites.FindAllSites();
    }



    public static JsonObject getAllTransports(){
        return Transports.ChooseAll("a", "b");
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

    public static void insertDriverToDB(){

    }
    public static void insertTruckToDB(){

    }
    public static void insertTransportToDB(){

    }
    public static void insertSiteToDB(){

    }

    public static void updateDriverToDB(){

    }
    public static void updateTruckToDB(){

    }
    public static void updateTransportToDB(){

    }
    public static void updateSiteToDB(){

    }


}
