package presentation;

import java.util.Scanner;

public class Transportation_manager_Menu {
    public static void transportation_manager() {

        Scanner reader = new Scanner(System.in);

  //TODO adding the password to the manager

        System.out.println("Enter password:");
        String password = reader.nextLine();

        while (!password.equals("123456789")) {
            System.out.println("Wrong password, try again..\n");
            System.out.println("Enter password:");
            password = reader.nextLine();
        }

        while (true) {
            StringBuilder manager_menu = new StringBuilder();
            manager_menu.append("\nHello Transportation Manager! What do you want to do?\n");
            manager_menu.append("Choose from the options bellow:\n");
            manager_menu.append("'1' - Add Driver.\n");
            manager_menu.append("'2' - Add Truck.\n");
            manager_menu.append("'3' - Add Store.\n");
            manager_menu.append("'4' - Add Supplier.\n");
            manager_menu.append("'5' - Make Transportation.\n");
            manager_menu.append("'6' - Show all Transport.\n");
            manager_menu.append("'9' - Exit to the Main menu.");

            System.out.println(manager_menu);

            String answer = reader.nextLine();

            while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3") &&
                    !answer.equals("4") && !answer.equals("5") && !answer.equals("6") && !answer.equals("9")) {

                System.out.println("\nWrong input! please write your ans ware again..\n");
                System.out.println(manager_menu);

                answer = reader.nextLine();
            }

            if (answer.equals("9"))
                break;

            switch (answer) {
                case "1" -> add_driver();
                case "2" -> add_truck();
                case "3" -> add_store();
                case "4" -> add_supplier();
                case "5" -> Create_transportation();
                case "6" -> show_all_Transport();
            }
        }
    }

    /**
     * Adding a Driver from the user
     */
    public static void add_driver(){
        Add_Driver.add_driver();
    }
    /**
     * Adding a Truck from the user
     */
    public static void add_truck(){
        Add_Truck.add_truck();
    }
    /**
     * Adding a Store from the user
     */
    public static void add_store(){
        Add_Site.add_site("Store");
    }
    /**
     * Adding a Supplier from the user
     */
    public static void add_supplier(){
        Add_Site.add_site("Supplier");
    }
    /**
     * Making Transport
     */
    public static void Create_transportation(){
        Create_Transportation.create_Transport();
    }
    /**
     * Showing all Transport
     */
    public static void show_all_Transport(){
        Show_All_Transportations.show_all_Transportation();
    }
}

