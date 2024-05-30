package Domain;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;




public class IO_Data {
    protected static Map<Integer, Employee> currEmployees = new HashMap<>();
    protected static Map<Integer, String[][]> WeekPreferences = new HashMap<>();
    static boolean flag = false;
    static int amount_days = 5;
    static int amount_shifts = 2;
    public static boolean isAdmin = false; // for user menu
    public static String employeeID; // for user interactions with his data
    /**
     * Import all employees data.
     * @return List of employees in Json format.
     */
    public static void ImportEmployees() {
        String line;
        String csvSplitBy = ",";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(Constants.PATH_EMPLOYEES))) {
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] fields = line.split(csvSplitBy);

                if (fields.length == 7) { // Assuming the CSV has exactly 7 columns
                    String id = fields[0];
                    String[] jobTypeArray = fields[1].split("/");
                    ArrayList<JobTypeEnum> jobTypes = new ArrayList<>();
                    for (String jobType : jobTypeArray) {
                        try {
                            jobTypes.add(JobTypeEnum.valueOf(jobType.trim().toUpperCase().replace(" ", "_")));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid job type: " + jobType);
                        }
                    }
                    String name = fields[2];
                    String bankID = fields[3];
                    String startDate = fields[4];
                    int salary = Integer.parseInt(fields[5]);
                    int restDays = Integer.parseInt(fields[6]);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    try {
                        // Parse the string to LocalDate
                        LocalDate date = LocalDate.parse(startDate, formatter);
                        Employee employee = new Employee(id,  name, bankID, salary, restDays, date, jobTypes);
                        if(!flag){
                            currEmployees.put(Integer.valueOf(employee.getId()), employee);
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        flag = true;
    }
    public static void ImportPreferences(){
        for (Integer id : currEmployees.keySet()){
            if (!WeekPreferences.containsKey(id)){
                String[][] preferences = ImportEmployeePreferences(id);
                WeekPreferences.put(id, preferences);
            }
        }
    }
    public static String[][] ImportEmployeePreferences(Integer id) {
        //    static List<Employee> currEmployees = new ArrayList<>();

        String[][] preference = new String[amount_shifts][amount_days];
        String line;
        String csvSplitBy = ",";
        String empSpecificPath = Constants.PATH_DATA_Preferences + id.toString() + ".csv";

        try (BufferedReader br = new BufferedReader(new FileReader(empSpecificPath))) {
            br.readLine(); // Skip the header line

            int rowInt = -1;
           while ((line = br.readLine()) != null && rowInt<2){
              rowInt++;
                // Use comma as separator
               String[] fields = line.split(csvSplitBy);

              if (fields.length == 5) { // Assuming the CSV has exactly 5 columns
                  // Create a 2D array to store the fields

                    for (int i = 0; i < 5; i++) {
                     preference[rowInt][i] = fields[i];
                  }
             }
            }
        } catch (IOException e) {
          e.printStackTrace();
          System.out.println(id);
     }
        return preference;
    }

    public static List<JsonObject> PrintEmployees() {
        List<JsonObject> employees = new ArrayList<>();

        Collection<Employee> e_List = currEmployees.values();
        for(Employee employee : e_List){
            employees.add(employee.toJson());
        }

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

    /**
     * Add an Employee to the database.
     * @param id of an employee
     * @return a message of the performed action.
     */
    // TODO: Delete from database.
    public static boolean RemoveEmployee(String id) throws IOException {
        int indexToRemove = SearchEmployee(id);
        if(indexToRemove != -1){
            currEmployees.remove(Integer.parseInt(id));
            RemoveEmployeeFromCSV(id);
            return true;
        }
        return false;
    }

    private static void RemoveEmployeeFromCSV(String id) throws IOException {
        List<String> lines = new ArrayList<>();
        String header = null;

        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(Constants.PATH_EMPLOYEES))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (header == null) {
                    header = line; // Save the header
                    lines.add(header);
                    continue;
                }
                String[] values = line.split(",");
                if (!values[0].equals(id)) { // Assuming ID is the first column
                    lines.add(line);
                }
            }
        }

        // Write the updated data back to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.PATH_EMPLOYEES))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    /**
     * Search for employee by ID
     * @param id to check
     * @return index of employee in the list if exists, otherwise -1
     */
    public static int SearchEmployee(String id){
        if(!flag) { return -1; }
        int counter = 0;
        Collection<Employee> employees = currEmployees.values();
        for(Employee employee : employees){
            if(employee.getId().equals(id)){
                return counter;
            }
            counter++;
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
        Collection<Employee> employees = currEmployees.values();
        for(Employee employee : employees){
            if(employee.getId().equals(id)){
                return ConvertEmployeeToJson(employee);
            }
        }
        return null;
    }

    public static void SetEmployeeID(String id){
        employeeID = id;
    }

    /**
     *  Convert Employee to JSON format.
     */
    private static JsonObject ConvertEmployeeToJson(Employee employee){
        return employee.toJson();
        //return new Gson().toJsonTree(employee).getAsJsonObject();
    }

    public static void addEmployeeToCSV(JsonObject employeeJson) throws IOException {
        try (FileWriter writer = new FileWriter(Constants.PATH_EMPLOYEES, true)) {
            writer.append(employeeJsonToCSVString(employeeJson));
            writer.append("\n");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void addEmployeeToList(JsonObject e) {
        // TODO: CHECK IF WORKS PROPERLY!
        // Date does not work! Attribute is null.
        currEmployees.put(Integer.parseInt(e.get("id").getAsString()),Employee.JsonToEmployee(e));
    }

    public static LocalDate StringToDate(String dateStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // Parse the string to LocalDate
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
        }
        return null;
    }
    private static String employeeJsonToCSVString(JsonObject employeeJson) {
        StringBuilder csvString = new StringBuilder();
        csvString.append(employeeJson.get("id").getAsString()).append(",");
        csvString.append(employeeJson.get("jobType").getAsString()).append(",");
        csvString.append(employeeJson.get("name").getAsString()).append(",");
        csvString.append(employeeJson.get("bankID").getAsString()).append(",");


        //JsonObject contractJson = employeeJson.getAsJsonObject("contract");
        csvString.append(employeeJson.get("startDate").getAsString()).append(",");
        csvString.append(employeeJson.get("salary").getAsString()).append(",");
        csvString.append(employeeJson.get("restDays").getAsString()).append(",");
        // Add other fields as necessary
        return csvString.toString();
    }
}
