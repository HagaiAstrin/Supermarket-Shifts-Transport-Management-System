package Presentation;

import Controller.AdminController;
import Controller.SystemController;

import java.io.IOException;
import java.util.Scanner;

public class AdminMenu {
    private static final Scanner scanner = new Scanner(System.in); // Use a single scanner instance

    public static void FirstMenu() throws InterruptedException, IOException {
        Config();
        System.out.println("---------------------------------------------");
        System.out.println("|                 Admin Menu                |");
        System.out.println("---------------------------------------------");
        while (true) {
            System.out.println("Please choose an option:\n");
            System.out.println("1. Load employees data");
            System.out.println("2. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    ProgressBar();
                    System.out.println("Data was loaded successfully.");
                    Thread.sleep(500);
                    AdminController.ImportEmployees();
                    Menu();
                    return;
                case 2:
                    // TODO: Implement LOGOUT
                    SystemController.Logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void Config(){

    }

    public static void Menu () throws IOException, InterruptedException {
        System.out.println("---------------------------------------------");
        System.out.println("|                 Admin Menu                |");
        System.out.println("---------------------------------------------");
        while (true) {
            System.out.println("Please choose an option:\n");
            System.out.println("1. Add new employee");
            System.out.println("2. Remove employee");
            System.out.println("3. Update employee details");
            System.out.println("4. Logout");
            System.out.println("5. Statistics");
            System.out.println("6. Manage Shifts");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Call method to add new employee
                    Menu.AddEmployee();
                    break;
                case 2:
                    // Call method to remove employee
                    Menu.RemoveEmployee();
                    break;
                case 3:
                    // Call method to update employee details
                    Menu.UpdateEmployeeDetails();
                    break;
                case 4:
                    // TODO: Implement LOGOUT
                    SystemController.Logout();
                    return;
                case 5:
                    Printer.PrintAllEmployees(AdminController.PrintEmployees());
                    break;
                case 6:
                    ShiftInteraction();
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void ProgressBar () {
        // Simulate a task and show the progress
        int totalTasks = 100;
        ProgressBar progressBar = new ProgressBar(totalTasks, 50);

        for (int i = 0; i < totalTasks; i++) {
            try {
                Thread.sleep(20); // Simulate work being done
            } catch (InterruptedException ignored) {

            }
            progressBar.update(1);
        }

        progressBar.finish();
    }

    private static void ShiftInteraction() throws IOException {

        while (true){

            System.out.println("You Entered the Shift Menu\nSelect the next Stage");
            System.out.println("1: Print the scheduled shift for the next week you entered\n");
            System.out.println("2: Add shifts");
            System.out.println("3: Delete Shifts");
            System.out.println("4: Finish(Save & Print Shifts)");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

            }
        }
    }
}

