package Presentation;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    /**
     * Start of the Program
     */
    public static void main(String[] args) throws InterruptedException, SQLException {

        Scanner reader = new Scanner(System.in);

        while (true) {

            StringBuilder menu = new StringBuilder();
//            menu.append("\n--------------------------------------------------------");
//            menu.append("\n| Hello and Welcome to Super - Li Transportation Menu |\n");
//            menu.append("--------------------------------------------------------\n");
//            menu.append("\n");

            menu.append("\n--------------------------------------------------------");
            menu.append("\n| Hello and Welcome to Segal Shinua Transportation Menu |\n");
            menu.append("--------------------------------------------------------\n");
            menu.append("\n");

            menu.append("press '1' if you are the Manager.\n");
            menu.append("press '2' if you are a Driver.\n");
            menu.append("press '3' if you want to load Data.\n");
            menu.append("press '9' to exit.\n");
            System.out.println(menu);

            String type = reader.nextLine();

            while (!type.equals("1") && !type.equals("2") && !type.equals("9") && !type.equals("3")) {

                System.out.println("\nWrong input! please try again..\n");
                System.out.println(menu);

                type = reader.nextLine();
            }
            switch (type) {
                case "1" -> TransportationManagerMenu.transportation_manager();
                case "2" -> DriverMenu.driver_x();
                case "3" -> {
                    CSV_Reader.reader("dev/CSV_Data/Drivers.csv", 1);
                    CSV_Reader.reader( "dev/CSV_Data/Sites.csv", 2);
                    CSV_Reader.reader( "dev/CSV_Data/Trucks.csv", 3);
                }
                case "9" -> {
                    return;
                }
            }
        }
    }
}
