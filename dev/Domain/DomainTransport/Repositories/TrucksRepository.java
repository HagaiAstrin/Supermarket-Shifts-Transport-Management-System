package Domain.DomainTransport.Repositories;

import DAL.DALTransport.DriversDAO;
import DAL.DALTransport.TrucksDAO;
import Domain.DomainTransport.Obejects.Truck;
import com.google.gson.JsonObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class TrucksRepository implements IRepository<Truck> {

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
    public JsonObject ChooseAll(JsonObject jas) throws SQLException {

        JsonObject j = new JsonObject();

        int day = jas.get("Day").getAsInt();
        int shift = jas.get("Shift").getAsInt();

        int count = 1;
        for (Truck t : AllTrucks) {
            String Preferences[][] = TrucksDAO.GET_TRUCK_TABLE(t.getLicence_number());

            if (Preferences[shift - 1][day - 1].equals("1")) {
                if (t.getStatus().equals("available")) {
                    j.addProperty(String.valueOf(count), t.to_String());
                    count++;
                }
            }
        }
        return j;
    }
    @Override
    public void Update(JsonObject j){

        String LicenceNumber = j.get("Licence number").getAsString();
        String newStatus = j.get("Status").getAsString();

        for (Truck t : AllTrucks){
            if (t.getLicence_number().equals(LicenceNumber))
                t.setStatus(newStatus);
        }
    }
    @Override
    public ArrayList<Truck> FindAll() {
        return AllTrucks;
    }
    @Override
    public int getAmount() {
        return AllTrucks.size();
    }
}
