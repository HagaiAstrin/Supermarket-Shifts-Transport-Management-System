package Domain;

import com.google.gson.JsonObject;

import java.util.ArrayList;

public class TrucksRepository implements IRepository<Truck>{

    private static ArrayList<Truck> AllTrucks = new ArrayList<>();

    @Override
    public void Add(Truck truck) {
        AllTrucks.add(truck);
    }
    @Override
    public void Delete(Truck truck) {
        AllTrucks.remove(truck);
    }
    @Override
    public Truck FindByID(String ID) {
        for (Truck t: AllTrucks){
            if (t.to_String().equals(ID))
                return t;
        }
        return null;
    }
    @Override
    public JsonObject FindAll(String s, String v) {
        JsonObject j = new JsonObject();
        int count = 1;
        for (Truck t: AllTrucks){
            if (t.getStatus().equals("available")){
                j.addProperty(String.valueOf(count), t.to_String());
                count++;
            }
        }
        return j;
    }
    @Override
    public JsonObject FindMore() {
        return null;
    }
    @Override
    public int getAmount() {
        return AllTrucks.size();
    }
}
