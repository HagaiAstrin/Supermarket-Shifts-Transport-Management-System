package presentation;

import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;

import java.util.Scanner;

public class Add_Item {
    public static void add_items() {
        JsonObject j = new JsonObject();

        Scanner reader = new Scanner(System.in);

        System.out.println("\nPlease enter Item:");

        System.out.println("Name of the item:");
        j.addProperty("Name", reader.next());

        while (true) {
            System.out.println("\nWeight of the item:");
            String Weight = reader.next();
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
            String Amount = reader.next();
            try {
                Integer.parseInt(Amount);
                j.addProperty("Amount", Amount);
                break;
            } catch (Exception e) {
                System.out.println("\nWrong input! try again..");
            }
        }
        Transportation_manager_controller.create_items_list(j);
    }
}
