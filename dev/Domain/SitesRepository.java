package Domain;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class SitesRepository implements IRepository <Site>{

    private static Map<String, Map<String, Map<String, Site>>> AllSites = new HashMap<>();

    @Override
    public void Add(Site s) {

        AddShippingArea(s.getShipping_area());

        Map<String, Site> map = AllSites.get(s.getShipping_area()).get(s.getType());

        if (!map.containsKey(s.getName())) {
            AllSites.get(s.getShipping_area()).get(s.getType()).put(s.getName(), s);
        }
    }
    @Override
    public void Delete(Site s) {

        Map<String, Site> map = AllSites.get(s.getShipping_area()).get(s.getType());

        if (map.containsKey(s.getName())) {
            AllSites.get(s.getShipping_area()).get(s.getType()).remove(s.getName());
        }
    }
    @Override
    public Site FindByID(String ID) {
        for (Map.Entry<String, Map<String, Map<String, Site>>> SA : AllSites.entrySet()){
            for (Map.Entry<String, Map<String, Site>> Type: AllSites.get(SA.getKey()).entrySet()){
                for (Map.Entry<String, Site> site : AllSites.get(SA.getKey()).get(Type.getKey()).entrySet()){
                    if (site.getValue().to_string().equals(ID))
                        return site.getValue();
                }
            }
        }
        return null;
    }
    @Override
    public JsonObject FindAll(String area, String type) {
        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Site> iter : AllSites.get(area).get(type).entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getValue().to_string());
        }
        return j;
    }
    @Override
    public JsonObject FindMore() {
        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Map<String, Map<String, Site>>> iter : AllSites.entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getKey());
        }
        return j;
    }
    @Override
    public int getAmount() {
        int count = 0;
        for (Map.Entry<String, Map<String, Map<String, Site>>> SA : AllSites.entrySet()){
            for (Map.Entry<String, Map<String, Site>> Type: AllSites.get(SA.getKey()).entrySet()){
                for (Map.Entry<String, Site> site : AllSites.get(SA.getKey()).get(Type.getKey()).entrySet()){
                    count++;
                }
            }
        }
        return count;
    }

    public void AddShippingArea(String s){
        for (Map.Entry<String, Map<String, Map<String, Site>>> iter : AllSites.entrySet()) {
            if (iter.getKey().equals(s))
                return;
        }
        Map<String, Map<String, Site>> m = new HashMap<>();

        m.put("Store",new HashMap<>());
        m.put("Supplier",new HashMap<>());

        AllSites.put(s, m);
    }
}
