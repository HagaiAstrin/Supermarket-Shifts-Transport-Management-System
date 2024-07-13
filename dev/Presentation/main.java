package Presentation;
import Presentation.PresentationEmployee.Program;
import Presentation.PresentationTransport.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Program program = new Program();
        while (true) {
            displayMenu(program);
        }

    }

    private static void displayMenu(Program program) throws Exception {
        program.logo();
        System.out.println("Welcome to the \"Super-Lee\" system.");
        System.out.println("Choose an option:");
        System.out.println("1. Employee Management");
        System.out.println("2. Transport Management");
        int choice = getUserChoice();
        switch (choice) {
            case 1:
                //HR module
                program.Menu();
                break;
            case 2:
                Main_Transport.TransportMenu();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static int getUserChoice() {
        int choice = -1;
        while (choice < 1 || choice > 2) {
            System.out.print("Enter your choice (1 or 2): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 2) {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return choice;
    }
}
