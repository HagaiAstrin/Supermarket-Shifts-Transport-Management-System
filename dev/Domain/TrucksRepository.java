package Domain;

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
    public Truck FindByIndex(int index) {
        return AllTrucks.get(index);
    }

    @Override
    public Truck Find(String s) {
        for (Truck t: AllTrucks){
            if (t.getLicence_number().equals(s))
                return t;
        }
        return null;
    }
    public ArrayList<Truck> FindAll(){
        return AllTrucks;
    }
}
