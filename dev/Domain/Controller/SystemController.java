package Domain.Controller;

import Domain.*;

public class SystemController {
    /**
     * Hash function using for password validation.
     */
    public boolean LoginInputValidatorAdmin(String[] input) {
        return new SHA_256_Hasher().Checker(input, Constants.DEV + IO_Data.branch + Constants.PATH_DATA_VALIDATION_ADMIN);
    }

    /**
     * Hash function using for password validation.
     */
    public boolean LoginInputValidatorUser(String[] input) {
        return new SHA_256_Hasher().Checker(input, Constants.DEV + IO_Data.branch + Constants.PATH_DATA_VALIDATION_USER);

    }

    public static void Logout(){
        System.out.println("Dear user, thank you for using \"Super-Lee\" HR System!");
        System.out.println("Logged out successfully.");
        IO_Data.EraseEmployees();
        IO_Data.setFlag(false);
    }

    /**
     * Set the ID of the employee to perform certain tasks.
     */
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
