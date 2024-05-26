package Presentation;

import Domain.Employee;
import Controller.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Domain.JobTypeEnum;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Menu {
    public static void AddEmployee() {
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

        Gson gson = new Gson();
        Employee e = new Employee(id, name, bankID, salary, restDays, startDate, jobType);

        AdminController.AddEmployee(gson.toJsonTree(e).getAsJsonObject());
    }

    public static void RemoveEmployee(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        // TODO: Check if id is valid to delete.
        AdminController ac = new AdminController();
        System.out.println(ac.RemoveEmployee(id));
    }

    public static void UpdateEmployeeDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();
        while(!AdminController.SearchEmployee(id)) {
            System.out.print("Employee ID not found.");
            System.out.print("Enter Employee ID: ");
            id = scanner.nextLine();
        }
        Employee employee = ConvertFronJsonToEmployee(AdminController.GetEmployee(id));
        while (true) {
            System.out.println("\nUpdate Employee Menu:");
            System.out.println("1. Update Name");
            System.out.println("2. Update Bank ID");
            System.out.println("3. Update Salary");
            System.out.println("4. Update Rest Days");
            System.out.println("5. Update Start Date");
            System.out.println("6. Update Job Type");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new Name: ");
                    String name = scanner.nextLine();
                    employee.setName(name);
                    System.out.println("Name updated.");
                    break;
                case 2:
                    System.out.print("Enter new Bank ID: ");
                    String bankID = scanner.nextLine();
                    employee.setBankID(bankID);
                    System.out.println("Bank ID updated.");
                    break;
                case 3:
                    System.out.print("Enter new Salary: ");
                    int salary = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    employee.setSalary(salary);
                    System.out.println("Salary updated.");
                    break;
                case 4:
                    System.out.print("Enter new Rest Days: ");
                    int restDays = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    employee.setRestDays(restDays);
                    System.out.println("Rest Days updated.");
                    break;
                case 5:
                    // TODO: Why would you change the start date of work???
//                    System.out.print("Enter new Start Date (yyyy-MM-dd): ");
//                    String startDateStr = scanner.nextLine();
//                    Date startDate = null;
//                    try {
//                        startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
//                        employee.setStartDate(startDate);
//                        System.out.println("Start Date updated.");
//                    } catch (ParseException e) {
//                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
//                    }
                    break;
                case 6:
                    System.out.print("Enter new Job Type (e.g., CASHIER, SHIFT_MANAGER, STOCK_KEEPER): ");
                    String jobType = scanner.nextLine();
                    employee.setJobType(JobTypeEnum.valueOf(jobType));
                    System.out.println("Job Type updated.");
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Employee ConvertFronJsonToEmployee(JsonObject json){
        Gson gson = new Gson();
        return gson.fromJson(json, Employee.class);
    }
}
