package presentation;

import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;

import java.util.ArrayList;
import java.util.Scanner;

public class Create_Transportation {
    public static void create_Transport() {

        JsonObject new_json = new JsonObject();

        Scanner reader = new Scanner(System.in);

        ArrayList<String> targets = new ArrayList<>();

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

            String area = choose_area();

            String a = "yes";

            while (a.equals("yes")){
                System.out.println("Please choose supplier or store:");
                System.out.println("""
                Supplier - '1'.
                Store - '2'.""");

                String site = reader.next();
                while (!site.equals("1") && !site.equals("2")) {
                    System.out.println("Wrong input!, try again..");
                    System.out.println("Please choose supplier or store:");
                    System.out.println("""
                Supplier - '1'.
                Store - '2'.""");
                    site = reader.next();
                }
                switch (site) {
                    case "1" -> {
                        String supplier = choose_supplier(area);
                        targets.add(supplier);
                        String b = "yes";
                        while (b.equals("yes")){
                            add_items();
                            System.out.println("Do you want to add another item?");
                            b = reader.next();
                        }
                        create_document(supplier, "supplier", area);
                    }
                    case "2" ->{
                        String store = choose_store(area);
                        targets.add(store);
                        String b = "yes";
                        while (b.equals("yes")){
                            add_items();
                            System.out.println("Do you want to add item?");
                            b = reader.next();
                        }
                        create_document(store, "store", area);
                    }
                }
                System.out.println("Do you want to add site?\nEnter 'yes' or 'no'.");
                a = reader.next();
            }
            boolean result = create_Transportation(new_json, targets);
            if (!result){

            }

            System.out.println("Would you like to add a site for the transportation? ");
            System.out.println("Enter 'yes' or 'no': ");
            answer = reader.next();
        }
    }

    public static String choose_truck(){

        System.out.println("Please choose an Truck: ");

        JsonObject new_trucks = Transportation_manager_controller.choose_truck();

        if (new_trucks.size() == 0)
            return null;

        return print_to_user(new_trucks.size(), new_trucks);
    }

    public static String choose_driver(){

        System.out.println("Please choose an Driver: ");

        JsonObject new_drivers = Transportation_manager_controller.choose_driver();

        if (new_drivers.size() == 0)
            return null;

        return print_to_user(new_drivers.size(), new_drivers);
    }

    public static String choose_area(){

        System.out.println("Please choose an Shipping area: ");

        JsonObject new_areas = Transportation_manager_controller.choose_area();

        return print_to_user(new_areas.size(), new_areas);
    }

    public static String choose_supplier(String area){

        System.out.println("Please choose an Supplier:");

        JsonObject new_suppliers = Transportation_manager_controller.choose_supplier(area);

        return print_to_user(new_suppliers.size(), new_suppliers);
    }

    public static String choose_store(String area){

        System.out.println("Please choose an Store:");

        JsonObject new_stores = Transportation_manager_controller.choose_store(area);

        return print_to_user(new_stores.size(), new_stores);
    }

    public static void add_items(){
        JsonObject j = new JsonObject();

        Scanner reader = new Scanner(System.in);

        System.out.println("Please enter Item:");

        System.out.println("Name:");
        j.addProperty("name", reader.next());

        System.out.println("Weight:");
        j.addProperty("weight", reader.next());

        System.out.println("Amount");
        j.addProperty("amount", reader.next());

    }
    public static void create_document(String site, String type, String area){
        JsonObject j = new JsonObject();

        j.addProperty("site", site);
        j.addProperty("type", type);
        j.addProperty("area", area);

        Transportation_manager_controller.create_document(j);
    }
    public static boolean create_Transportation(JsonObject j, ArrayList<String> a){
        return Transportation_manager_controller.create_transport(j, a);
    }
    public static void make_solution(){

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
        return j.get(answer).toString();
    }
}
