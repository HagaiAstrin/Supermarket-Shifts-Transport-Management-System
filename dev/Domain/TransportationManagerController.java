package Domain;

import com.google.gson.JsonObject;

public class TransportationManagerController {

    /**
     * Addition methods:
     */
    public static void add_driver(JsonObject j){
        Builder.build_driver(j);
    }
    public static void add_truck(JsonObject j){
        Builder.build_truck(j);
    }
    public static void add_site(JsonObject j){
        Builder.build_Site(j);
    }
    public static void add_Item (JsonObject j, String s){
        DataStructManager.add_item(j, s);
    }


    public static void create_document(JsonObject j){
        Builder.build_document(j);
    }
    public static void create_transport(JsonObject j){
        DataStructManager.createTransport(j);}
    public static int check_transportation(){
        return DataStructManager.checkTransport();
    }

    /**
     * Selection methods
     */
    public static JsonObject choose_truck(){
        return DataStructManager.chooseTruckFromData();
    }
    public static JsonObject choose_driver(String truck){
        return DataStructManager.chooseDriverFromData(truck);
    }
    public static JsonObject choose_area(){
        return DataStructManager.chooseAreaFromData();
    }
    public static JsonObject choose_supplier_or_store(String a, String type){
        return DataStructManager.chooseSiteFromData(a, type);
    }
    public static JsonObject choose_good_Truck(){return Solutions.get_Truck_Json();}
    public static JsonObject Choose_Supplier_Target(){
        return Solutions.Choose_Drop_Supplier_Target();
    }
    public static JsonObject choose_items(){
        return DataStructManager.chooseItems();
    }
    public static int amount_items(String s){
        return DataStructManager.amountItems(s);
    }
    public static void change_truck(JsonObject j){Solutions.replace_truck_and_driver(j);}


    /**
     * Solution methods:
     */
    public static void drop_Site(String a){
        Solutions.drop_Site(a);}
    public static void replace_Documents(String s){Solutions.replace_documents(s);}
    public static JsonObject get_Items_Json(String a){
        return Solutions.get_Item_Json(a);
    }
    public static void drop_Items(String a, String b) {Solutions.drop_Items(a, b);}


    /**
     * Print method:
     */
    public static JsonObject show_all_Transport(){return DataStructManager.printAllTransports();}

}
