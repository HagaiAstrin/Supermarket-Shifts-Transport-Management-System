package presentation;

import com.google.gson.JsonObject;
import controller.Driver_controller;


import java.util.Scanner;

public class Driver_x {

    public static void driver_x() {

        JsonObject new_Json = new JsonObject();

        System.out.println("\nEnter your name:");

        Scanner reader = new Scanner(System.in);
        String name = reader.next();

        System.out.println("Enter your password:");
        String password = reader.next();

        new_Json.addProperty("Name", name);
        new_Json.addProperty("Password", password);

        String driver_name = controller.Driver_controller.check_driver(new_Json);

        while (driver_name == null) {
            System.out.println("\nThe name or password are wrong, try again..\n");
            System.out.println("Enter name:");
            name = reader.next();

            System.out.println("Enter password:");
            password = reader.next();

            new_Json.addProperty("Name", name);
            new_Json.addProperty("Password", password);

            driver_name = controller.Driver_controller.check_driver(new_Json);
        }

        String a = "yes";
        while (a.equals("yes")) {
            System.out.println("\nHello " + driver_name + "! What do you want to do?\n");
            System.out.println("Press '1' to - Report on leaving");
            System.out.println("Press '2' to - Report on back");

            String answer = reader.next();

            while (!answer.equals("1") && !answer.equals("2")) {
                System.out.println("\nWrong input, try again..\n");
                System.out.println("Hello " + driver_name + "! What do you want to do?\n");
                System.out.println("Press '1' to - Report on leaving");
                System.out.println("Press '2' to - Report on back");

                answer = reader.next();

            }
            switch (answer) {
                case "1" -> leaving(new_Json);
                case "2" -> back(new_Json);
            }
            System.out.println("\nDo you want to do something else?");
            System.out.println("\npress 'yes' to continue in the system");
            a = reader.next();
        }
    }

    public static void leaving (JsonObject j){
        String a = Driver_controller.update_leaving(j);
        System.out.println(a);
    }

    public static void back (JsonObject j){
        String a = Driver_controller.update_back(j);
        System.out.println(a);
    }
}

