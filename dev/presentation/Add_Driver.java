package presentation;

import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;

import java.util.Scanner;

public class Add_Driver {

    public static void add_driver() {

        JsonObject new_json = new JsonObject();

        Scanner reader = new Scanner(System.in);

        String answer = "yes";

        while (answer.equals("yes")){
            System.out.println("\nEnter the name of the driver:");
            String name = reader.next();

            System.out.println("Enter the license leve of the driver: 'A', 'B', 'C'");
            String license = reader.next();

            while (!license.equals("A") && !license.equals("B") && !license.equals("C")){
                System.out.println("\nWrong input! try again..");
                System.out.println("Enter the license leve of the driver: 'A', 'B', 'C'");
                license = reader.next();
            }

            System.out.println("Enter password of 5 digit:");
            String password = reader.next();

            while (password.length() != 5){
                System.out.println("\nWrong input! The password should hava a 5 digit. try again..");
                System.out.println("Enter password of 5 digit:");
                password = reader.next();
            }

            new_json.addProperty("Name",name);
            new_json.addProperty("Licence",license);
            new_json.addProperty("Password",password);

            Transportation_manager_controller.add_driver(new_json);

            System.out.println("Driver added successfully!");

            System.out.println("Would you like to add another driver? Enter 'yes' or 'no'.");
            answer = reader.next();
        }
    }
}
