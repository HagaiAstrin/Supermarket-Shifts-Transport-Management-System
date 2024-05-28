package presentation;

import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;
import domain.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Create_Transportation {
    public static Transport new_t;
    public static void create_Transport() {

        JsonObject new_json = new JsonObject();

        Scanner reader = new Scanner(System.in);

        String answer = "yes";

        while (answer.equals("yes")){


            System.out.println("Please enter the date of the transportation:");
            String date = reader.next();

            System.out.println("Please enter the leaving time of the transportation:");
            String l_time = reader.next();

            System.out.println("Please enter the source place of the transportation:");
            String source = reader.next();

            String truck = choose_truck();
            String driver = choose_driver();

            if (truck == null || driver == null) {
                System.out.println("Sorry but we can't arrange the transport, " +
                        "because there are no truck or driver that available");
                return;
            }

            new_json.addProperty("date", date);
            new_json.addProperty("leaving time", l_time);
            new_json.addProperty("truck", truck);
            new_json.addProperty("driver", driver);
            new_json.addProperty("source", source);

            Transportation_manager_controller.create_transport(new_json);

            String area = choose_area();

            System.out.println("Please choose supplier or store:\n" +
                    "Supplier - '1'.\nStore - '2'.");
            String site = reader.next();
            while (!site.equals("1") && !site.equals("2")) {
                System.out.println("Wrong input!, try again..");
                System.out.println("Please choose supplier or store:\n" +
                        "Supplier - '1'.\nStore - '2'.");
                site = reader.next();
            }
            switch (site) {
                case "1" -> choose_supplier(area);
                case "2" -> choose_store(area);
            }
            System.out.println("Would you like to add a site for the transportation? ");
            System.out.println("Enter 'yes' or 'no': ");
            answer = reader.next();
        }
    }

    public static String choose_truck(){

        Scanner reader = new Scanner(System.in);
        System.out.println("Please choose an Truck: ");

        JsonObject new_trucks = Transportation_manager_controller.choose_truck();

        String answer = print_to_user(new_trucks.size(), new_trucks);

        if (new_trucks.size() == 0)
            return null;

        int end_index = (new_trucks.get(answer).toString().indexOf(" ", 16));
        return new_trucks.get(answer).toString().substring(16, end_index);
    }

    public static String choose_driver(){

        Scanner reader = new Scanner(System.in);
        System.out.println("Please choose an Driver: ");

        JsonObject new_drivers = Transportation_manager_controller.choose_driver();

        String answer = print_to_user(new_drivers.size(), new_drivers);

        if (new_drivers.size() == 0)
            return null;

        int end_index = (new_drivers.get(answer).toString().indexOf(" ", 15));
        return new_drivers.get(answer).toString().substring(15, end_index);
    }

    public static String choose_area(){

        System.out.println("Please choose an Shipping area: ");

        Scanner reader = new Scanner(System.in);

        JsonObject new_areas = Transportation_manager_controller.choose_area();

        if (new_areas.size() == 0)
            return null;

        String answer = print_to_user(new_areas.size(), new_areas);

        int end_index = (new_areas.get(answer).toString().indexOf(" ", 16));
        return new_areas.get(answer).toString().substring(16, end_index);
    }

    public static String choose_supplier(String area){

        System.out.println("Please choose an Supplier: ");

        Scanner reader = new Scanner(System.in);

        JsonObject new_suppliers = Transportation_manager_controller.choose_supplier(area);

        if (new_suppliers.size() == 0)
            return null;

        String answer = print_to_user(new_suppliers.size(), new_suppliers);

        int end_index = (new_suppliers.get(answer).toString().indexOf(" ", 16));
        return new_suppliers.get(answer).toString().substring(16, end_index);
    }

    public static String choose_store(String area){

        System.out.println("Please choose an Store: ");

        Scanner reader = new Scanner(System.in);

        JsonObject new_stores = Transportation_manager_controller.choose_store(area);

        if (new_stores.size() == 0)
            return null;

        String answer = print_to_user(new_stores.size(), new_stores);

        int end_index = (new_stores.get(answer).toString().indexOf(" ", 16));
        return new_stores.get(answer).toString().substring(16, end_index);
    }

    public static String print_to_user (int size, JsonObject j){
        Scanner reader = new Scanner(System.in);

        String answer;

        while (true) {
            for (int i = 1; i <= size; i++) {
                System.out.println("press " + i + " for - " + j.get(String.valueOf(i)));
            }

            answer = reader.next();

            try {
                Integer.parseInt(answer);
                if (Integer.parseInt(answer) > size || Integer.parseInt(answer) <= 0) {
                    throw new Exception();
                }
                break;
            }
            catch (Exception e) {
                System.out.println("Wrong input! try again..");
            }
        }
        return answer;
    }
}
