package Service;

import Domain.DriverController;
import com.google.gson.JsonObject;

public class DriverApplication {
    DriverController DC = new DriverController();


    public String check_driver_logIn(JsonObject j) {
        return DC.check_driver_logIn(j);
    }

    public String update_back(JsonObject j) {
        return DC.update_back(j);
    }

    public String update_leaving(JsonObject j) {
        return DC.update_leaving(j);
    }

    public StringBuilder Print_document(JsonObject j) {
        return DC.Print_document(j);
    }
}
