package Presentation;

import com.google.gson.JsonObject;
import Domain.DriverController;


import java.util.Scanner;

public class DriverMenu {

    /**
     * Driver menu in the System
     */
    public static void driver_x() throws InterruptedException {

        JsonObject new_Json = new JsonObject();

        Scanner reader = new Scanner(System.in);

        System.out.println("\nEnter your name:");
        String name = reader.nextLine();

        System.out.println("\nEnter your password:");
        String password = reader.nextLine();

        new_Json.addProperty("Name", name);
        new_Json.addProperty("Password", password);

        String driver_name = DriverController.check_driver_logIn(new_Json);

        while (driver_name == null) {
            System.out.println("\nThe name or password are wrong, try again..\n");
            System.out.println("Enter name:");
            name = reader.nextLine();

            System.out.println("\nEnter password:");
            password = reader.nextLine();

            new_Json.addProperty("Name", name);
            new_Json.addProperty("Password", password);

            driver_name = DriverController.check_driver_logIn(new_Json);
        }
        String d = DriverController.Print_document(new_Json);


        String a = "yes";
        while (a.equals("yes")) {
            StringBuilder new_s = new StringBuilder();
            new_s.append("\n-----------------------------------------------------");
            new_s.append("\n|                  Driver Menu                    |\n");
            new_s.append("-----------------------------------------------------\n");
            String s = "\nHello " + driver_name + "!\n";
            new_s.append(s);
            if (d != null) {
                new_s.append(d);
            }
            else{
                new_s.append("""

                        You don't have a Transportation list.
                        Please come back after the transport manager has assigned you to the ride.\n""");
            }
            new_s.append("\nWhat do you want to do?\n");
            new_s.append("Press '1' to - Report on leaving\n");
            new_s.append("Press '2' to - Report on back\n");
            new_s.append("Press '9' to - Back to menu");

            System.out.println(new_s);
            String answer = reader.nextLine();

            while (!answer.equals("1") && !answer.equals("2") && !answer.equals("9") ) {
                System.out.println("\nWrong input, try again..\n");
                System.out.println(new_s);

                answer = reader.nextLine();

            }
            if (answer.equals("9"))
                break;

            switch (answer) {
                case "1" -> leaving(new_Json);
                case "2" -> back(new_Json);
            }

            System.out.println("\n\nDo you want to do something else?");
            System.out.println("Press 'yes' to continue in the system");
            a = reader.nextLine();
        }
    }

    /**
     * Update leaving
     * @param j - JsonObject argument represent the Driver
     */
    public static void leaving (JsonObject j) throws InterruptedException {
        String a = DriverController.update_leaving(j);
        System.out.println(a);
    }

    /**
     * Update come back
     * @param j - JsonObject argument represent the Driver
     */
    public static void back (JsonObject j){
        String a = DriverController.update_back(j);
        System.out.println(a);
    }
}

