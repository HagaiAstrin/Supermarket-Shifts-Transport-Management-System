package presentation;

import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;
import domain.DataStructManager;
import domain.Truck;

import java.util.Scanner;

public class Add_Truck {

    public static void add_truck() {

        JsonObject new_json = new JsonObject();

        Scanner reader = new Scanner(System.in);

        String answer = "yes";

        while (answer.equals("yes")){
            System.out.println("Enter licence_number of 8 digits:");
            String licence_number = reader.next();

            while (licence_number.length() != 8){
                System.out.println("Wrong input! The licence_number should hava a 8 digit. try again..");
                System.out.println("Enter licence_number of 8 digits:");
                licence_number = reader.next();
            }

            System.out.println("Enter licence level: 'A', 'B' OR 'C'");
            String licence_level = reader.next();

            System.out.println("Please enter net_weight:");
            String net_weight = reader.next();

            System.out.println("Please enter max_weight:");
            String max_weight = reader.next();


            new_json.addProperty("licence number", licence_number);
            new_json.addProperty("licence level", licence_level);
            new_json.addProperty("net_weight", net_weight);
            new_json.addProperty("max_weight", max_weight);

            Transportation_manager_controller.add_truck(new_json);

            System.out.println("Added truck successful!");

            System.out.println("Would you like to add another truck? Enter 'yes' or 'no'");
            answer = reader.next();
        }

        reader.close();
    }
}
