package Controller;
import com.google.gson.Gson;
import Domain.*;
import com.google.gson.JsonObject;

import java.util.List;

public class AdminController {
    private IO_Data io_data;
    public AdminController() {
        io_data = new IO_Data();
    }

    /**
     * Get all information regarding employees.
     * @return List of Json objects containing raw data about employees.
     */
    public List<JsonObject> PrintEmployees(){
        return io_data.ImportEmployees(Constants.PATH_EMPLOYEES);
    }

    public void AddEmployee(JsonObject json){
        System.out.println(json);
    }
}
