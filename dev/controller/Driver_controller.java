package controller;

import com.google.gson.JsonObject;
import domain.DataStructManager;

public class Driver_controller {

    /**
     * Driver Controllerj asdfnasdlfads
     */
    public static String check_driver_logIn(JsonObject j){
        return DataStructManager.driver_log_in(j);
    }
    public static String update_back (JsonObject j){
         return DataStructManager.update_back_driver(j);
    }
    public static String update_leaving (JsonObject j){
         return DataStructManager.update_leaving_driver(j);
    }
    public static StringBuilder Print_document(JsonObject j){
        return DataStructManager.print_driver_doc(j);
    }
}
