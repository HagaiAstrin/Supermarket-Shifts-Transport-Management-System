package Controller;
import com.google.gson.Gson;
import Domain.*;
import com.google.gson.JsonObject;
public class EmployeeController {
    private IO_Data io_data;
    private String employeeID; // Determines which data to show based on ID.
    public EmployeeController(){
        io_data = new IO_Data();
    }

//    public JsonObject viewPersonalDetails(){
//
//    }
}
