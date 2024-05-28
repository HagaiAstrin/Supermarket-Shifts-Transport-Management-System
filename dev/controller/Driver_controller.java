package controller;

import com.google.gson.JsonObject;
import domain.DataStructManager;

public class Driver_controller {
    public static String check_driver(JsonObject j){
        return DataStructManager.check_driver(j);
    }
    public static void update_back (JsonObject j){
        DataStructManager.update_back_driver(j);
    }
    public static void update_leaving (JsonObject j){
        DataStructManager.update_leaving_driver(j);
    }
}
