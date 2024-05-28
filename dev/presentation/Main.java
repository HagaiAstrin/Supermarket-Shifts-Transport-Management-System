package presentation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        while (true) {

            System.out.println("Hello! Welcome to Super-Li Transportation system!\n");
            System.out.println("press '1' if you are the Manager\n");
            System.out.println("press '2' if you are a Driver\n");
            System.out.println("press '9' to exit\n");

            String type = reader.next();

            while (!type.equals("1") && !type.equals("2") && !type.equals("9")) {

                System.out.println("Wrong input! please try again..\n");
                System.out.println("press '1' if you are the Manager\n");
                System.out.println("press '2' if you are a Driver\n");
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
