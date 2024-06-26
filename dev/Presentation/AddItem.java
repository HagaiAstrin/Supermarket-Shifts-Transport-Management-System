package Presentation;

import Domain.TransportationController;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class AddItem {

    /**
     * Add Item from the user
     */
    public static void add_items() {
        JsonObject j = new JsonObject();

        Scanner reader = new Scanner(System.in);

        System.out.println("\nPlease enter Item:");

        System.out.println("Name of the item:");
        j.addProperty("Name", reader.nextLine());

        while (true) {
            System.out.println("\nWeight of the item:");
            String Weight = reader.nextLine();
            try {
                Double.parseDouble(Weight);
                j.addProperty("Weight", Weight);
                break;
            } catch (Exception e) {
                System.out.println("\nWrong input! try again..");
            }
        }

        while (true) {
            System.out.println("\nAmount of the items");
            String Amount = reader.nextLine();
            try {
                Integer.parseInt(Amount);
                j.addProperty("Amount", Amount);
                break;
            } catch (Exception e) {
                System.out.println("\nWrong input! try again..");
            }
        }
        TransportationController.add_item(j, "Supplier");
    }
}
