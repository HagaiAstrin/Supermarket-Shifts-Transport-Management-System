package Presentation;

import Controller.EmployeeController;
import Controller.SystemController;
import Domain.IO_Data;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.Scanner;

public class UserMenu {
    public static Scanner scanner = new Scanner(System.in); // Use a single scanner instance

    public static void Menu(){
        System.out.println("---------------------------------------------");
        System.out.println("|                 User Menu                 |");
        System.out.println("---------------------------------------------");
        while (true) {
            System.out.println("Please choose an option:\n");
            System.out.println("1. View personal details");
            System.out.println("2. View preferences");
            System.out.println("3. Update preferences");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Call method to view personal details
                    JsonObject j =  EmployeeController.ViewPersonalData();
                    System.out.println(Printer.JsonToEmployee(j));
                    break;
                case 2:
                    String[][] preferences = EmployeeController.GetPreferences();
                    Printer.PrintPreferences(preferences);
                    break;
                case 3:
                    updatePreferences();
                    break;
                case 4:
                    // TODO: Implement!
                    SystemController.Logout();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void updatePreferences() {
        String[][] preferences = EmployeeController.GetPreferences();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nUpdate Preferences Menu:");
            System.out.println("1. Update Sunday");
            System.out.println("2. Update Monday");
            System.out.println("3. Update Tuesday");
            System.out.println("4. Update Wednesday");
            System.out.println("5. Update Thursday");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 6) {
                break;
            }
            if (choice > 6 || choice < 1) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            int row = 0;
            while(row != 1 && row != 2) {
                System.out.println("Enter row number to update (1 or 2): ");
                System.out.println("1. Update Morning");
                System.out.println("2. Update Evening");
                row = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (row != 1 && row != 2) {
                    System.out.println("Invalid row number.");
                }
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter new value for Sunday (1 or 0): ");
                    preferences[row][0] = scanner.nextLine().trim();
                    break;
                case 2:
                    System.out.print("Enter new value for Monday (1 or 0): ");
                    preferences[row][1] = scanner.nextLine().trim();
                    break;
                case 3:
                    System.out.print("Enter new value for Tuesday (1 or 0): ");
                    preferences[row][2] = scanner.nextLine().trim();
                    break;
                case 4:
                    System.out.print("Enter new value for Wednesday (1 or 0): ");
                    preferences[row][3] = scanner.nextLine().trim();
                    break;
                case 5:
                    System.out.print("Enter new value for Thursday (1 or 0): ");
                    preferences[row][4] = scanner.nextLine().trim();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            IO_Data.UpdatePreferencesToCSV(preferences);
        }
    }
}
