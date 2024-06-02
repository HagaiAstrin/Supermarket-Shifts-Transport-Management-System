package domain;

import com.google.gson.JsonObject;

import java.util.Map;

import static domain.DataStructManager.*;

public class Solutions {

    /**
     * @return JsonObject of all trucks that can make the Transport
     */
    public static JsonObject get_Truck_Json() {
        JsonObject new_json = new JsonObject();
        int count = 1;
        for(Truck tr : DataStructManager.trucks){
            if(tr.isAvailability() && (tr.getMax_weight() - tr.getNet_weight()) >= DataStructManager.current_max_transport){
                new_json.addProperty(String.valueOf(count), tr.to_String());
                count++;
            }
        }
        return new_json;
    }
    /**
     * @param a - String argument represent the Site to dropped items from
     * @return JsonObject of the Items to dropped
     */
    public static JsonObject get_Item_Json(String a){
        JsonObject new_json = new JsonObject();
        int count = 1;
        for(Document d : DataStructManager.documents) {
            if(d.to_string().equals(a)) {
                for (Map.Entry<Item, Integer> iter : d.getItem_map().entrySet()) {
                    new_json.addProperty(String.valueOf(count++), d.item_String(iter.getKey()));
                }
                break;
            }
        }
        return new_json;
    }
    /**
     *
     * @return JsonObject represent the available Sites to dropped
     */
    public static JsonObject Choose_Drop_Target(){
        JsonObject j = new JsonObject();
        int count = 1;
        for (Document d: DataStructManager.documents){
            j.addProperty(String.valueOf(count++), d.to_string());
        }
        return j;
    }
    /**
     * Replace Documents
     * @param a - String represent of the Document to replace
     */
    public static void replace_documents(String a){
        int count = 0;
        for(Document d : documents){
            if(d.to_string().equals(a)){
                documents.remove(d);
                Document doc = documents.remove(documents.size() - 1);
                documents.add(count, doc);
                break;
            }
            count++;
        }
    }
    /**
     * Dropped Items
     * @param a - String represent of the Item to dropped
     */
    public static void drop_Items(String a){
        boolean bool = false;
        for(Document d : documents) {
            for (Map.Entry<Item, Integer> entry : d.getItem_map().entrySet()) {
                if (d.item_String(entry.getKey()).equals(a)) {
                    d.drop_Item(entry.getKey());
                    if(d.getItem_map().isEmpty()) documents.remove(d);
                    all_items.remove(entry.getKey());
                    bool = true;
                    break;
                }
            }
            if(bool) break;
        }
    }
    /**
     * Dropped Site from Database
     * @param a - String argument represent the Site to dropped
     */
    public static void drop_Site(String a){
        for(Document d : documents){
            if(d.to_string().equals(a)){
                if (d.getTarget().getType().equals("Supplier")){

                }
                for(Map.Entry<Item, Integer> entry : d.getItem_map().entrySet()){
                    items.remove(entry.getKey());
                }
                documents.remove(d);

            }
        }
    }
}
