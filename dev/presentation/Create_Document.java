package presentation;

import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;

import java.util.Scanner;


public class Create_Document {
    public static void create(){

        Scanner reader = new Scanner(System.in);

        JsonObject new_json_document = new JsonObject();

        String a = "yes";

        while(a.equals("yes")) {
            System.out.println("Please enter the name of thee product:");
            String name = reader.next();

            System.out.println("Please enter the weight of this product:");
            String weight = reader.next();

            System.out.println("Please enter the amount of products would you like to order:");
            String amount = reader.next();

            new_json_document.addProperty("name", name);
            new_json_document.addProperty("weight", weight);
            new_json_document.addProperty("amount", amount);

            Transportation_manager_controller.create_document(new_json_document);

            System.out.println("Would you like to add products?\nPlease enter 'yes' or 'no'.");
            a = reader.next();
        }
    }
}
