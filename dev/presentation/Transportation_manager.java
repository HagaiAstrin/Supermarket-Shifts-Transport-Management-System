package presentation;

import java.util.Scanner;

public class Transportation_manager {
    public static void transportation_manager() {

        Scanner reader = new Scanner(System.in);

  //TODO adding the password to the manager

//        System.out.println("Enter password:");
//        String password = reader.next();
//
//        while (!password.equals("123456789")) {
//            System.out.println("Wrong password, try again..\n");
//            System.out.println("Enter password:");
//            password = reader.next();
//        }


        while (true) {
            StringBuilder manager_menu = new StringBuilder();
            manager_menu.append("Hello Transportation Manager! What do you want to do?\n");
            manager_menu.append("Choose from the options bellow:\n");
            manager_menu.append("'1' - Add Driver.\n");
            manager_menu.append("'2' - Add Truck.\n");
            manager_menu.append("'3' - Add Store.\n");
            manager_menu.append("'4' - Add Supplier.\n");
            manager_menu.append("'5' - Make Transportation.\n");
            manager_menu.append("'6' - Show all Transport.\n");
            manager_menu.append("'9' - Exit to the Main menu.\n");

            System.out.println(manager_menu);

            String answer = reader.next();

            while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3") &&
                    !answer.equals("4") && !answer.equals("5") && !answer.equals("6") && !answer.equals("9")) {

                System.out.println("\nWrong input! please write your ans ware again..\n");
                System.out.println(manager_menu);

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
                case "6" -> show_all_Transport();
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
    public static void show_all_Transport(){
        Transport_Show.show_all_Transportation();
    }
}

