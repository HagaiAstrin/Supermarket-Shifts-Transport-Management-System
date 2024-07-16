package Presentation.PresentationEmployee;

import Domain.DomainEmployee.Controller.AdminController;
import Domain.DomainEmployee.Controller.DataController;
import Domain.DomainEmployee.Controller.SystemController;
import Domain.DomainEmployee.IO_Data;
import com.google.gson.JsonObject;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public static Scanner scanner = new Scanner(System.in); // Use a single scanner instance
    static int employeeInShift = 1; // Default


    /**
     * First menu the admin sees.
     * He has to import the data.
     */
    public static void FirstMenu() throws InterruptedException, IOException {
        employeeInShift = Config();
        System.out.println("---------------------------------------------");
        System.out.println("|                 Admin Menu                |");
        System.out.println("---------------------------------------------");
        while (true) {
            System.out.println("Please choose an option:\n");
            System.out.println("\t1. Load employees data");
            System.out.println("\t2. Logout");
            System.out.print("Enter your choice: ");
            String choice;
            while (true) {
                choice = scanner.nextLine();
                if (choice.equals("1") || choice.equals("2")){
                    break;
                }
                System.out.println("Wrong input, please try again: ");
            }
            int choice_int = Integer.parseInt(choice);
            switch (choice_int) {
                case 1:
//                    ProgressBar();

                    System.out.println("Data was loaded successfully.");
                    Thread.sleep(500);
                    AdminController.ImportEmployees();
                    // Going to the shift menu.
                    List<List<List<String>>> lst = DataController.getTableValuesAsArray();
                    AdminController.setShiftedEmployees(lst);
                    Menu();
                    return;
                case 2:
                    SystemController.Logout();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }


    }

    public static int Config() {
        System.out.println("Hello Manager,\nCould you please specify the number of employees needed for each role in a shift?");
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                return choice;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();  // Clear the invalid input
            }
        }
    }

    public static void Menu () throws IOException, InterruptedException {
        System.out.println("---------------------------------------------");
        System.out.println("|                 Admin Menu                |");
        System.out.println("---------------------------------------------");
        while (true) {
            System.out.println("Please choose an option:\n");
            System.out.println("\t1. Add new employee");
            System.out.println("\t2. Remove employee");
            System.out.println("\t3. Update employee details");
            System.out.println("\t4. Manage Shifts");
            System.out.println("\t5. Print all employees");
            System.out.println("\t6. Logout");
            System.out.print("Enter your choice: ");

            try {
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
                        ShiftInteraction();
                        break;
                    case 5:
                        Printer.PrintAllEmployees(AdminController.PrintEmployees());
                        break;
                    case 6:
                        SystemController.Logout();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            catch (Exception e){
                System.out.println("Invalid choice. Please try again.");
                scanner.nextLine();
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
    //----------------------------------------------------------Asaf
    private static void ShiftInteraction() throws IOException {

        while (true){

            System.out.println("\nYou Entered the Shift Menu\nSelect the next Stage");
            System.out.println("\t1: Print scheduled week for all Job types");
            System.out.println("\t2: Print scheduled week for specific job type");
            System.out.println("\t3: Add shifts");
            System.out.println("\t4: Delete Shifts");
            System.out.println("\t5: Restart Week");
            System.out.println("\t6: Go Back");
            System.out.println("\t7: Finish(Save & Print Shifts)\n");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        // Print
                        System.out.println(AdminController.printAllTypeWeek());
                        IO_Data.GetDriversInShift();
                        break;
                    case 2:
                        int job = IOJobShiftMenu();
                        String jobWeek = AdminController.printTypeWeek(job);
                        System.out.println(jobWeek);
                        break;
                    case 3:
                        AddShiftMenu();
                        break;
                    case 4:
                        DeleteShiftMenu();
                        break;
                    case 5:
                        AdminController.RestartWeek();
                        break;
                    case 6:
                        return;
                    case 7:
                        try {
                            AdminController.checkEmployeeWeekFull(employeeInShift);
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                            System.out.println("Finish and try again later");
                            break;
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
            catch (Exception e){
                System.out.println("Invalid choice. Please try again.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Menu for adding a shift that contains employees.
     */
    private static void AddShiftMenu() throws IOException {

        //Getting User Input
        List<Integer> iOShiftMenu = iOShiftMenu(); // Getting the day and the shift
        int job = IOJobShiftMenu(); // Getting what kind of Job

        int day = iOShiftMenu.get(0);
        int shift = iOShiftMenu.get(1);

        List<JsonObject> ja = AdminController.getEmployeeToShift(job, day, shift);
        Printer.PrintAllEmployees(ja);

        System.out.println("Could you please provide the ID of the employee you would like to assign to this shift?\n");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            AdminController.SetShift(job, day, shift, id);
            System.out.println(id + " was successfully added");
        }
        catch (Exception e) {
            System.out.println("Could not add employee to shift.");
        }
    }

    /**
     * Menu for deleting a shift that contains employees.
     */
    private static void DeleteShiftMenu() throws IOException{
        System.out.println("Enter ID you want to remove from shift");
        int id = scanner.nextInt();
        scanner.nextLine();

        List<Integer> dayAndShift = iOShiftMenu();

        int day = dayAndShift.get(0);
        int shift = dayAndShift.get(1);
        int job = IOJobShiftMenu();

        try {
            String processOutput = AdminController.removeShift(id,day, shift, job);
            System.out.println(processOutput);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


    public static Integer IOJobShiftMenu() throws IOException{
        while (true){
            try {
                System.out.println("\nSelect the job you want to add a shift for");
                System.out.println("\t0: STOCK_KEEPER");
                System.out.println("\t1: SHIFT_MANAGER");
                System.out.println("\t2: CASHIER");
                System.out.println("\t3: Go back");

                int job = scanner.nextInt();
                scanner.nextLine();
                return job;
            }
            catch (Exception e){
                System.out.println("Invalid choice. Please try again.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Determines which employee to add to which shift.
     */
    public static List<Integer> iOShiftMenu() throws IOException{
        List <Integer> userInput = new ArrayList<>();
        while (true){
            try {
                System.out.println("What day you want to be added");
                System.out.println("0: Sunday");
                System.out.println("1: Monday");
                System.out.println("2: Tuesday");
                System.out.println("3: Wednesday");
                System.out.println("4: Thursday");

                int day = scanner.nextInt();
                scanner.nextLine();
                userInput.add(day);
                break;
            }
            catch (Exception e){
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
        while (true){
            try{
                System.out.println("What Shift you want to add?");
                System.out.println("0: Morning");
                System.out.println("1: Evening");

                int shift = scanner.nextInt();
                scanner.nextLine();

                userInput.add(shift);
                break;
            }
            catch (Exception e){
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
        return userInput;
    }
}

