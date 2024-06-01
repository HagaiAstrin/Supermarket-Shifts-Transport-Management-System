package Controller;
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
        IO_Data.startWeek();
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

    public static String printAllTypeWeek(){
        return WeeklyShift.iterAllJobWeeklyShift();
    }

    public static String printTypeWeek(int job){
        return WeeklyShift.getJobWeeklyShift(job);
    }
    public static void checkEmployeeWeekFull(int amount) throws Exception {
        WeeklyShift.checkEmployeeForEachJobType(amount);
    }

    public static void SetShift(int job, int day, int shift, int id){
        IO_Data.setEmployeeHowCanWork(job, day, shift, id);
    }

    public static String removeShift(int id, int day, int shift, int job){
        return IO_Data.removeFromShiftIO(id, day, shift, job);
    }
    public static List<JsonObject> getEmployeeToShift(int job, int day, int shift){
        return IO_Data.getEmployeeHowCanWork(job,day,shift);
    }
}
