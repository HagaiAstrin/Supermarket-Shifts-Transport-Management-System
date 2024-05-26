package Controller;
import com.google.gson.Gson;
import Domain.*;
import com.google.gson.JsonObject;

import java.util.List;

public class AdminController {
    public AdminController() {
    }

    /**
     * Get all information regarding employees.
     * @return List of Json objects containing raw data about employees.
     */
    public List<JsonObject> PrintEmployees(){
        return IO_Data.ImportEmployees();
    }

    public void AddEmployee(JsonObject json){
        System.out.println(json);
    }

    public String RemoveEmployee(String id){
        if(IO_Data.RemoveEmployee(id)){
            return "Employee " + id + " was removed successfully!\n";
        }
        return "No employee was found with id: " + id + "\nPlease make sure you imported the employees data.\n";
    }
}
