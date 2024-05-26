package Domain;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonObject;



public class IO_Data {
    static List<Employee> currEmployees = new ArrayList<>();
    static boolean flag = false;
    public static boolean isAdmin = false; // for user menu
    public static String employeeID; // for user interactions with his data
    /**
     * Import all employees data.
     * @return List of employees in Json format.
     */
    public static List<JsonObject> ImportEmployees() {
        List<JsonObject> employees = new ArrayList<>();
        String line;
        String csvSplitBy = ",";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(Constants.PATH_EMPLOYEES))) {
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] fields = line.split(csvSplitBy);

                if (fields.length == 7) { // Assuming the CSV has exactly 7 columns
                    String id = fields[0];
                    String jobType = fields[1];
                    String name = fields[2];
                    Date startDate = dateFormat.parse(fields[3]);
                    String bankID = fields[4];
                    int salary = Integer.parseInt(fields[5]);
                    int restDays = Integer.parseInt(fields[6]);

                    Employee employee = new Employee(id,  name, bankID, salary, restDays, startDate, jobType);
                    if(!flag){
                        currEmployees.add(employee);
                    }
                    Gson gson = new Gson();
                    employees.add(gson.toJsonTree(employee).getAsJsonObject());
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        flag = true;
        return employees;
    }

    /**
     * Add an Employee to the database.
     * @param json of an employee
     * @return a message of the performed action.
     */
    // TODO: Append to database.
    // TODO: add the employee to the .csv
    // TODO: add the employee to the List in IO_Data.
    public static String AddEmployee(JsonObject json){
        if(SearchEmployee(String.valueOf(json.get("id"))) == -1){
            return "Can't add employee with the same ID as " + json.get("id").getAsString() + ".\n";
        }

        System.out.println(json);
        return "Employee " + json.get("id").getAsString() + " has been added!\n";
    }

    /**
     * Add an Employee to the database.
     * @param id of an employee
     * @return a message of the performed action.
     */
    // TODO: Delete from database.
    public static boolean RemoveEmployee(String id){
        int indexToRemove = SearchEmployee(id);
        if(indexToRemove != -1){
            currEmployees.remove(indexToRemove);
            return true;
        }
        return false;
    }

    /**
     * Search for employee by ID
     * @param id to check
     * @return index of employee in the list if exists, otherwise -1
     */
    public static int SearchEmployee(String id){
        if(!flag) { return -1; }
        for(int i = 0; i < currEmployees.size(); i++){
            if(currEmployees.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public static boolean Authentication(String username, String password, String id, String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 3) continue; // Skip malformed lines

                String storedUsername = fields[0].trim();
                String storedHashedPassword = fields[1].trim();
                String storedID = fields[2].trim();

                if (storedUsername.equals(username) && storedHashedPassword.equals(password) && storedID.equals(id)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *  Get Employee in JSON format.
     * @param id of an employee
     * @return Employee as JSON, if not exists - null
     */
    public static JsonObject GetEmployee(String id){
        for(int i = 0; i < currEmployees.size(); i++){
            if(currEmployees.get(i).getId().equals(id)){
                return ConvertEmployeeToJson(currEmployees.get(i));
            }
        }
        return null;
    }

    /**
     *  Convert Employee to JSON format.
     */
    private static JsonObject ConvertEmployeeToJson(Employee employee){
        Gson gson = new Gson();
        return gson.toJsonTree(employee).getAsJsonObject();
    }
}
