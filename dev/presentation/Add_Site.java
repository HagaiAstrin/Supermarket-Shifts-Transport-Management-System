package presentation;

import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;

import java.util.Scanner;

public class Add_Site {

    public static void add_site(String Type){

        JsonObject new_json = new JsonObject();

        new_json.addProperty("Type", Type);

        Scanner reader = new Scanner(System.in);

        String a = "yes";

        while (a.equals("yes")) {

            System.out.println("Enter name:");
            new_json.addProperty("Name", reader.next());

            System.out.println("Enter address:");
            new_json.addProperty("Address", reader.next());

            System.out.println("Enter phone number:");
            new_json.addProperty("Phone number", reader.next());

            System.out.println("Enter contact:");
            new_json.addProperty("Contact", reader.next());

            System.out.println("Enter Shipping area:");
            new_json.addProperty("Shipping area", reader.next());

            Transportation_manager_controller.add_site(new_json);

            System.out.println("\n" + Type + "added successfully!\n");
            System.out.println("Would you like to add another " + Type + "? Press 'yes' or 'no'.");
            a = reader.next();
        }
    }
}
