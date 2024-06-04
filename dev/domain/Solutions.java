package domain;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Map;

import static domain.DataStructManager.*;

public class Solutions {

    /**
     * @return JsonObject of all trucks that can make the Transport
     */
    public static JsonObject get_Truck_Json() {
        JsonObject new_json = new JsonObject();
        int count = 1;
        for (Truck tr : DataStructManager.getTrucks()) {
            if (tr.isAvailability() && (tr.getMax_weight() - tr.getNet_weight()) >= DataStructManager.current_max_transport) {
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
    public static JsonObject get_Item_Json(String a) {
        JsonObject new_json = new JsonObject();
        int count = 1;
        for (Document d : DataStructManager.getDocuments()) {
            if (d.to_string().equals(a)) {
                for (Map.Entry<Item, Integer> iter : d.getItem_map().entrySet()) {
                    new_json.addProperty(String.valueOf(count++), (iter.getKey().to_string()));
                }
                break;
            }
        }
        return new_json;
    }

    /**
     * @return JsonObject represent the available Sites to dropped
     */
    public static JsonObject Choose_Drop_Supplier_Target() {
        JsonObject j = new JsonObject();
        int count = 1;
        for (Document d : DataStructManager.getDocuments()) {
            if (d.getTarget().getType().equals("Supplier"))
                j.addProperty(String.valueOf(count++), d.to_string());
        }
        return j;
    }

    /**
     * Replace Documents
     *
     * @param a - String represent of the Document to replace
     */
    public static void replace_documents(String a) {
        int count = 0;
        for (Document d : documents) {
            if (d.to_string().equals(a)) {
                break;
            }
            count++;
        }
        drop_Site(a);
        documents.add(count, documents.remove(documents.size() - 1));
    }

    /**
     * Dropped Items
     *
     * @param a - String represent of the Item to dropped
     */
    public static void drop_Items(String a, String b) {

        int count = 0;
        for (Document d : documents) {
            if (d.to_string().equals(b)) {
                for (Map.Entry<Item, Integer> entry : d.getItem_map().entrySet()) {
                    if (entry.getKey().to_string().equals(a)) {
                        d.drop_Item(entry.getKey());
                        curr_all_items.remove(entry.getKey());
                        if(d.getItem_map().isEmpty()){
                            documents.remove(d);
                        }
                        break;
                    }

                }

                for (int i = count; i < documents.size(); i++) {
                    if (documents.get(i).getTarget().getType().equals("Store")) {
                        for (Map.Entry<Item, Integer> entry : documents.get(i).getItem_map().entrySet()) {
                            if (entry.getKey().to_string().equals(a)) {
                                documents.get(i).drop_Item(entry.getKey());
                                if(documents.get(i).getItem_map().isEmpty()){
                                    documents.remove(documents.get(i));
                                }
                                break;
                            }
                        }
                    }
                }
                break;
            }
            count++;
        }

    }


    /**
     * Dropped Site from Database
     *
     * @param a - String argument represent the Site to dropped
     */
    public static void drop_Site(String a) {
        Document document = documents.get(0);
        int count = 0;
        for (Document d : documents) {
            if (d.to_string().equals(a)) {
                document = d;
                documents.remove(d);
                break;
            }
            count++;
        }
        ArrayList<Integer> arr = new ArrayList<>();
        for (Map.Entry<Item, Integer> iter : document.getItem_map().entrySet()) {
            curr_all_items.remove(iter.getKey());
            for (int i = count; i < documents.size(); i++) {
                if (documents.get(i).getTarget().getType().equals("Store")) {

//                    Map<Item, Integer> map_to_check = documents.get(i).getItem_map();
//                    for (Map.Entry<Item, Integer> entry : map_to_check.entrySet()) {
//                        if (entry.getKey().to_string().equals(iter.getKey().to_string())) {   //TODO has a problem
//                            documents.get(i).drop_Item(entry.getKey());
//                            if (documents.get(i).getItem_map().isEmpty()) {
//                                documents.remove(documents.get(i));
//                            }
//                        }
//
//                    }

                    if(documents.get(i).getItem_map().containsKey(iter.getKey())){
                        documents.get(i).drop_Item(iter.getKey());
                    }
                    if(documents.get(i).getItem_map().isEmpty()) {
                        arr.add(i);
                    }
                }
            }
        }

        if(arr.size() > 0) {
            for (int j = arr.size() - 1; j >= 0; j--) {
                DataStructManager.remove_doc(arr.get(j));
            }
        }

    }

}



