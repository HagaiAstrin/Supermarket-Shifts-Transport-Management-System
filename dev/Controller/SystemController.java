package Controller;

import Domain.Constants;
import Domain.Employee;
import Domain.SHA_256_Hasher;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SystemController {
    public SystemController() {}

    public boolean LoginInputValidatorAdmin(String[] input) {
        return new SHA_256_Hasher().Checker(input, Constants.PATH_DATA_VALIDATION_Admin);
    }

    public boolean LoginInputValidatorUser(String[] input) {
        return new SHA_256_Hasher().Checker(input, Constants.PATH_DATA_VALIDATION_User);

    }

    // TODO: Save all data into .csv!!!!
    public static void Logout(){

    }
    public static JsonObject ConvertEmployeeToJson(Employee employee){
        Gson gson = new Gson();
        return gson.toJsonTree(employee).getAsJsonObject();
    }
}
