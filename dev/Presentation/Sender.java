package Presentation;

import com.google.gson.JsonObject;

import java.time.LocalDate;

public class Sender {
    // This class is responsible to send objects as JsonObject:
    public static JsonObject EmployeeToJson(String id, String name, String bankID, int salary, int restDays, LocalDate startDate, String workType){
        JsonObject json = new JsonObject();
        json.addProperty("id", id);
        json.addProperty("name", name);
        json.addProperty("bankID", bankID);
        json.addProperty("salary", salary);
        json.addProperty("restDays", restDays);
        json.addProperty("startDate", startDate.toString());
        json.addProperty("jobType", workType);

        return json;
    }
}
