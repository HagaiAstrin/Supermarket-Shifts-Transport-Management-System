package presentation;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controller.Driver_controller;
import domain.DataStructManager;
import domain.Driver;

import java.util.Scanner;

public class Driver_x {

    public static void driver_x() {

        Driver driver;

        JsonObject new_Json = new JsonObject();

        System.out.println("Enter name:");

        Scanner reader = new Scanner(System.in);
        String name = reader.next();

        System.out.println("Enter password:");
        String password = reader.next();

        new_Json.addProperty("name", name);
        new_Json.addProperty("password", password);

        String driver_name = controller.Driver_controller.check_driver(new_Json);

        while (driver_name == null) {
            System.out.println("The name or password are wrong, try again..");
            System.out.println("Enter name:");
            name = reader.next();

            System.out.println("Enter password:");
            password = reader.next();

            new_Json.addProperty("name", name);
            new_Json.addProperty("password", password);

            driver_name = controller.Driver_controller.check_driver(new_Json);
        }

        System.out.println("Hello " + driver_name + "! What do you want to do?");
        System.out.println("""
                Report on leaving - '1'.
                Report on back - '2'.""");
        String answer = reader.next();

        while (!answer.equals("1") && !answer.equals("2")) {
            System.out.println("Wrong input, try again..");
            System.out.println("Hello " + driver_name + "! What do you want to do?");
            System.out.println("""
                Report on leaving - '1'.
                Report on back - '2'.""");

            answer = reader.next();

        }
        switch (answer) {
            case "1" -> leaving(new_Json);
            case "2" -> back(new_Json);
        }
    }

    public static void leaving (JsonObject j){
        System.out.println("Have a good trip!");
        Driver_controller.update_leaving(j);

    }

    public static void back (JsonObject j){
        System.out.println("Welcome back!");
        Driver_controller.update_back(j);
    }
}

