package presentation;

import java.util.Scanner;

public class Main {

    /**
     * Start of the Program
     */
    public static void main(String[] args) throws InterruptedException {

        Scanner reader = new Scanner(System.in);

        while (true) {

            StringBuilder menu = new StringBuilder();
            menu.append("\n--------------------------------------------------------");
            menu.append("\n| Hello and Welcome to Super - Li Transportation Menu |\n");
            menu.append("--------------------------------------------------------\n");
            menu.append("\n");
            menu.append("press '1' if you are the Manager.\n");
            menu.append("press '2' if you are a Driver.\n");
            menu.append("press '3' if you want to load Data.\n");
            menu.append("press '9' to exit.");
            System.out.println(menu);

            String type = reader.nextLine();

            while (!type.equals("1") && !type.equals("2") && !type.equals("9") && !type.equals("3")) {

                System.out.println("\nWrong input! please try again..");
                System.out.println(menu);

                type = reader.nextLine();
            }
            switch (type) {
                case "1" -> Transportation_manager_Menu.transportation_manager();
                case "2" -> Driver_Menu.driver_x();
                case "3" -> {
                    CSV_reader.reader("dev/data/Drivers.csv", 1);
                    CSV_reader.reader( "dev/data/Sites.csv", 2);
                    CSV_reader.reader( "dev/data/Trucks.csv", 3);
                }
                case "9" -> {
                    return;
                }
            }
        }
    }
}
