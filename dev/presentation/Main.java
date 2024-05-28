package presentation;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        while (true) {

            System.out.println("Hello! Welcome to Super-Li Transportation system!");
            System.out.println("press '1' if you are manager.\npress '2' if you are driver.\npress '9' to exit.");

            String type = reader.next();

            if (type.equals("9"))
                return;

            while (!type.equals("1") && !type.equals("2")) {
                System.out.println("Wrong input! please try again..");
                System.out.println("""
                        Hello! Welcome to Super-Li Transportation system!
                        press 1 if you are manager
                        press 2 if you are driver.""");
                type = reader.next();
            }
            switch (type) {
                case "1" -> Transportation_manager.transportation_manager();
                case "2" -> Driver_x.driver_x();
            }
        }
    }
}
