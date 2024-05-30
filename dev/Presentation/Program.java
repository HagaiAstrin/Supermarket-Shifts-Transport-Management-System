package Presentation;
import Domain.Employee;
import Domain.IO_Data;
import com.google.gson.Gson;
import Controller.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Program {

    public Program(){

    }

    private Scanner scanner = new Scanner(System.in); // Use a single scanner instance

    public static void main(String[] args) throws IOException {
        Program program = new Program();
        program.Menu();
    }

    public void Menu() throws IOException {
        login();
    }

    private static void logo(){
        System.out.println("     _______. __    __  .______    _______ .______          __       _______  _______ ");
        System.out.println("    /       ||  |  |  | |   _  \\  |   ____||   _  \\        |  |     |   ____||   ____|");
        System.out.println("   |   (----`|  |  |  | |  |_)  | |  |__   |  |_)  |       |  |     |  |__   |  |__   ");
        System.out.println("    \\   \\    |  |  |  | |   ___/  |   __|  |      /        |  |     |   __|  |   __|  ");
        System.out.println(".----)   |   |  `--'  | |  |      |  |____ |  |\\  \\----.   |  `----.|  |____ |  |____ ");
        System.out.println("|_______/     \\______/  | _|      |_______|| _| `._____|   |_______||_______||_______|");
        System.out.println("                                                                                      ");
        System.out.println("--------------------------------------------------------------------------------------");
    }

    private void login() throws IOException {
        logo();


        // Admin or User
        String UserKind;
        System.out.println("Welcome to the \"Super-Lee\" system.");
        System.out.println("Please identify yourself.");
        System.out.println("Press 1 for Admin login, or 2 for User login: ");

        while (true) {
            String WhatKindOfUserLogin = scanner.nextLine();
            if (WhatKindOfUserLogin.equals("1") || WhatKindOfUserLogin.equals("2")) {
                UserKind = WhatKindOfUserLogin;
                break;
            }
            // If the user didn't press 1 or 2
            System.out.println("Wrong input, please try again: ");
        }

        String[] UsernamePassword = UserPassInput();
        while (true) {
            if (UserKind.equals("1") && admin_checker(UsernamePassword)) {
                AdminMenu();
                break;
            } else if (UserKind.equals("2") && user_checker(UsernamePassword)) {
                IO_Data.SetEmployeeID(UsernamePassword[2]);
                UserMenu();
                break;
            } else {
                System.out.println("Invalid username or password. Try again.");
                UsernamePassword = UserPassInput();
            }
        }
    }

    private boolean admin_checker(String[] UserPassInput) {
        return new SystemController().LoginInputValidatorAdmin(UserPassInput);
    }

    private boolean user_checker(String[] UserPassInput) {
        return new SystemController().LoginInputValidatorUser(UserPassInput);
    }

    private String[] UserPassInput() {
        System.out.print("Please enter your username: ");
        String userName = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Please enter your ID: ");
        String id = scanner.nextLine();
        return new String[]{userName, password, id};
    }

    private void AdminMenu() throws IOException {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Load employees data");
            System.out.println("2. Add new employee");
            System.out.println("3. Remove employee");
            System.out.println("4. Update employee details");
            System.out.println("5. Logout");
            System.out.println("6. Statistics");
            System.out.println("7. Manage Shifts");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            // TODO: Create a class for admin menu and user menu.
            switch (choice) {
                case 1:
                    // Call method to view all employee
                    AdminController.ImportEmployees();
                    break;
                case 2:
                    // Call method to add new employee
                    Menu.AddEmployee();
                    break;
                case 3:
                    // Call method to remove employee
                    Menu.RemoveEmployee();
                    break;
                case 4:
                    // Call method to update employee details
                    Menu.UpdateEmployeeDetails();
                    break;
                case 5:
                    // TODO: Implement LOGOUT
                    SystemController.Logout();
                    return;
                case 6:
                    Printer.PrintAllEmployees(AdminController.PrintEmployees());
                    break;
                case 7:
                    ShiftInteraction();
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void ShiftInteraction() throws IOException {

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

    //TODO: Get ID from successful user login, insert it into the employee controller.
    private void UserMenu(){
        AdminController.ImportEmployees();
        while (true) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1. View personal details");
            System.out.println("2. Update preferences"); //TODO
            System.out.println("3. I dont know");
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
                    // Call method to view salary
                    //viewSalary();
                    break;
                case 3:
                    // Call method to view remaining rest days
                    //viewRemainingRestDays();
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

    private void Config(){
        System.out.println("Config");
    }
}

