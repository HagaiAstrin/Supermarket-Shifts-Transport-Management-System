package Domain.Repositories;

import Domain.Obejects.Driver;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class DriverRepository implements IRepository<Driver> {
    private static ArrayList<Driver> AllDrivers = new ArrayList<>();

    @Override
    public void Add(Driver driver) {
        AllDrivers.add(driver);
    }
    @Override
    public void Delete(Driver driver) {
        AllDrivers.remove(driver);
    }
    @Override
    public Driver FindByID(String ID) {
        for (Driver d: AllDrivers){
            if (d.to_String().equals(ID))
                return d;
        }
        return null;
    }
    @Override
    public JsonObject ChooseAll(String s, String v) {

        JsonObject j = new JsonObject();
        int count = 1;
        for (Driver d: AllDrivers){
            if (d.getStatus().equals("available")){
                if (d.getLicense().compareTo(s) >= 0){
                    j.addProperty(String.valueOf(count), d.to_String());
                    count++;
                }
            }
        }
        return j;
    }

    @Override
    public void Update(JsonObject j) {

    }

    @Override
    public ArrayList<Driver> FindAll() {
        return AllDrivers;
    }
    @Override
    public int getAmount() {
        return AllDrivers.size();
    }

}
