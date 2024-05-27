package presentation;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        while (true){
            System.out.println("Hello! Welcome to Super-Li Transportation system!");
            System.out.println("If you want to exit from the system' press '9'.");

            String answer = reader.next();

            if (answer.equals("9"))
                return;

            System.out.println("press '1' if you are manager.\npress '2' if you are driver.");

            String type = reader.next();
            while (!type.equals("1") && !type.equals("2")){
                System.out.println("Wrong input! please try again..");
                System.out.println("""
                        Hello! Welcome to Super-Li Transportation system!
                        press 1 if you are manager
                        press 2 if you are driver.""");
                type = reader.next();
            }
            switch (type) {
                case "1" ->Transportation_manager.transportation_manager();
                case "2" -> Driver_x.driver_x();
            }
        }

    }
}
