package Domain;

import java.util.HashMap;
import java.util.Map;

public class SitesRepository implements IRepository <Site>{

    private static Map<String, Map<String, Map<String, Site>>> AllSites = new HashMap<>();

    @Override
    public void Add(Site s) {
        Map<String, Site> map = AllSites.get(s.getShipping_area()).get(s.getType());
        if (!map.containsKey(s.getName())) {
            AllSites.get(s.getShipping_area()).get(s.getType()).put(s.getName(), s);
        }
    }
    public void AddShippingArea(String Shipping_area){
        Map<String, Map<String, Site>> map = new HashMap<>();
        map.put("Store",new HashMap<>());
        map.put("Supplier",new HashMap<>());
        AllSites.put(Shipping_area, map);
    }

    @Override
    public void Delete(Site s) {
//        String area = s.getShipping_area();
//
//        int count = 1;
//        for (Map.Entry<String, Site> iter :AllSites.get(area).get(type).entrySet()) {
//            j.addProperty(String.valueOf(count++), iter.getValue().to_string());
//        }
    }

    @Override
    public Site FindByIndex(int index) {
        return null;
    }

    @Override
    public Site Find(String s) {
        return null;
    }
}
