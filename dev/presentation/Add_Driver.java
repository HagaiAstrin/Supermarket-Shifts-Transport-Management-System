package presentation;

import domain.DataStructManager;
import domain.Driver;

import java.util.Scanner;

public class Add_Driver {

    public static void add_driver() {

//        need to do Jsons!!!

        Scanner reader = new Scanner(System.in);

        String answer = "yes";

        while (answer.equals("yes")){
            System.out.println("Enter the name of the driver:");
            String name = reader.next();

            System.out.println("Enter the license leve of the driver: 'A', 'B' OR 'C'");
            String license = reader.next();

            while (!license.equals("A") && !license.equals("B") && !license.equals("C")){
                System.out.println("Wrong input! try again..");
                System.out.println("Enter the license leve of the driver: 'A', 'B' OR 'C'");
                license = reader.next();
            }

            System.out.println("Enter password of 5 digit:");
            String password = reader.next();

            while (password.length() != 5){
                System.out.println("Wrong input! The password should hava a 5 digit. try again..");
                System.out.println("Enter password of 5 digit:");
                password = reader.next();
            }

            char l = license.charAt(0);

            DataStructManager.add_Driver(new Driver(name, l, password));

            System.out.println("Would you like to add another driver? Enter 'yes' or 'no'.");
            answer = reader.next();
        }

        reader.close();
    }
}
