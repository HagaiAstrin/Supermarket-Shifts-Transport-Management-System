package presentation;

import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;


import java.util.Scanner;

public class Add_Truck {

    public static void add_truck() {

        JsonObject new_json = new JsonObject();

        Scanner reader = new Scanner(System.in);

        String answer = "yes";

        while (answer.equals("yes")){

            System.out.println("\nEnter licence_number of 8 digits:");
            String licence_number = reader.next();

            while (licence_number.length() != 8){
                System.out.println("\nWrong input! The licence_number should hava a 8 digit. try again..");
                System.out.println("Enter licence_number of 8 digits:");
                licence_number = reader.next();
            }

            System.out.println("Enter the license leve of the Truck: 'A', 'B', 'C'");
            String licence_level = reader.next();

            while (!licence_level.equals("A") && !licence_level.equals("B") && !licence_level.equals("C")){
                System.out.println("\nWrong input! try again..");
                System.out.println("Enter the license leve of the driver: 'A', 'B' OR 'C'");
                licence_level = reader.next();
            }

            String net_weight = null;
            while (net_weight == null){
                System.out.println("Please enter net weight:");
                net_weight = reader.next();

                try {
                    Double.parseDouble(net_weight);
                }
                catch (Exception e){
                    System.out.println("\nWrong input! try again..");
                    net_weight = null;
                }
            }

            String max_weight = null;
            while (max_weight == null){
                System.out.println("\nPlease enter max weight:");
                max_weight = reader.next();

                try {
                    Double.parseDouble(max_weight);
                }
                catch (Exception e){
                    System.out.println("\nWrong input! try again..");
                    max_weight = null;
                }
            }

            new_json.addProperty("Licence number", licence_number);
            new_json.addProperty("Licence level", licence_level);
            new_json.addProperty("Net weight", net_weight);
            new_json.addProperty("Max weight", max_weight);

            Transportation_manager_controller.add_truck(new_json);

            System.out.println("Truck added successfully!");

            System.out.println("Would you like to add another truck? Enter 'yes' or 'no'");
            answer = reader.next();
        }
    }
}
