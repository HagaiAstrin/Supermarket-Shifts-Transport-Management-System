package Domain.Controllers;

import Domain.Obejects.Driver;
import com.google.gson.JsonObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class DriverController {

    /**
     * Driver Controller
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
    public static String printDriverDoc(JsonObject j) throws SQLException{

        ArrayList<Driver> drivers = DataController.getAllDrivers();

        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get("Password").getAsString().equals(driver.getPassword())) {
                return driver.getRoute();
            }
        }
        return null;
    }
    /**
     * Updating that the Driver comes back
     * @param j - JsonObject argument
     */
    public static String updateBackDriver(JsonObject j) throws SQLException{

        ArrayList<Driver> drivers = DataController.getAllDrivers();

        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get
                    ("Password").getAsString().equals(driver.getPassword())) {
                if (driver.getStatus().equals("On the road")){
                    driver.getTruck().setStatus("available");
                    driver.getTransportDocument().setStatus("Delivered!");
                    driver.setTransportID(0);
                    driver.setRoute(null);
                    driver.setStatus("available");
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
    public static String updateLeavingDriver(JsonObject j) throws SQLException {

        ArrayList<Driver> drivers = DataController.getAllDrivers();

        for (Driver driver : drivers) {
            if (j.get("Name").getAsString().equals(driver.getName()) && j.get
                    ("Password").getAsString().equals(driver.getPassword())) {
                if (driver.getTransportDocument().getID() == 0) {
                    return ("\nYou didnt assigned to any Transportation!");
                }
                else if(driver.getStatus().equals("On the road")) {
                    return "\nYou are currently in transit";
                }
                else {
                    driver.getTruck().setStatus("On the road");
                    driver.setStatus("On the road");
                    driver.getTransportDocument().setStatus("Out for Delivery..");
//                    driver.getTran().setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//                    driver.getTran().setLeaving_time(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                    return ("\nHave a good trip!");
                }
            }
        }
        return ("\nYou are not exist in the system!");
    }
}
