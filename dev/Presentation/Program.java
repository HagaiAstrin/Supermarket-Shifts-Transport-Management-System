package Presentation;
import Domain.Employee;
import com.google.gson.Gson;
import Controller.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.Scanner;

public class Program {
    private AdminController ac;

    private Scanner scanner = new Scanner(System.in); // Use a single scanner instance

    public static void main(String[] args) {
        Program program = new Program();
        program.Menu();

    }

    public void Menu() {
        login();
        ac = new AdminController();
        System.out.println(ac.PrintEmployees());

        Employee e1 = new Employee("100", "Hagai", "22", 150000, 0, new Date("12/02/2022"), "Cashier");
        Gson g = new Gson();
        JsonElement je = g.toJsonTree(e1);
        ac.AddEmployee(je.getAsJsonObject());
    }

    private void login() {
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
                // Admin logic here
                break;
            } else if (UserKind.equals("2") && user_checker(UsernamePassword)) {
                // User logic here
                break;
            } else {
                System.out.println("Invalid username or password. Try again.");
                UsernamePassword = UserPassInput();
            }
        }
    }

    private boolean admin_checker(String[] UserPassInput) {
        // Go to Controller
        return true;
    }

    private boolean user_checker(String[] UserPassInput) {
        // Go to Controller
        return true;
    }

    private String[] UserPassInput() {
        System.out.print("Please enter your username: ");
        String userName = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();
        return new String[]{userName, password};
    }

    private void Logout(){
        System.out.println("Goodbye!");
        System.exit(0);
    }

    private void AdminMenu(){
        System.out.println("Admin Menu");
    }

    private void UserMenu(){
        System.out.println("User Menu");
    }

    private void Config(){
        System.out.println("Config");
    }
}

