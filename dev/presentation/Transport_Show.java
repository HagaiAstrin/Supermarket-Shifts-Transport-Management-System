package presentation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;

public class Transport_Show {
    public static void show_all_Transportation(){
        JsonObject j = Transportation_manager_controller.show_all_Transport();
        if(j == null){
            System.out.println("\nNo transport has left the shipping area ! \n");
        }
        else {
            System.out.println("\n");
            System.out.println("\nAll the transport that left \n");
            for (String key : j.keySet()) {
                System.out.println("----------------------------------------------------------------\n");
                JsonElement element = j.get(key);
                String s = element.getAsString();
                System.out.println(s);
            }
        }
    }
}
