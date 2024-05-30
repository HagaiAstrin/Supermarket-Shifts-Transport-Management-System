package Presentation;

import Controller.EmployeeController;
import Controller.SystemController;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class UserMenu {
    private static final Scanner scanner = new Scanner(System.in); // Use a single scanner instance

    public static void Menu(){
        System.out.println("---------------------------------------------");
        System.out.println("|                 User Menu                 |");
        System.out.println("---------------------------------------------");
        while (true) {
            System.out.println("Please choose an option:\n");
            System.out.println("1. View personal details");
            System.out.println("2. Update preferences"); //TODO
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Call method to view personal details
                    JsonObject j =  EmployeeController.ViewPersonalData();
                    System.out.println(j);
                    break;
                case 2:
                    // Some update of preferences (???)
                    break;
                case 3:
                    // TODO: Implement!
                    SystemController.Logout();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
