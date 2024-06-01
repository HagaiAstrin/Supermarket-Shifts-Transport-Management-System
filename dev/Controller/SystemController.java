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

    public static void Logout(){
        System.out.println("Dear user, thank you for using \"Super-Lee\" HR System!");
        System.out.println("Logged out successfully.");
    }

    public static Employee ConvertFronJsonToEmployee(JsonObject json){
        Gson gson = new Gson();
        return gson.fromJson(json, Employee.class);
    }
}
