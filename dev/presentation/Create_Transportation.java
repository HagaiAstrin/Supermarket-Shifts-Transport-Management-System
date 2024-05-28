package presentation;

import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;
import domain.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Create_Transportation {
    public static Transport new_t;
    public static void create_Transport(){

        JsonObject new_json = new JsonObject();

        Scanner reader = new Scanner(System.in);

        String answer = "yes";

        System.out.println("Please enter the date of the transportation:");
        String date = reader.next();

        System.out.println("Please enter the leaving time of the transportation:");
        String l_time = reader.next();

        System.out.println("Please enter the source place of the transportation:");
        String source = reader.next();

        String truck = choose_truck();
        String driver = choose_driver();

        if (truck == null || driver == null){
            System.out.println("Sorry but we can't arrange the transport, " +
                    "because there are no truck or driver that available");
            return;
        }

        new_json.addProperty("date", date);
        new_json.addProperty("leaving time", l_time);
        new_json.addProperty("truck", truck);
        new_json.addProperty("driver", driver);
        new_json.addProperty("source", source);

//        Transportation_manager_controller.create_transport(new_json);

        while (answer.equals("yes")) {


        }









            System.out.println("Please choose supplier or store:\n" +
                               "Supplier - '1'.\nStore - '2'.");
            String site = reader.next();
            while (!site.equals("1") && !site.equals("2")){
                System.out.println("Wrong input!, try again..");
                System.out.println("Please choose supplier or store:\n" +
                                   "Supplier - '1'.\nStore - '2'.");
                site = reader.next();
            }

            switch (site) {
                case "1" -> choose_supplier(map_shipping.get(Shipping_area));
                case "2" -> choose_store(map_shipping.get(Shipping_area));
            }
            System.out.println("Would you like to add a site for the transportation?\nEnter 'yes' or 'no'.");
            answer = reader.next();
        }
        new_t.is_Weight_Good();
    }

    public static String choose_truck(){

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a truck:");

        JsonObject new_trucks = Transportation_manager_controller.choose_truck();

        int count = Integer.parseInt(new_trucks.get("count").toString());

        if (count == 0)
            return null;

        String answer;
        while (true) {
            for (int i = 1; i <= count; i++) {
                System.out.println("press "+i+" for - "+new_trucks.get(String.valueOf(count)));
            }

            answer = reader.next();

            try {
                Integer.parseInt(answer);
                if (Integer.parseInt(answer) > count || Integer.parseInt(answer) <= 0) {
                    throw new Exception();
                }
                break;
            }
            catch (Exception e) {
                System.out.println("Wrong input! try again..");
            }
        }
        int end_index = (new_trucks.get(answer).toString().indexOf(" ", 16));
        return new_trucks.get(answer).toString().substring(16, end_index);
    }

    public static String choose_driver(){

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a Driver:");

        JsonObject new_drivers = Transportation_manager_controller.choose_driver();

        int count = Integer.parseInt(new_drivers.get("count").toString());

        if (count == 0)
            return null;

        String answer;
        while (true) {
            for (int i = 1; i <= count; i++) {
                System.out.println("press "+i+" for - "+new_drivers.get(String.valueOf(count)));
            }

            answer = reader.next();

            try {
                Integer.parseInt(answer);
                if (Integer.parseInt(answer) > count || Integer.parseInt(answer) <= 0) {
                    throw new Exception();
                }
                break;
            }
            catch (Exception e) {
                System.out.println("Wrong input! try again..");
            }
        }
        int end_index = (new_drivers.get(answer).toString().indexOf(" ", 15));
        return new_drivers.get(answer).toString().substring(15, end_index);
    }

    public static String choose_Area(){

        System.out.println("Please choose an Shipping area:\n");

        JsonObject new_trucks = Transportation_manager_controller.choose_Area();

        int count = Integer.parseInt(new_trucks.get("count").toString());

        if (count == 0)
            return null;

        String answer;
        while (true) {
            for (int i = 1; i <= count; i++) {
                System.out.println("press " + i + " for - " + new_trucks.get(String.valueOf(count)));
            }

            answer = reader.next();

            try {
                Integer.parseInt(answer);
                if (Integer.parseInt(answer) > count || Integer.parseInt(answer) <= 0) {
                    throw new Exception();
                }
                break;
            }
            catch (Exception e) {
                System.out.println("Wrong input! try again..");
            }
        }
        int end_index = (new_trucks.get(answer).toString().indexOf(" ", 16));
        return new_trucks.get(answer).toString().substring(16, end_index);
    }

    public static void choose_supplier(String area) {

        Scanner reader = new Scanner(System.in);

        System.out.println("Please choose supplier:\n");
        Map<Integer, String> map_shipping = new HashMap<>();
        StringBuilder suppliers = new StringBuilder();
        int count = 1;
        for (Map.Entry<String, Site> iter : DataStructManager.manager_Map.get(area).get("Supplier").entrySet()) {
            String s = "press '" + count + "' for - " + iter.getKey() + ".\n";
            suppliers.append(s);
            map_shipping.put(count++, iter.getKey());
        }
        System.out.println(suppliers);

        int sup = reader.nextInt();

        while (sup>count){
            System.out.println("Wrong input! try again..");
            System.out.println(suppliers);
            sup = reader.nextInt();
        }


        Create_Document.create(DataStructManager.manager_Map.get(area).get("Supplier").get(map_shipping.get(sup)), new_t);
    }

    public static void choose_store (String area){

            Scanner reader = new Scanner(System.in);

            System.out.println("Please choose store:\n");
            Map<Integer, String> map_shipping = new HashMap<>();
            StringBuilder stores = new StringBuilder();
            int count = 1;
            for (Map.Entry<String, Site> iter : DataStructManager.manager_Map.get(area).get("Store").entrySet()) {
                String s = "press '" + count + "' for - " + iter.getKey() + ".\n";
                stores.append(s);
                map_shipping.put(count++, iter.getKey());
            }
            System.out.println(stores);

            int str = reader.nextInt();

            while (str>count){
                System.out.println("Wrong input! try again..");
                System.out.println(stores);
                str = reader.nextInt();
            }
            Create_Document.create(DataStructManager.manager_Map.get(area).get("Store").get(map_shipping.get(str)), new_t);
        }

}
