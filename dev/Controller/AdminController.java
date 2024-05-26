package Controller;
import com.google.gson.Gson;
import Domain.*;
import com.google.gson.JsonObject;

import java.util.List;

public class AdminController {

    /**
     * Get all information regarding employees.
     * @return List of Json objects containing raw data about employees.
     */
    public static List<JsonObject> PrintEmployees(){
        return IO_Data.ImportEmployees();
    }

    public static String AddEmployee(JsonObject json){
        return IO_Data.AddEmployee(json);
        //System.out.println(json);
    }

    public String RemoveEmployee(String id){
        if(IO_Data.RemoveEmployee(id)){
            return "Employee " + id + " was removed successfully!\n";
        }
        return "No employee was found with id: " + id + "\nPlease make sure you imported the employees data.\n";
    }

    public static boolean SearchEmployee(String id){
        return IO_Data.SearchEmployee(id) != 1;
    }

    public static JsonObject GetEmployee(String id){
        return IO_Data.GetEmployee(id);
    }

    //TODO: Update employee data in .csv and in list.
    public static void UpdateEmployee(JsonObject json){

    }
}
