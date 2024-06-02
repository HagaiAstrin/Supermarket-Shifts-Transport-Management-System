package Presentation;

import Controller.*;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    private final Scanner scanner = new Scanner(System.in); // Use a single scanner instance
    boolean firstTime = true;

    public Program(){
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Program program = new Program();
        program.Menu();
    }

    public void Menu() throws IOException, InterruptedException {
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

    private void login() throws IOException, InterruptedException {
        logo();


        // Admin or User
        String UserKind;
        System.out.println("Welcome to the \"Super-Lee\" system.");
        System.out.println("Please identify yourself.");
        System.out.print("Press 1 for Admin login, or 2 for User login: ");

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
                if(firstTime){
                    firstTime = false;
                    AdminMenu.FirstMenu();
                }
                else{ AdminMenu.Menu(); }
                break;
            } else if (UserKind.equals("2") && user_checker(UsernamePassword)) {
                SystemController.setEmployeeIDIOData(UsernamePassword[2]);
                if(firstTime){
                    firstTime = false;
                    AdminController.ImportEmployees();
                    UserMenu.Menu();
                }
                else{ AdminMenu.Menu(); }
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
}

