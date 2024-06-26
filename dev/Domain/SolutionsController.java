package Domain;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Map;

import static Domain.TransportationController.*;

public class SolutionsController {

    /**
     * @return JsonObject of all trucks that can make the Transport
     */
    public static JsonObject get_Truck_Json() {
        JsonObject new_json = new JsonObject();
        int count = 1;
        for (Truck tr : TransportationController.getAllTrucks()) {
            if (tr.getStatus().equals("available") && (tr.getMax_weight() -
                tr.getNet_weight())>= Transport.get_transport_Max_weight()) {
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
        for (Document d : Transport.getTargets()) {
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
        for (Document d : Transport.getTargets()) {
            if (d.getTarget().getType().equals("Supplier"))
                j.addProperty(String.valueOf(count++), d.to_string());
        }
        return j;
    }
    public static void replace_truck_and_driver(JsonObject t){
        for (Truck truck : TransportationController.getAllTrucks()) {
            if (truck.to_String().equals(t.get("Truck").getAsString()))
                Transport.setTruck(truck);
        }
        for (Driver driver : TransportationController.getAllDrivers()) {
            if (driver.to_String().equals(t.get("Truck").getAsString()))
                Transport.setDriver(driver);
        }

    }
    /**
     * Replace Documents
     *
     * @param a - String represent of the Document to replace
     */
    public static void replace_documents(String a) {
        int count = 0;
        for (Document d : Transport.getTargets()) {
            if (d.to_string().equals(a)) {
                break;
            }
            count++;
        }
        drop_Site(a);
        Transport.getTargets().add(count, Transport.getTargets().remove(Transport.getTargets().size() - 1));
    }
    /**
     * Dropped Items
     *
     * @param a - String represent of the Item to dropped
     */
    public static void drop_Items(String a, String b) {

        int count = 0;
        for (Document d : Transport.getTargets()) {
            if (d.to_string().equals(b)) {
                for (Map.Entry<Item, Integer> entry : d.getItem_map().entrySet()) {
                    if (entry.getKey().to_string().equals(a)) {
                        d.drop_Item(entry.getKey());
                        Transport.getAll_transport_items().remove(entry.getKey());
                        if(d.getItem_map().isEmpty()){
                            Transport.getTargets().remove(d);
                        }
                        break;
                    }

                }

                for (int i = count; i < Transport.getTargets().size(); i++) {
                    if (Transport.getTargets().get(i).getTarget().getType().equals("Store")) {
                        for (Map.Entry<Item, Integer> entry : Transport.getTargets().get(i).getItem_map().entrySet()) {
                            if (entry.getKey().to_string().equals(a)) {
                                Transport.getTargets().get(i).drop_Item(entry.getKey());
                                if(Transport.getTargets().get(i).getItem_map().isEmpty()){
                                    Transport.getTargets().remove(Transport.getTargets().get(i));
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
        Document document = Transport.getTargets().get(0);
        int count = 0;
        for (Document d : Transport.getTargets()) {
            if (d.to_string().equals(a)) {
                document = d;
                Transport.getTargets().remove(d);
                break;
            }
            count++;
        }
        ArrayList<Integer> arr = new ArrayList<>();
        for (Map.Entry<Item, Integer> iter : document.getItem_map().entrySet()) {
            Transport.getAll_transport_items().remove(iter.getKey());
            for (int i = count; i < Transport.getTargets().size(); i++) {
                if (Transport.getTargets().get(i).getTarget().getType().equals("Store")) {

                    if(Transport.getTargets().get(i).getItem_map().containsKey(iter.getKey())){
                        Transport.getTargets().get(i).drop_Item(iter.getKey());
                    }
                    if(Transport.getTargets().get(i).getItem_map().isEmpty()) {
                        arr.add(i);
                    }
                }
            }
        }

        if(arr.size() > 0) {
            for (int j = arr.size() - 1; j >= 0; j--) {
//                DataStructManager.remove_doc(arr.get(j));
                Transport.remove_Document(arr.get(j));

            }
        }

    }

}



