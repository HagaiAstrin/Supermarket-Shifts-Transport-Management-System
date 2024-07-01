package DAL;

import Domain.Obejects.Truck;

import java.sql.SQLException;
import java.util.List;

public class TrucksDAO implements IDAO<Truck>{
    @Override
    public Truck get(int id) {
        return null;
    }

    @Override
    public List<Truck> GET_ALL() {
        return null;
    }

    @Override
    public void INSERT(Truck truck) throws SQLException {

    }

    @Override
    public void UPDATE(Truck truck, String[] params) {

    }

    @Override
    public void DELETE(Truck truck) {

    }
}
