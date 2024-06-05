package Controller;
import com.google.gson.Gson;
import Domain.*;
import com.google.gson.JsonObject;

import java.io.IOException;

public class EmployeeController {
    public static JsonObject ViewPersonalData() {
        return IO_Data.GetEmployee(IO_Data.employeeID);
    }

    /**
     * Get employee preferences based on his ID in the IO_Data
     */
    public static String[][] GetPreferences() {
        return IO_Data.GetPreferencesFromCSV();

    }
}