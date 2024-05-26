package Controller;

import Domain.Constants;
import Domain.SHA_256_Hasher;

public class SystemController {
    public SystemController() {}

    public boolean LoginInputValidatorAdmin(String[] input) {
        return new SHA_256_Hasher().Checker(input, Constants.PATH_DATA_VALIDATION_Admin);
    }

    public boolean LoginInputValidatorUser(String[] input) {
        return new SHA_256_Hasher().Checker(input, Constants.PATH_DATA_VALIDATION_User);

    }

}
