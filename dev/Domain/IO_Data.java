package Domain;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import Domain.Controller.DataController;
import com.google.gson.JsonObject;


public class IO_Data {
    protected static Map<Integer, Employee> currEmployees = new HashMap<>();
    static boolean flag = false;
    static int amount_days = 5;
    static int amount_shifts = 2;
    public static String employeeID; // for user interactions with his data
    public static String branch = ""; // Where is the branch located.

    /**
     * Get a list of the current loaded employees in the system.
     */
    public static List<Employee> GetCurrEmployees() {
        Collection<Employee> employeeCollection = currEmployees.values();
        return new ArrayList<>(employeeCollection);
    }

    public static void SetCurrEmployees(Map<Integer, Employee> _currEmployees) {
        currEmployees = _currEmployees;
    }

    /**
     * Get the list of employees available in a specific time for shift arrangement.
     */
    public static List<JsonObject> getEmployeeHowCanWork(int job, int day, int shift) {
            return WeeklyShift.getEmployeeForShift(job, day,shift);
    }

    /**
     * Remove an employee from a shift.
     */
    public static String removeFromShiftIO(int id, int day, int shift, int job){
        Employee e = currEmployees.get(id);
        if (e == null){
            throw new RuntimeException("Employee not found");
        }
        if (day >= amount_days || day < 0 || shift >= amount_shifts || amount_shifts < 0){
            throw new RuntimeException("Invalid day or shift");
        }
        if (e.WeekPreferences[day][shift].equals("2")){
            if (WeeklyShift.removeEmployee(job, day, shift, id)){ //Changed in public calendar
                e.WeekPreferences[day][shift] = "1"; // save in Employee Personal preferences
                dayTypeEnum dte = dayTypeEnum.values()[day];
                ShiftType st = ShiftType.values()[shift];
                return e.getName() + "(id: " + id + ")" + " was removed from shift and change his preferences to not work on " + dte.toString() + " at " + st.toString() + " shift";
            }
        }
        return e.getName() + "(id: " + id + ")" + " was not shifted in this shift";
    }


    public static void setEmployeeHowCanWork(int job, int day, int shift, int id){
        try {
            WeeklyShift.setEmployeeHowCanWork(job, day, shift, id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public static void startWeek(){
        WeeklyShift.startWeek();
    }
    /**
     * Import all employees data.
     * @return List of employees in Json format.
     */
    public static void ImportEmployees() {
        DataController.importEmployees();
    }


    public static String[][] ImportEmployeePreferences(String id) {
        return DataController.importEmployeePreferences(id);
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

    /**
     * Add an Employee to the database.
     * @param id of an employee
     * @return a message of the performed action.
     */

    public static boolean RemoveEmployee(String id) throws IOException {
        int indexToRemove = SearchEmployee(id);
        if(indexToRemove != -1){
            DataController.removeEmployee(id);
            currEmployees.remove(Integer.parseInt(id));
            DataController.removeEmployeePreferences(id);
            DataController.removeEmployeeLogin(id);
            return true;
        }
        return false;
    }

    public static boolean RemoveEmployeeFromMap(String id) throws IOException {
        int indexToRemove = SearchEmployee(id);
        if(indexToRemove != -1){
            currEmployees.remove(Integer.parseInt(id));
            return true;
        }
        return false;
    }

    /**
     * Delete an employee's preferences from the csv file.
     */
    private static boolean deleteCsvFile(String id) {
        File file = new File(Constants.DEV + IO_Data.branch + Constants.PATH_DATA_PREFERENCES + id + ".csv");
        if (file.exists() && file.isFile()) {
            return file.delete();
        }
        return false;
    }

    /**
     * Remove an employee from the csv file
     */
    private static void RemoveEmployeeFromCSV(String id) throws IOException {
        List<String> lines = new ArrayList<>();
        String header = null;

        // Read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(Constants.DEV + branch + Constants.PATH_EMPLOYEE))) {
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.DEV + branch + Constants.PATH_EMPLOYEE))) {
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

    /**
     * Check validity of a user's login using SHA-256 encryption.
     */
    public static boolean Authentication(String username, String password, String id, String path) {
        return DataController.Authenticate(username, password, id, path);
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

    public static void UpdateEmployee(String id, String fieldName, String fieldValue){
        JsonObject employee = GetEmployee(id);

        employee.remove(fieldName);
        employee.addProperty(fieldName, fieldValue);
        try {
            IO_Data.RemoveEmployeeFromMap(id);
            currEmployees.put(Integer.valueOf(id), Employee.JsonToEmployee(employee));
        }
        catch (Exception e){

        }
    }


    public static void SetEmployeeID(String id){
        employeeID = id;
    }

    /**
     *  Convert Employee to JSON format.
     */
    private static JsonObject ConvertEmployeeToJson(Employee employee){
        return employee.toJson();
    }

    /**
     * Add an employee to csv file.
     */
    public static void addEmployeeToCSV(JsonObject employeeJson) throws IOException {
        DataController.addEmployee(employeeJson);
    }

    public static String createPreferencesCsv(JsonObject e) throws Exception{
        String id = e.get("id").getAsString();
        File file = new File(Constants.DEV + IO_Data.branch + Constants.PATH_DATA_PREFERENCES + id + ".csv");
        boolean result;

        try {
            // Create necessary directories if they do not exist
            file.getParentFile().mkdirs();

            // Create a new file
            result = file.createNewFile();
            if (result) {
                // Write data to the newly created file
                try (FileWriter writer = new FileWriter(file)) {
                    // Header
                    writer.append("Sun,Mon,The,Wen,Thu\n");
                    // Data rows
                    writer.append("1,1,1,1,1\n");
                    writer.append("1,1,1,1,1\n");
                }
                return "File created: " + file.getCanonicalPath(); // Returns the path string
            } else {
                throw new Exception("Problem with creating file: " + file.getCanonicalPath());
            }
        } catch (IOException ex) {
            throw new Exception("Problem with creating file: " + file.getCanonicalPath(), ex);
        }
    }

    public static void addEmployeeToList(JsonObject e) {
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

    /**
     * Get employee's preferences based on his ID from IO_Data.
     */
    public static String[][] GetPreferencesFromCSV(){
        return DataController.importEmployeePreferences(employeeID);
    }

    public static void UpdatePreferencesToCSV(String[][] preferences){
        DataController.updatePreferencesToDB(preferences, employeeID);
    }

    public static void SetBranchName(String s){
        DataController.SetDB(s);
        branch = s;
    }

    /**
     * Used to get branches of the Super-Lee shop. Usually Be'er-Sheva and Haifa.
     */
    public static List<String> listFoldersInDirectory() {
        File directory = new File("dev\\data");
        List<String> dbFiles = new ArrayList<>();

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".db")) {
                        // Remove the ".db" extension before adding to the list
                        String fileNameWithoutExtension = file.getName().substring(0, file.getName().length() - 3);
                        dbFiles.add(fileNameWithoutExtension);
                    }
                }
            }
        } else {
            return null;
        }

        return dbFiles;
    }

    public static void appendEmployee(String id, String name, String bankID, int salary, int restDays, LocalDate startDate,  ArrayList<JobTypeEnum> jobTypes){
        Employee employee = new Employee(id, name, bankID, salary, restDays, startDate, jobTypes);
        currEmployees.put(Integer.valueOf(employee.getId()), employee);
    }

    public static void setFlag(boolean value){
        flag = value;
    }

    public static void EraseEmployees() {
        currEmployees = new HashMap<>();
    }
}
