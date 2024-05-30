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

    private void login() throws IOException {
        // Admin or User
        String UserKind;
        System.out.print("Hey there, if you are admin press 1, else press 2: ");

        while (true) {
            String WhatKindOfUserLogin = scanner.nextLine();
            if (WhatKindOfUserLogin.equals("1") || WhatKindOfUserLogin.equals("2")) {
                UserKind = WhatKindOfUserLogin;
                break;
            }
            // If the user didn't press 1 or 2
            System.out.print("Try selecting again: ");
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
                    new Menu().ShiftMenu();
                    AdminController.Shifts();
                default:
                    System.out.println("Invalid choice. Please try again.");
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

