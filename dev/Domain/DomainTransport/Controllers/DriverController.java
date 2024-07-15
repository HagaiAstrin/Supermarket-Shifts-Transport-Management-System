package Domain.DomainTransport.Controllers;

import DAL.DALTransport.DriversDAO;
import DAL.DALTransport.TrucksDAO;
import Domain.DomainTransport.Obejects.*;
import com.google.gson.JsonObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverController {

    /**
     * Driver Log in to the System
     */
    public static String driverLogIn(JsonObject j) throws SQLException {

        ArrayList<Driver> drivers = DataController.getAllDrivers();

        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get("Password").getAsString().equals(driver.getPassword())) {
                return driver.getName();
            }
        }
        return null;
    }
    public static String printDriverRoute(JsonObject j) throws SQLException{

        ArrayList<Driver> drivers = DataController.getAllDrivers();

        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) &&
                    j.get("Password").getAsString().equals(driver.getPassword())) {
                return driver.getRoute();
            }
        }
        return null;
    }
    public static String updateBackDriver(JsonObject j) throws SQLException{

        ArrayList<Driver> drivers = DataController.getAllDrivers();

        JsonObject DriverJson = new JsonObject();
        JsonObject TruckJson = new JsonObject();
        JsonObject TransportJson = new JsonObject();

        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get
                    ("Password").getAsString().equals(driver.getPassword())){

                if (driver.getStatus().equals("On the road")){

                    String DriverShifts [][] = DriversDAO.GET_DRIVER_PREFERENCES(driver.getDriverID());
                    String TruckShifts [][] = TrucksDAO.GET_TRUCK_TABLE(driver.getTruck().getLicence_number());

                    int day = driver.getTransportDocument().getDay();
                    int shift = driver.getTransportDocument().getShift();

                    DriverShifts[shift-1][day-1] = "1";
                    TruckShifts[shift-1][day-1] = "1";

                    DriversDAO.UPDATE_DRIVER_PREFERENCES(DriverShifts, driver.getDriverID());
                    TrucksDAO.UPDATE_TRUCK_TABLE(TruckShifts, driver.getTruck().getLicence_number());

                    DriverJson.addProperty("Driver ID", driver.getDriverID());
                    DriverJson.addProperty("Status", "available");
                    DriverJson.addProperty("Route", "Not Have");
                    DriverJson.addProperty("Transport ID", 0);
                    DriverJson.addProperty("Truck Licence Number", "000-00-000");

                    TruckJson.addProperty("Licence number", driver.getTruck().getLicence_number());
                    TruckJson.addProperty("Status", "available");

                    TransportJson.addProperty("Transportation ID",driver.getTransportDocument().getID());
                    TransportJson.addProperty("Status", "Delivered!");

                    DataController.updateDriver(DriverJson);
                    DataController.updateTruck(TruckJson);
                    DataController.updateTransport(TransportJson);

                    return ("\nWelcome back " + driver.getName() + "!");
                }
                return ("\nYou can't report back because you didnt made Transportation!\n");
            }
        }
        return ("\nYou are not exist in the system!\n");
    }
    public static String updateLeavingDriver(JsonObject j) throws SQLException {

        ArrayList<Driver> drivers = DataController.getAllDrivers();

        JsonObject DriverJson = new JsonObject();
        JsonObject TruckJson = new JsonObject();
        JsonObject TransportJson = new JsonObject();

        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get
                    ("Password").getAsString().equals(driver.getPassword())) {

                if (driver.getTransportDocument() == null) {
                    return ("\nYou didnt assigned to any Transportation!");
                }
                else if(driver.getStatus().equals("On the road")) {
                    return "\nYou are currently in transit";
                }
                else {

                    DriverJson.addProperty("Driver ID", driver.getDriverID());
                    DriverJson.addProperty("Status", "On the road");
                    DriverJson.addProperty("Route", driver.getRoute());
                    DriverJson.addProperty("Transport ID", driver.getTransportDocument().getID());
                    DriverJson.addProperty("Truck Licence Number", driver.getTruck().getLicence_number());

                    TruckJson.addProperty("Licence number", driver.getTruck().getLicence_number());
                    TruckJson.addProperty("Status", "On the road");

                    TransportJson.addProperty("Transportation ID",driver.getTransportDocument().getID());
                    TransportJson.addProperty("Status", "Out for Delivery..");

                    DataController.updateDriver(DriverJson);
                    DataController.updateTruck(TruckJson);
                    DataController.updateTransport(TransportJson);

                    String DriverShifts [][] = DriversDAO.GET_DRIVER_PREFERENCES(driver.getDriverID());
                    String TruckShifts [][] = TrucksDAO.GET_TRUCK_TABLE(driver.getTruck().getLicence_number());

                    int day = driver.getTransportDocument().getDay();
                    int shift = driver.getTransportDocument().getShift();

                    DriverShifts[shift-1][day-1] = "3";
                    TruckShifts[shift-1][day-1] = "3";

                    DriversDAO.UPDATE_DRIVER_PREFERENCES(DriverShifts, driver.getDriverID());
                    TrucksDAO.UPDATE_TRUCK_TABLE(TruckShifts, driver.getTruck().getLicence_number());

                    return ("\nHave a good trip " + driver.getName() + "!");
                }
            }
        }
        return ("\nYou are not exist in the system!");
    }
    public static String UpdateDriverWorkPreferences(JsonObject j) throws SQLException {

        ArrayList<Driver> drivers = DataController.getAllDrivers();

        int id = 0;
        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get("Password").getAsString().equals(driver.getPassword())) {
                id = driver.getDriverID();
            }
        }

        String[][] preferences = DriversDAO.GET_DRIVER_PREFERENCES(id);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\nUpdate Preferences Menu:");
            System.out.println("\t1. Update Sunday");
            System.out.println("\t2. Update Monday");
            System.out.println("\t3. Update Tuesday");
            System.out.println("\t4. Update Wednesday");
            System.out.println("\t5. Update Thursday");
            System.out.println("\t6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 6) {
                break;
            }
            if (choice > 6 || choice < 1) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            int row = 0;
            while (row != 1 && row != 2) {
                System.out.println("Enter row number to update (1 or 2): ");
                System.out.println("1. Update Morning");
                System.out.println("2. Update Evening");
                row = scanner.nextInt();
                scanner.nextLine();

                if (row != 1 && row != 2) {
                    System.out.println("Invalid row number.");
                }
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter new value for Sunday (1 or 0): ");
                    preferences[row - 1][0] = scanner.nextLine().trim();
                    break;
                case 2:
                    System.out.print("Enter new value for Monday (1 or 0): ");
                    preferences[row - 1][1] = scanner.nextLine().trim();
                    break;
                case 3:
                    System.out.print("Enter new value for Tuesday (1 or 0): ");
                    preferences[row - 1][2] = scanner.nextLine().trim();
                    break;
                case 4:
                    System.out.print("Enter new value for Wednesday (1 or 0): ");
                    preferences[row - 1][3] = scanner.nextLine().trim();
                    break;
                case 5:
                    System.out.print("Enter new value for Thursday (1 or 0): ");
                    preferences[row - 1][4] = scanner.nextLine().trim();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        return DriversDAO.UPDATE_DRIVER_PREFERENCES(preferences, id);
    }
}
