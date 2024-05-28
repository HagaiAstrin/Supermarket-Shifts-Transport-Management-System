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
        DataStructManager.add_Store(j);
    }
    public static void add_supplier(JsonObject j){
        DataStructManager.add_Supplier(j);
    }
    public static void create_document(JsonObject j){
       DataStructManager.create_document(j);
    }
    public static boolean create_transport(JsonObject j){return DataStructManager.create_transportation(j);}
    public static JsonObject choose_truck(){
        return DataStructManager.choose_truck();
    }
    public static JsonObject choose_driver(){
        return DataStructManager.choose_driver();
    }
    public static JsonObject choose_area(){
        return DataStructManager.choose_area();
    }
    public static JsonObject choose_supplier(String a){
        return DataStructManager.choose_supplier(a);
    }
    public static JsonObject choose_store(String a){
        return DataStructManager.choose_store(a);
    }
}
