package Domain.DomainTransport.Repositories;

import DAL.DALTransport.IDAO;
import DAL.DALTransport.SitesDAO;
import Domain.DomainTransport.Controllers.DataController;
import Domain.DomainTransport.Obejects.Site;
import com.google.gson.JsonObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SitesRepository implements IRepository<Site> {

    private static Map<String, Map<String, Map<String, Site>>> AllSites = new HashMap<>();


    /**
     * Add a Site to the AllSites
     */
    @Override
    public void Add(Site s) {

        AddShippingArea(s.getShipping_area());

        Map<String, Site> map = AllSites.get(s.getShipping_area()).get(s.getType());

        if (!map.containsKey(s.getName())) {
            AllSites.get(s.getShipping_area()).get(s.getType()).put(s.getName(), s);
        }
    }

    /**
     * Delete a Site from the AllSites
     */
    @Override
    public void Delete(Site s) {

        Map<String, Site> map = AllSites.get(s.getShipping_area()).get(s.getType());

        if (map.containsKey(s.getName())) {
            AllSites.get(s.getShipping_area()).get(s.getType()).remove(s.getName());
        }
    }

    /**
     * Find a Site from the AllSites using String ID
     */
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

    /**
     * Returns JsonObject of all the Sites in the AllSites and inside the Specific area
     */
    @Override
    public JsonObject ChooseAll(JsonObject jas) throws SQLException {

        String area = jas.get("Area").getAsString();
        String type = jas.get("Type").getAsString();
        String day = jas.get("Day").getAsString();
        String time = jas.get("Time").getAsString();

        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Site> iter : AllSites.get(area).get(type).entrySet()) {

            if (type.equals("Store")){
                String name = iter.getValue().getName();

                boolean answer = DataController.isStorekeeper(name, day, time);

                if (!answer)
                    continue;
            }

            j.addProperty(String.valueOf(count++), iter.getValue().to_string());
        }
        return j;
    }
    @Override
    public void Update(JsonObject j) {

    }
    @Override
    public ArrayList<Site> FindAll() {
        return null;
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

    // Only Sites Repository Methods:
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
    public JsonObject FindShippingArea(){
        JsonObject j = new JsonObject();
        int count = 1;
        for (Map.Entry<String, Map<String, Map<String, Site>>> iter : AllSites.entrySet()) {
            j.addProperty(String.valueOf(count++), iter.getKey());
        }
        return j;
    }

    /**
     * @return the AllSites
     */
    public Map<String, Map<String, Map<String, Site>>> FindAllSites(){
        return AllSites;
    }
}
