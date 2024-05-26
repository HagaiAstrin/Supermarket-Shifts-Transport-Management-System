package Controller;
import com.google.gson.Gson;
import Domain.*;
import com.google.gson.JsonObject;
public class EmployeeController {
    private static String employeeID; // Determines which data to show based on ID.

    public static void SetID(String id){
        employeeID = id;
    }

//    public static JsonObject ViewPersonalData(String employeeID){
//
//    }
}
