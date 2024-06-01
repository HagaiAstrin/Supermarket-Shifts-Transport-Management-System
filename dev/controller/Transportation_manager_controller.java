package controller;

import com.google.gson.JsonObject;
import domain.Change_Truck;
import domain.DataStructManager;
import domain.Drop_Items;
import domain.Drop_Sites;
import presentation.Create_Transportation;

public class Transportation_manager_controller {

    public static void add_driver(JsonObject j){
        DataStructManager.add_driver(j);
    }
    public static void add_truck(JsonObject j){
        DataStructManager.add_truck(j);
    }
    public static void add_site(JsonObject j){
        DataStructManager.add_Site(j);
    }
    public static void create_items_list(JsonObject j){
        DataStructManager.create_items_list(j);
    }
    public static void create_document(JsonObject j){
       DataStructManager.create_document(j);
    }
    public static boolean create_transport(JsonObject j){return DataStructManager.create_transportation(j);}
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
    public static String choose_result(){
        return Create_Transportation.choose_solution();
    }
    public static JsonObject choose_good_Truck(){return Change_Truck.get_Json_tr();}
    public static JsonObject Choose_Site_Target(){
        return Drop_Sites.Choose_Drop_Target();
    }

    public static void drop_Site(String a){DataStructManager.drop_Site(a);}
    public static void replace_Documents(String s){DataStructManager.replace_documents(s);}

    public static JsonObject get_Items_Json(String a){
        return Drop_Items.getItem_Json(a);
    }
    public static void drop_Items(String a){DataStructManager.drop_Items(a);}

    public static JsonObject show_all_Transport(){return DataStructManager.all_transport();}
}
