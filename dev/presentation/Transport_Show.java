package presentation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;

public class Transport_Show {
    public static void show_all_Transportation(){
        JsonObject j = Transportation_manager_controller.show_all_Transport();
        if(j == null){
            System.out.println("No transport has taken place ! \n");
        }
        else {
            System.out.println("\n");
            int x = 1;
            for (String key : j.keySet()) {
                if(x != 1) {
                    System.out.println("----------------------------------------------------------------\n");
                }
                x++;
                JsonElement element = j.get(key);
                String s = element.getAsString();
                System.out.println(s);
            }
        }
    }
}
