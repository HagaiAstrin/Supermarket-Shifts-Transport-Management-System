package Domain;

import com.google.gson.JsonObject;

public class DriverController {

    /**
     * Driver Controller
     */
    public static String check_driver_logIn(JsonObject j){
        return DataStructManager.driver_log_in(j);
    }
    public static String update_back (JsonObject j){
         return DataStructManager.updateBackDriver(j);
    }
    public static String update_leaving (JsonObject j){
         return DataStructManager.updateLeavingDriver(j);
    }
    public static String Print_document(JsonObject j){
        return DataStructManager.print_driver_doc(j);
    }
}
