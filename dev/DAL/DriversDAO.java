package DAL;

import Domain.Obejects.Driver;

import java.sql.SQLException;
import java.util.List;

public class DriversDAO implements IDAO<Driver>{
    @Override
    public Driver get(int id) {
        return null;
    }
    @Override
    public List<Driver> GET_ALL() {
        return null;
    }
    @Override
    public void INSERT(Driver driver) throws SQLException {

    }
    @Override
    public void UPDATE(Driver driver, String[] params) {

    }
    @Override
    public void DELETE(Driver driver) {
    }
}
