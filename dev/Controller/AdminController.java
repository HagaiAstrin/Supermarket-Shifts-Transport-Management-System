package Controller;
import com.google.gson.Gson;
import Domain.*;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

public class AdminController {

    /**
     * Get all information regarding employees.
     * @return List of Json objects containing raw data about employees.
     */
    public static List<JsonObject> PrintEmployees(){
        return IO_Data.PrintEmployees();
    }
    public static void ImportEmployees(){
        IO_Data.ImportEmployees();
    }
    public static void AddEmployee(JsonObject json) throws IOException {
        IO_Data.addEmployeeToCSV(json);
        IO_Data.addEmployeeToList(json);
    }

    public static String RemoveEmployee(String id){
        try {
            if (IO_Data.RemoveEmployee(id)) {
                return "Employee " + id + " was removed successfully!\n";
            }
        }
        catch (IOException ignored) {

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
    public static void Shifts(){
        IO_Data.ImportPreferences();
        WeeklyShift damn = new WeeklyShift(1);
    }
}
