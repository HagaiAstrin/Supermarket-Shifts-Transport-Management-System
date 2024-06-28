package Domain;

import com.google.gson.JsonObject;

import java.util.ArrayList;

public class TransportsRepository implements IRepository<TransportDocument>{

    private static ArrayList<TransportDocument> allTransportations =  new ArrayList<>();

    @Override
    public void Add(TransportDocument s) {
        allTransportations.add(s);
    }
    @Override
    public void Delete(TransportDocument s) {
        allTransportations.remove(s);
    }
    @Override
    public TransportDocument FindByID(String ID) {
        for (TransportDocument tran: allTransportations){
            if (tran.getID() == Integer.parseInt(ID))
                return tran;
        }
        return null;
    }
    @Override
    public JsonObject FindAll(String s, String v) {
        JsonObject j = new JsonObject();
        int count = 1;
        for (TransportDocument t : allTransportations){
            j.addProperty(String.valueOf(count++), t.to_String());
        }
        return j;
    }
    @Override
    public JsonObject FindMore() {
        return null;
    }
    @Override
    public int getAmount() {
        return allTransportations.size();
    }
}
