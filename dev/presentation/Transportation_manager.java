package presentation;

import java.util.Scanner;

public class Transportation_manager {
    public static void transportation_manager() {

        Scanner reader = new Scanner(System.in);

        System.out.println("Enter password:");
        String password = reader.next();

        while (!password.equals("123456789")) {
            System.out.println("Wrong password, try again..\n");
            System.out.println("Enter password:");
            password = reader.next();
        }

        while (true) {

            System.out.println("\nHello Transportation Manager! What do you want to do?\n");
            System.out.println("Choose from the options bellow:\n");
            System.out.println("'1' - Add Driver");
            System.out.println("'2' - Add Truck.");
            System.out.println("'3' - Add Store.");
            System.out.println("'4' - Add Supplier");
            System.out.println("'5' - Make Transportation.");
            System.out.println("'9' - To Exit.\n");

            String answer = reader.next();

            while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3") && !answer.equals("4")
                                                              && !answer.equals("5") && !answer.equals("9")) {
                System.out.println("Wrong input! please write your ans ware again..\n");
                System.out.println("Choose from the options bellow:\n");
                System.out.println("'1' - Add driver");
                System.out.println("'2' - Add store.");
                System.out.println("'3' - Add supplier.");
                System.out.println("'4' - Add driver");
                System.out.println("'5' - Make transportation.");
                System.out.println("'9' - To Exit.\n");

                answer = reader.next();
            }

            if (answer.equals("9"))
                break;

            switch (answer) {
                case "1" -> add_driver();
                case "2" -> add_truck();
                case "3" -> add_store();
                case "4" -> add_supplier();
                case "5" -> make_transportation();
            }
        }
    }

    public static void add_driver(){
        Add_Driver.add_driver();
    }
    public static void add_truck(){
        Add_Truck.add_truck();
    }
    public static void add_store(){
        Add_Site.add_site("Store");
    }
    public static void add_supplier(){
        Add_Site.add_site("Supplier");
    }
    public static void make_transportation(){
        Create_Transportation.create_Transport();
    }
}

