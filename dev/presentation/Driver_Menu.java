package presentation;

import com.google.gson.JsonObject;
import controller.Driver_controller;


import java.util.Scanner;

public class Driver_Menu {

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

        String driver_name = controller.Driver_controller.check_driver(new_Json);

        while (driver_name == null) {
            System.out.println("\nThe name or password are wrong, try again..\n");
            System.out.println("Enter name:");
            name = reader.nextLine();

            System.out.println("\nEnter password:");
            password = reader.nextLine();

            new_Json.addProperty("Name", name);
            new_Json.addProperty("Password", password);

            driver_name = controller.Driver_controller.check_driver(new_Json);
        }
        StringBuilder d = (Driver_controller.Print_document(new_Json));


        String a = "yes";
        while (a.equals("yes")) {
            StringBuilder new_s = new StringBuilder();
            String s = "\nHello " + driver_name + "!\n";
            new_s.append(s);
            if (d != null) {
                new_s.append("\nYou got a Transportation list!\n");
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

            System.out.println("\nDo you want to do something else?");
            System.out.println("Press 'yes' to continue in the system");
            a = reader.nextLine();
        }
    }

    /**
     * Update leaving
     * @param j - JsonObject argument represent the Driver
     */
    public static void leaving (JsonObject j) throws InterruptedException {
        String a = Driver_controller.update_leaving(j);
        System.out.println(a);
        if(a.equals("Have a good trip!")) TruckAnimation.print_leave();
    }

    /**
     * Update come back
     * @param j - JsonObject argument represent the Driver
     */
    public static void back (JsonObject j){
        String a = Driver_controller.update_back(j);
        System.out.println(a);
    }
}

