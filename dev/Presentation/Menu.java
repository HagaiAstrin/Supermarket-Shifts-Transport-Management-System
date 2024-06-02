package Presentation;

import Domain.Employee;
import Controller.*;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import Domain.JobTypeEnum;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Menu {
    public static Scanner scanner;

    /**
     * Adding employee to the database.
     */
    public static void AddEmployee() throws IOException {
        JsonObject json = new JsonObject();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();
        json.addProperty("id", id);

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        json.addProperty("name", name);

        System.out.print("Enter Bank ID: ");
        String bankID = scanner.nextLine();
        json.addProperty("bankID", bankID);

        System.out.print("Enter Salary: ");
        int salary = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        json.addProperty("salary", salary);

        System.out.print("Enter Rest Days: ");
        int restDays = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        json.addProperty("restDays", restDays);

        scanner = new Scanner(System.in);
        System.out.println("Enter a date (dd/MM/yyyy): ");
        String dateString = scanner.nextLine(); // Read the input from the user
        json.addProperty("date", dateString);

        String jobType = GetJobType();


        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate date = null;
        try {
            // Parse the string to LocalDate
            date = LocalDate.parse(dateString, formatter);

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
            return;
        }

        JsonObject e_Json = Sender.EmployeeToJson(id, name, bankID, salary, restDays, date, jobType);
        try {
            AdminController.AddEmployee(e_Json);
            System.out.println("Employee added successfully.");
        } catch (IOException ex) {
            System.err.println("Failed to add employee: " + ex.getMessage());
        }
    }

    private static String GetJobType(){
        Scanner scanner = new Scanner(System.in);
        JobTypeEnum[] enumArray = JobTypeEnum.values();
        int size = enumArray.length;

        while(true) {
            System.out.println("Choose Job Type by number: ");
            for (int i = 0; i < enumArray.length; i++) {
                System.out.println((i + 1) + ". " + enumArray[i]);
            }
            System.out.println("Press " + (size+1) + " to exit.");
            int jobType = Integer.parseInt(scanner.nextLine());
            if(jobType == size+1) {return null;}
            if (jobType < 0 || jobType > enumArray.length) {
                System.out.println("Invalid job type.");
                continue;
            }
            return enumArray[jobType-1].toString();
        }
    }

    /**
     * Removing Employee from the database.
     */
    public static void RemoveEmployee(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();

        // TODO: Check if id is valid to delete.
        System.out.println(AdminController.RemoveEmployee(id));
    }

    public static void UpdateEmployeeDetails() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        String id = scanner.nextLine();
        while(!AdminController.SearchEmployee(id)) {
            System.out.print("Employee ID not found.");
            System.out.print("Enter Employee ID: ");
            id = scanner.nextLine();
        }
        JsonObject employee = AdminController.GetEmployee(id);
        while (true) {
            System.out.println("\nUpdate Employee Menu:");
            System.out.println("1. Update Name");
            System.out.println("2. Update Bank ID");
            System.out.println("3. Update Salary");
            System.out.println("4. Update Rest Days");
            System.out.println("5. Update Job Type");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new Name: ");
                    String name = scanner.nextLine();
                    employee.remove("name");
                    employee.addProperty("name", name);
                    System.out.println("Name updated.");
                    break;
                case 2:
                    System.out.print("Enter new Bank ID: ");
                    String bankID = scanner.nextLine();
                    employee.remove("bankID");
                    employee.addProperty("bankID", bankID);
                    System.out.println("Bank ID updated.");
                    break;
                case 3:
                    System.out.print("Enter new Salary: ");
                    int salary = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    employee.remove("salary");
                    employee.addProperty("salary", salary);
                    System.out.println("Salary updated.");
                    break;
                case 4:
                    System.out.print("Enter new Rest Days: ");
                    int restDays = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    employee.remove("restDays");
                    employee.addProperty("restDays", restDays);
                    System.out.println("Rest Days updated.");
                    break;
                case 5:
//                    System.out.print("Enter new Job Type (e.g., CASHIER, SHIFT_MANAGER, STOCK_KEEPER): ");
                    String jobTypeString = employee.get("jobType").getAsString();
                    String[] jobTypeArray = jobTypeString.split("/");

                    String jobType;
                    while(true) {
                        jobType = GetJobType();
                        if (jobType != null) {
                            for (int i = 0; i < jobTypeArray.length; i++) {
                                if (jobType.equals(jobTypeArray[i])) {
                                    jobType = null;
                                    break;
                                }
                            }
                            for (int j = 0; j < jobTypeArray.length; j++) {
                                jobType += "/" + jobTypeArray[j];
                            }
                            employee.addProperty("jobType", jobType);
                            System.out.println("Job Type updated.");
                        }
                        else {break;}
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.       ");
            }

            AdminController.RemoveEmployee(id);
            AdminController.AddEmployee(employee);
            //AdminController.UpdateEmployee(SystemController.ConvertEmployeeToJson(employee));
        }
    }

    private static Employee ConvertFronJsonToEmployee(JsonObject json){
        return SystemController.ConvertFronJsonToEmployee(json);
    }

    public static void PrintEmpPref(){

    }
}
