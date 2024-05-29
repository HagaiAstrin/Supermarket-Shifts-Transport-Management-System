package Presentation;

import com.google.gson.JsonObject;

import java.util.List;

public class Printer {
    // TODO: Get data as Json and print it with friendly manner.
    public static void PrintAllEmployees(List<JsonObject> employees){
        String str = "Employees List:\n";
        for(int i = 0; i < employees.size(); i++){
            str += JsonToEmployee(employees.get(i));
        }

        System.out.println(str);
    }

    private static String JsonToEmployee(JsonObject employee){
        String str = "";

        str += "\n---- ID: ";
        str += employee.get("id").getAsString();

        str += "\nName: " + employee.get("name").getAsString() + "\n";
        str += "Job Type: " + employee.get("jobType").getAsString() + "\n";

        return str;
    }


}
