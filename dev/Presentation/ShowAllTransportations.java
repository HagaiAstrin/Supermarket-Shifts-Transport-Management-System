package Presentation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import Domain.TransportationManagerController;

public class ShowAllTransportations {

    /**
     * Print to user all the Transport that left the shipping area
     */
    public static void show_all_Transportation(){
        JsonObject j = TransportationManagerController.show_all_Transport();
        if(j == null){
            System.out.println("\nNo transport has left the shipping area ! \n");
        }
        else {
            System.out.println("\n");
            System.out.println("\n\n\nAll the transport that left \n");
            for (String key : j.keySet()) {
                System.out.println("----------------------------------------------------------------\n");
                JsonElement element = j.get(key);
                String s = element.getAsString();
                System.out.println(s);
            }
        }
    }
}
