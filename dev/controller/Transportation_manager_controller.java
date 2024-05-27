package controller;

import com.google.gson.JsonObject;
import domain.DataStructManager;

public class Transportation_manager_controller {

    public static void add_driver(JsonObject j){
        DataStructManager.add_driver(j);
    }
    public static void add_truck(JsonObject j){
        DataStructManager.add_truck(j);
    }
    public static void add_store(JsonObject j){
        DataStructManager.add_store(j);
    }
    public static void add_supplier(JsonObject j){
        DataStructManager.add_supplier(j);
    }
}
