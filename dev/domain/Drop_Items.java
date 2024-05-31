package domain;

import com.google.gson.JsonObject;

import java.util.Map;

public class Drop_Items {
    public static JsonObject getItem_Json(String a){
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
}
