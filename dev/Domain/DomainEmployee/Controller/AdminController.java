package Domain.DomainEmployee.Controller;
import Domain.DomainEmployee.*;
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

    /**
     * Import employees from csv file
     */
    public static void ImportEmployees(){
        IO_Data.ImportEmployees();
        IO_Data.startWeek();
        // TODO: load template table to memory
    }

    /**
     * Adding new employee to the system
     */
    public static void AddEmployee(JsonObject json) throws IOException {
        IO_Data.addEmployeeToCSV(json);
        IO_Data.addEmployeeToList(json);
    }
    public static String createPreferencesNewEmp(JsonObject json) throws Exception {
        return DataController.createPreferencesTable(json);
        //return IO_Data.createPreferencesCsv(json);
    }

    /**
     * Removing new employee to the system
     */
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

    /**
     * Search for an employee to the system
     */
    public static boolean SearchEmployee(String id){
        return IO_Data.SearchEmployee(id) != 1;
    }

    /**
     * Get an employee based on his id as a JsonObject.
     */
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

    /**
     * Add an attribute for a shift - id and job of employee on day and time of the shift.
     */
    public static void SetShift(int job, int day, int shift, int id) throws Exception {
        IO_Data.setEmployeeHowCanWork(job, day, shift, id);
        DataController.InsertToTemplateTable(day, shift, String.valueOf(id), JobTypeEnum.values()[job]);
    }

    public static String removeShift(int id, int day, int shift, int job){
        try {
            DataController.RemoveFromTemplateTable(day, shift, String.valueOf(id), JobTypeEnum.values()[job]);
            String ret = IO_Data.removeFromShiftIO(id, day, shift, job);
            return ret;
        }
        catch (RuntimeException e){
            return e.getMessage();
        }
    }

    /**
     * Get the list of employees in a certain shift.
     */
    public static List<JsonObject> getEmployeeToShift(int job, int day, int shift){
        return IO_Data.getEmployeeHowCanWork(job,day,shift);
    }

    public static void AddNewLoginInfo(String id, String name) {
        DataController.addNewLoginInfo(id, name);
    }

    public static void RestartWeek() {
        DataController.recreateTemplateTable();
    }

    public static void setShiftedEmployees(List<List<List<String>>> lst) {
        WeeklyShift.setShiftedAllEmployees(lst);
    }
}
