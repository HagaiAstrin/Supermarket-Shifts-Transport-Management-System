package Domain;

import java.util.ArrayList;

public class DriverRepository implements IRepository<Driver>{
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
    public Driver FindByIndex(int index) {
        return AllDrivers.get(index);
    }

    @Override
    public Driver Find(String s) {
        for (Driver d: AllDrivers){
            if (d.getWorkerNumber() == Integer.parseInt(s))
                return d;
        }
        return null;
    }
}
