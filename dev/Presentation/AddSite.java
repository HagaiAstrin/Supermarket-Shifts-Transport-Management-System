package Presentation;

import com.google.gson.JsonObject;
import Domain.TransportationManagerController;

import java.util.Scanner;

public class AddSite {

    /**
     * Input from the user for Site arguments
     * @param Type - String type represent Store / Supplier
     */
    public static void add_site(String Type){

        JsonObject new_json = new JsonObject();

        new_json.addProperty("Type", Type);

        Scanner reader = new Scanner(System.in);

        String a = "yes";

        while (a.equals("yes")) {

            System.out.println("\nEnter name:");
            new_json.addProperty("Name", reader.nextLine());

            System.out.println("\nEnter address:");
            new_json.addProperty("Address", reader.nextLine());

            System.out.println("\nEnter phone number:");
            new_json.addProperty("Phone number", reader.nextLine());

            System.out.println("\nEnter contact:");
            new_json.addProperty("Contact", reader.nextLine());

            System.out.println("\nEnter Shipping area:");
            new_json.addProperty("Shipping area", reader.nextLine());

            TransportationManagerController.add_site(new_json);

            System.out.println("\n" + Type + " added successfully!\n");
            System.out.println("Would you like to add another " + Type + "? Press 'yes' or 'no'.");
            a = reader.nextLine();
        }
    }
}
