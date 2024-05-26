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
    /**
     * Import all employees data.
     * @return List of employees in Json format.
     */
    public List<JsonObject> ImportEmployees() {
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
                    Gson gson = new Gson();
                    employees.add(gson.toJsonTree(employee).getAsJsonObject());
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return employees;
    }


    public boolean Authentication(String username, String password, String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(Constants.PATH_DATA_VALIDATION))) {
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
}
