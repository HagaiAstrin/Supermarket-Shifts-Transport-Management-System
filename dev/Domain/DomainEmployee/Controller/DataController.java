package Domain.DomainEmployee.Controller;

import Domain.DomainEmployee.IO_Data;
import Domain.DomainEmployee.JobTypeEnum;
import com.google.gson.JsonObject;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class DataController {
    private static String DB_URL = "jdbc:sqlite:dev/Data/";
    private static final int amount_shifts = 2;  // Assuming there are 2 shifts
    private static final int amount_days = 5;    // Assuming there are 5 days

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
                }
            }
        } catch (SQLException e) {
        }

        IO_Data.setFlag(true);
    }

    public static void addEmployee(JsonObject employeeJson) {
        // TODO: Call from addEmployeeToCSV
        String sql = "INSERT INTO employees(id, name, bankID, salary, restDays, startDate, jobType) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            String id = employeeJson.get("id").getAsString();
            String jobTypes = employeeJson.get("jobType").getAsString();
            String name = employeeJson.get("name").getAsString();
            String bankID = employeeJson.get("bankID").getAsString();
            String startDate = employeeJson.get("startDate").getAsString();
            int salary = employeeJson.get("salary").getAsInt();
            int restDays = employeeJson.get("restDays").getAsInt();

            // Convert startDate to the correct format
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(startDate, inputFormatter);
            String formattedDate = date.format(outputFormatter);

            // Set parameters for the prepared statement
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, bankID);
            preparedStatement.setInt(4, salary);
            preparedStatement.setInt(5, restDays);
            preparedStatement.setString(6, formattedDate);
            preparedStatement.setString(7, jobTypes);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public static String createPreferencesTable(JsonObject e) throws Exception {
        String id = e.get("id").getAsString();

        String createTableSQL = String.format(
                "CREATE TABLE IF NOT EXISTS \"%s\" (Sun INTEGER, Mon INTEGER, The INTEGER, Wen INTEGER, Thu INTEGER);",
                id
        );

        String insertRowSQL = String.format(
                "INSERT INTO \"%s\" (Sun, Mon, The, Wen, Thu) VALUES (1, 1, 1, 1, 1);",
                id
        );

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {

            // Create table
            statement.execute(createTableSQL);

            // Insert two rows
            statement.execute(insertRowSQL);
            statement.execute(insertRowSQL);

            return "Table created and rows inserted for id: " + id;
        } catch (SQLException ex) {
            throw new Exception("Problem with creating table or inserting rows for id: " + id, ex);
        }
    }

    public static String[][] importEmployeePreferences(String id) {
        String[][] preferences = new String[amount_shifts][amount_days];
        String getPreferencesSQL = String.format("SELECT Sun, Mon, The, Wen, Thu FROM \"%s\"", id);

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getPreferencesSQL)) {

            int row = 0;
            while (resultSet.next() && row < amount_shifts) {
                preferences[row][0] = resultSet.getString("Sun");
                preferences[row][1] = resultSet.getString("Mon");
                preferences[row][2] = resultSet.getString("The");
                preferences[row][3] = resultSet.getString("Wen");
                preferences[row][4] = resultSet.getString("Thu");
                row++;
            }
        } catch (SQLException e) {
        }

        return preferences;
    }

    public static void removeEmployee(String id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set parameter for the prepared statement
            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public static void removeEmployeeLogin(String id) {
        String sql = "DELETE FROM DataValidationUser WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set parameter for the prepared statement
            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public static void removeEmployeePreferences(String id) {
        String sql = String.format("DROP TABLE IF EXISTS \"%s\"", id);

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
            //System.out.println("Preferences table for employee ID " + id + " deleted successfully.");
        } catch (SQLException e) {

        }
    }

    public static void updateEmployeeField(String id, String fieldName, String fieldValue) {
        String sql = String.format("UPDATE Employees SET %s = ? WHERE id = ?", fieldName);

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, fieldValue);
            preparedStatement.setString(2, id);

            int rowsAffected = preparedStatement.executeUpdate();
            IO_Data.UpdateEmployee(id, fieldName, fieldValue);
            if (rowsAffected > 0) {
                System.out.println(fieldName + " updated successfully.");
            } else {
                System.out.println("Failed to update " + fieldName + " for employee ID " + id);
            }
        } catch (SQLException e) {

        }
    }

    public static boolean Authenticate(String username, String password, String id, String tableName) {
        String query = "";
        if(tableName.equals("admin")) {
            query = "SELECT COUNT(*) FROM DataValidationAdmin WHERE UserName = ? AND Password = ? AND ID = ?";
        }
        else{
            query = "SELECT COUNT(*) FROM DataValidationUser WHERE UserName = ? AND Password = ? AND ID = ?";
        }

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
        }

        return false;
    }

    public static void addNewLoginInfo(String id, String name) {
        String sql = "INSERT INTO DataValidationUser (UserName, Password, ID) VALUES (?, ?, ?)";

        // Default value for Password
        String defaultPassword = "4a44dc15364204a80fe80e9039455cc1608281820fe2b24f1e5233ade6af1dd5";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, defaultPassword);
            preparedStatement.setString(3, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New login info added successfully.");
            } else {
                System.out.println("Failed to add new login info.");
            }
        } catch (SQLException e) {

        }
    }


    public static void updatePreferencesToDB(String[][] preferences, String id) {
        String sql1 = String.format("UPDATE \"%s\" SET Sun=?, Mon=?, The=?, Wen=?, Thu=? WHERE rowid=1", id);
        String sql2 = String.format("UPDATE \"%s\" SET Sun=?, Mon=?, The=?, Wen=?, Thu=? WHERE rowid=2", id);

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmtMorning = conn.prepareStatement(sql1);
             PreparedStatement pstmtEvening = conn.prepareStatement(sql2)) {

            // Update morning preferences (row 0)
            pstmtMorning.setString(1, preferences[0][0]);
            pstmtMorning.setString(2, preferences[0][1]);
            pstmtMorning.setString(3, preferences[0][2]);
            pstmtMorning.setString(4, preferences[0][3]);
            pstmtMorning.setString(5, preferences[0][4]);
            pstmtMorning.executeUpdate();

            // Update evening preferences (row 1)
            pstmtEvening.setString(1, preferences[1][0]);
            pstmtEvening.setString(2, preferences[1][1]);
            pstmtEvening.setString(3, preferences[1][2]);
            pstmtEvening.setString(4, preferences[1][3]);
            pstmtEvening.setString(5, preferences[1][4]);
            pstmtEvening.executeUpdate();

        } catch (SQLException e) {
        }
    }


    public static void SetDB(String s) {
        DB_URL = "jdbc:sqlite:dev/Data/" + s + ".db";
    }
}