package Domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IO_Data {
    public List<Employee> readCsvFile(String filePath) {
        List<Employee> employees = new ArrayList<>();
        String line;
        String csvSplitBy = ",";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
                    employees.add(employee);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return employees;
    }
}
