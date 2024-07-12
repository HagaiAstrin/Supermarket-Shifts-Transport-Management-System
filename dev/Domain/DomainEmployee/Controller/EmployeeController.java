package Domain.DomainEmployee.Controller;
import Domain.DomainEmployee.*;
import com.google.gson.JsonObject;

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