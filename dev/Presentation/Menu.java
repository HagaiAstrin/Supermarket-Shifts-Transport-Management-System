package Presentation;

import Domain.Employee;
import Controller.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu {
    public static Employee AddEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Bank ID: ");
        String bankID = scanner.nextLine();

        System.out.print("Enter Salary: ");
        int salary = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter Rest Days: ");
        int restDays = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter Start Date (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }

        System.out.print("Enter Job Type (e.g., CASHIER, SHIFT_MANAGER, STOCK_KEEPER): ");
        String jobType = scanner.nextLine();

        return new Employee(id, name, bankID, salary, restDays, startDate, jobType);
    }

    public static void RemoveEmployee(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        // TODO: Check if id is valid to delete.
        AdminController ac = new AdminController();
        System.out.println(ac.RemoveEmployee(id));
    }
}
