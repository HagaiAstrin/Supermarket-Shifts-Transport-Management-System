package Controller;

import Domain.IO_Data;
import Domain.JobTypeEnum;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class DataController {
    private static final String DB_URL = "jdbc:sqlite:dev/Data/Be'erSheva.db";

    public static void importEmployees() {
        String query = "SELECT id, JobType, name, bankID, startDate, salary, restDays FROM Employees";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String[] jobTypeArray = resultSet.getString("JobType").split("/");
                ArrayList<JobTypeEnum> jobTypes = new ArrayList<>();
                for (String jobType : jobTypeArray) {
                    try {
                        jobTypes.add(JobTypeEnum.valueOf(jobType.trim().toUpperCase().replace(" ", "_")));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid job type: " + jobType);
                    }
                }
                String name = resultSet.getString("name");
                String bankID = resultSet.getString("bankID");
                String startDate = resultSet.getString("startDate");
                int salary = resultSet.getInt("salary");
                int restDays = resultSet.getInt("restDays");

                try {
                    // Parse the string to LocalDate
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(startDate, formatter);
                    IO_Data.appendEmployee(id, name, bankID, salary, restDays, date, jobTypes);
                } catch (Exception e) {
                    // TODO: Dont forge to remove the print for submission
                    System.out.println("Invalid date format for employee: " + name);
                }
            }
        } catch (SQLException e) {
            // TODO: Dont forge to remove the print for submission
            System.out.println("אמאלה");
            e.printStackTrace();
        }

        IO_Data.setFlag(true);
    }

    public static void addEmployeeFromJson(JsonObject employeeJson) {
        // TODO: Call from addEmployeeToCSV
        String sql = "INSERT INTO employees(id, name, bankID, salary, restDays, startDate, jobTypes) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            String id = employeeJson.get("id").getAsString();
            String jobTypes = employeeJson.get("jobType").getAsString();
            String name = employeeJson.get("name").getAsString();
            String bankID = employeeJson.get("bankID").getAsString();
            String startDate = employeeJson.get("startDate").getAsString();
            int salary = employeeJson.get("salary").getAsInt();
            int restDays = employeeJson.get("restDays").getAsInt();

            // Set parameters for the prepared statement
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, bankID);
            preparedStatement.setInt(4, salary);
            preparedStatement.setInt(5, restDays);
            preparedStatement.setString(6, startDate);
            preparedStatement.setString(7, jobTypes);

            preparedStatement.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
