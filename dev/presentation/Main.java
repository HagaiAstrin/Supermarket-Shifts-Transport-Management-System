package presentation;

import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        CSV_reader.reader("dev/data/Drivers.csv", 1);
        CSV_reader.reader( "dev/data/Sites.csv", 2);
        CSV_reader.reader( "dev/data/Trucks.csv", 3);

        while (true) {

            System.out.println("\nHello! Welcome to Super-Li Transportation system!\n");
            System.out.println("press '1' if you are the Manager.");
            System.out.println("press '2' if you are a Driver.");
            System.out.println("press '9' to exit.\n");

            String type = reader.next();

            while (!type.equals("1") && !type.equals("2") && !type.equals("9")) {

                System.out.println("\nWrong input! please try again..\n");
                System.out.println("press '1' if you are the Manager.");
                System.out.println("press '2' if you are a Driver.");
                System.out.println("press '9' to exit\n");

                type = reader.next();
            }
            switch (type) {
                case "1" -> Transportation_manager.transportation_manager();
                case "2" -> Driver_x.driver_x();
                case "9" -> {
                    return;
                }
            }
        }
    }
}
