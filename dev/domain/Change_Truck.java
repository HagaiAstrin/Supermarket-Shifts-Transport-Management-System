package domain;

import com.google.gson.JsonObject;
public class Change_Truck {

    /**
     * @return JsonObject of all trucks that can make the Transport
     */
    public static JsonObject get_Json_tr() {
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
}
