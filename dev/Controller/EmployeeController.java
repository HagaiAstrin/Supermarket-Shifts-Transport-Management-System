package Controller;
import com.google.gson.Gson;
import Domain.*;
import com.google.gson.JsonObject;

import java.io.IOException;

public class EmployeeController {
    public static JsonObject ViewPersonalData(){
        return IO_Data.GetEmployee(IO_Data.employeeID);
    }

    public static void AddEmployee(JsonObject employee) throws IOException {
        IO_Data.addEmployeeToCSV(employee);
    } //TODO check it if not in use delete in UML
    }

    public static String[][] GetPreferences(){
        return IO_Data.GetPreferencesFromCSV();

    }
}
