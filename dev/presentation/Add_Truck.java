package presentation;

import domain.DataStructManager;
import domain.Truck;

import java.util.Scanner;

public class Add_Truck {

    public static void add_truck() {

//        need to do Jsons!!!

        Scanner reader = new Scanner(System.in);

        String answer = "yes";

        while (answer.equals("yes")){
            System.out.println("Enter licence_number of 8 digits:");
            String licence_number = reader.next();

            while (licence_number.length() != 8){
                System.out.println("Wrong input! The licence_number should hava a 8 digit. try again..");
                System.out.println("Enter licence_number of 8 digits:");
                licence_number = reader.next();
            }

            System.out.println("Enter licence level: 'A', 'B' OR 'C'");
            String licence_level = reader.next();

            System.out.println("Please enter net_weight:");
            double net_weight = reader.nextDouble();

            System.out.println("Please enter max_weight:");
            double max_weight = reader.nextDouble();

            char t = licence_level.charAt(0);
            int l = Integer.parseInt(licence_number);

            DataStructManager.add_Truck(new Truck(l, t, net_weight, max_weight));

            System.out.println("Would you like to add another truck? Enter 'yes' or 'no'");
            answer = reader.next();
        }

        reader.close();
    }
}
