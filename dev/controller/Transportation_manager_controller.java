package controller;

import com.google.gson.JsonObject;
import domain.DataStructManager;

import java.util.ArrayList;

public class Transportation_manager_controller {

    public static void add_driver(JsonObject j){
        DataStructManager.add_driver(j);
    }
    public static void add_truck(JsonObject j){
        DataStructManager.add_truck(j);
    }
    public static void add_store(JsonObject j){
        DataStructManager.add_Site(j);
    }
    public static void add_supplier(JsonObject j){
        DataStructManager.add_Site(j);}
    public static void create_items_list(JsonObject j){
        DataStructManager.create_items_list(j);
    }

    public static void create_document(JsonObject j){
       DataStructManager.create_document(j);
    }
    public static boolean create_transport(JsonObject j, ArrayList<String> a){return DataStructManager.create_transportation(j, a);}
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
