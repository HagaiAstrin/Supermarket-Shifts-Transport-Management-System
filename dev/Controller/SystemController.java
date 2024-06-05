package Controller;

import Domain.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SystemController {
    public boolean LoginInputValidatorAdmin(String[] input) {
        return new SHA_256_Hasher().Checker(input, Constants.DEV + IO_Data.branch + Constants.PATH_DATA_VALIDATION_ADMIN);
    }

    public boolean LoginInputValidatorUser(String[] input) {
        return new SHA_256_Hasher().Checker(input, Constants.DEV + IO_Data.branch + Constants.PATH_DATA_VALIDATION_USER);

    }

    public static void Logout(){
        System.out.println("Dear user, thank you for using \"Super-Lee\" HR System!");
        System.out.println("Logged out successfully.");
    }

    public static void setEmployeeIDIOData(String setID){
        IO_Data.SetEmployeeID(setID);
    }

    public static String[] getEnumArray(){
        JobTypeEnum[] enumArray = JobTypeEnum.values();
        String[] array = new String[enumArray.length];
        for (int i = 0; i < enumArray.length; i++) {
            array[i] = enumArray[i].toString();
        }
        return array;
    }

    public static void SetBranchName(String branchName){
        IO_Data.SetBranchName(branchName);
    }

}
