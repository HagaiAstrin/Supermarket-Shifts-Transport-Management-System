package Presentation;

import Domain.Controller.EmployeeController;
import Domain.Controller.SystemController;
import Domain.IO_Data;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class UserMenu {
    public static Scanner scanner = new Scanner(System.in); // Use a single scanner instance

    public static void Menu(){
        System.out.println("---------------------------------------------");
        System.out.println("|                 User Menu                 |");
        System.out.println("---------------------------------------------");
        while (true) {
            System.out.println("Please choose an option:\n");
            System.out.println("\t1. View personal details");
            System.out.println("\t2. View preferences");
            System.out.println("\t3. Update preferences");
            System.out.println("\t4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1: // Employees details
                    JsonObject j =  EmployeeController.ViewPersonalData();
                    System.out.println(Printer.JsonToEmployee(j));
                    break;
                case 2: // Check out the preferences
                    String[][] preferences = EmployeeController.GetPreferences();
                    Printer.PrintPreferences(preferences);
                    break;
                case 3: // Updating the preferences
                    updatePreferences();
                    break;
                case 4:
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
            System.out.println("\t1. Update Sunday");
            System.out.println("\t2. Update Monday");
            System.out.println("\t3. Update Tuesday");
            System.out.println("\t4. Update Wednesday");
            System.out.println("\t5. Update Thursday");
            System.out.println("\t6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

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
                scanner.nextLine();

                if (row != 1 && row != 2) {
                    System.out.println("Invalid row number.");
                }
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter new value for Sunday (1 or 0): ");
                    preferences[row-1][0] = scanner.nextLine().trim();
                    break;
                case 2:
                    System.out.print("Enter new value for Monday (1 or 0): ");
                    preferences[row-1][1] = scanner.nextLine().trim();
                    break;
                case 3:
                    System.out.print("Enter new value for Tuesday (1 or 0): ");
                    preferences[row-1][2] = scanner.nextLine().trim();
                    break;
                case 4:
                    System.out.print("Enter new value for Wednesday (1 or 0): ");
                    preferences[row-1][3] = scanner.nextLine().trim();
                    break;
                case 5:
                    System.out.print("Enter new value for Thursday (1 or 0): ");
                    preferences[row-1][4] = scanner.nextLine().trim();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            // Now send the preferences to the io_data for further update.
            IO_Data.UpdatePreferencesToCSV(preferences);
        }
    }
}
