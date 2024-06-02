package controller;

import com.google.gson.JsonObject;
import domain.*;

public class Transportation_manager_controller {

//  Addition methods:
    public static void add_driver(JsonObject j){
        DataStructManager.add_driver(j);
    }
    public static void add_truck(JsonObject j){
        DataStructManager.add_truck(j);
    }
    public static void add_site(JsonObject j){
        DataStructManager.add_Site(j);
    }

//  Creation methods:
    public static void create_items_list(JsonObject j){
        DataStructManager.create_items_list(j);
    }
    public static void create_document(JsonObject j){
       DataStructManager.create_document(j);
    }
    public static boolean create_transport(JsonObject j){return DataStructManager.create_transportation(j);}

//  Selection methods:
    public static JsonObject choose_truck(){
        return DataStructManager.choose_truck();
    }
    public static JsonObject choose_driver(String truck){
        return DataStructManager.choose_driver(truck);
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
    public static JsonObject choose_good_Truck(){return Solutions.get_Truck_Json();}
    public static JsonObject Choose_Site_Target(){
        return Solutions.Choose_Drop_Target();
    }

//   Solution methods:
    public static void drop_Site(String a){Solutions.drop_Site(a);}
    public static void replace_Documents(String s){Solutions.replace_documents(s);}
    public static JsonObject get_Items_Json(String a){
        return Solutions.get_Item_Json(a);
    }
    public static void drop_Items(String a){Solutions.drop_Items(a);}

//    Print method:
    public static JsonObject show_all_Transport(){return DataStructManager.All_transport();}
}
