package presentation;

import domain.DataStructManager;
import domain.Driver;

import java.util.Scanner;

public class driver {

    public static void driver_x() {

        Driver driver;

        System.out.println("Enter name:");

        Scanner reader = new Scanner(System.in);
        String name = reader.next();

        System.out.println("Enter password:");
        String password = reader.next();

        driver = checking(name, password);

        while (driver == null) {
            System.out.println("The name or password are wrong, try again..");
            System.out.println("Enter name:");
            name = reader.next();

            System.out.println("Enter password:");
            password = reader.next();
            driver = checking(name, password);
        }

        System.out.println("Hello " + driver.getName() + "! What do you want to do?");
        System.out.println("""
                Report on leaving - '1'.
                Report on back - '2'.""");
        String answer = reader.next();

        while (!answer.equals("1") && !answer.equals("2")) {
            System.out.println("Wrong input, try again..");
            System.out.println("Hello " + driver.getName() + "! What do you want to do?");
            System.out.println("""
                Report on leaving - '1'.
                Report on back - '2'.""");

            answer = reader.next();

        }
        switch (answer) {
            case "1" -> leaving();
            case "2" -> back(driver);
        }
    }

    public static Driver checking (String n, String p) {

        for (Driver driver : DataStructManager.drivers) {
            if (n.equals(driver.getName()) && p.equals(driver.getPassword())) {
                return driver;
            }
        }
        return null;
    }
    public static void leaving (){
        System.out.println("Have a good trip!");
//        d.setAvailability(false);
//        d.getUsing_truck().setAvailability(false);
    }

    public static void back (Driver d){
        System.out.println("Welcome back!");
        d.setAvailability(true);
        d.getUsing_truck().setAvailability(true);

    }
}

