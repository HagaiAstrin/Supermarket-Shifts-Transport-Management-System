package Service;

import Domain.TransportationManagerController;
import com.google.gson.JsonObject;

public class TransportationManagerApplication {
    TransportationManagerController TC = new TransportationManagerController();

    /**
     * Addition methods:
     */
    public void add_driver(JsonObject j){
        TC.add_driver(j);
    }
    public void add_truck(JsonObject j){
        TC.add_truck(j);
    }
    public void add_site(JsonObject j){
        TC.add_site(j);
    }
    public void add_Item (JsonObject j, String s){
        TC.add_Item(j, s);
    }


    public void create_document(JsonObject j){
        TC.create_document(j);
    }
    public void create_transport(JsonObject j){ TC.create_transport(j);}
    public int check_transportation(){
        return TC.check_transportation();
    }

    /**
     * Selection methods
     */
    public JsonObject choose_truck(){
        return TC.choose_truck();
    }
    public JsonObject choose_driver(String truck){
        return TC.choose_driver(truck);
    }
    public JsonObject choose_area(){
        return TC.choose_area();
    }
    public JsonObject choose_supplier_or_store(String a, String type){
        return TC.choose_supplier_or_store(a, type);
    }
    public JsonObject choose_good_Truck(){return TC.choose_good_Truck();}
    public JsonObject Choose_Supplier_Target(){
        return TC.Choose_Supplier_Target();
    }
    public JsonObject choose_items(){
        return TC.choose_items();
    }
    public int amount_items(String s){
        return TC.amount_items(s);
    }
    public void change_truck(JsonObject j){TC.change_truck(j);}


    /**
     * Solution methods:
     */
    public void drop_Site(String a){
        TC.drop_Site(a);}
    public void replace_Documents(String s){TC.replace_Documents(s);}
    public JsonObject get_Items_Json(String a){
        return TC.get_Items_Json(a);
    }
    public void drop_Items(String a, String b) {TC.drop_Items(a, b);}


    /**
     * Print method:
     */
    public JsonObject show_all_Transport(){return TC.show_all_Transport();}

    }

