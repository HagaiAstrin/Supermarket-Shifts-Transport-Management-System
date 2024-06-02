package Presentation;
import Domain.IO_Data;
import Controller.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Program {

    public Program(){

    }

    public static Scanner scanner = new Scanner(System.in); // Use a single scanner instance

    boolean firstTime = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        Program program = new Program();
        program.Menu();
    }

    public static void SelectBranch(){
        List<String> branches = IO_Data.listFoldersInDirectory();
        if(branches == null) {
            System.out.println("Can't find branches");
            return;
        }
        int branch_choice;
        while(true){
            System.out.println("Please select branch:");
            for(int i = 0; i < branches.size(); i++){
                System.out.println(i + ". " +branches.get(i));
            }
            System.out.print("Enter your choice: ");
            branch_choice = scanner.nextInt();
            scanner.nextLine();
            if(branch_choice < 0 || branch_choice > branches.size()){
                System.out.println("Invalid choice. Please try again.");
            }
            else{
                break;
            }
        }
        //TODO: Move through controller
        IO_Data.SetBranchName(branches.get(branch_choice));
    }

    public void Menu() throws IOException, InterruptedException {
        SelectBranch();
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

    public void login() throws IOException, InterruptedException {
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
                IO_Data.SetEmployeeID(UsernamePassword[2]);
                if(firstTime){
                    firstTime = false;
                    AdminController.ImportEmployees();
                    UserMenu.Menu();
                }
                else{ AdminMenu.Menu(); }
                break;
            } else {
                System.out.println("Wrong input, please try again: ");
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

