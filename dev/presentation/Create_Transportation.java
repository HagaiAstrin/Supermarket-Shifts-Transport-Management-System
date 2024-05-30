package presentation;

import com.google.gson.JsonObject;
import com.google.gson.internal.sql.SqlTypesSupport;
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

            System.out.println("\nPlease enter the date of the transportation:");
            new_json.addProperty("Date", reader.next());

            System.out.println("\nPlease enter the leaving time of the transportation:");
            new_json.addProperty("Leaving time", reader.next());

            System.out.println("\nPlease enter the source place of the transportation:");
            new_json.addProperty("Source", reader.next());

            String truck = choose_truck();

            if (truck == null) {
                System.out.println("Sorry but we can't arrange the transport, " +
                        "because there are no truck that available");
                return;
            }

            String driver = choose_driver(truck);

            if (driver == null) {
                System.out.println("Sorry but we can't arrange the transport, " +
                        "because there are no driver that available");
                return;
            }

            new_json.addProperty("Truck", truck);
            new_json.addProperty("Driver", driver);

            String area = choose_area();

            String a = "yes";

            while (a.equals("yes")){
                System.out.println("Please choose Supplier or Store:\n");
                System.out.println("Press '1' to - Supplier");
                System.out.println("Press '2' to - Store");


                String site = reader.next();
                while (!site.equals("1") && !site.equals("2")) {
                    System.out.println("\nWrong input!, try again..");
                    System.out.println("Please choose Supplier or Store:\n");
                    System.out.println("Press '1' to - Supplier");
                    System.out.println("Press '2' to - Store");
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
                        create_document(supplier, "Supplier", area);
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
                        create_document(store, "Store", area);
                    }
                }
                System.out.println("Do you want to add site?\nEnter 'yes' or 'no'.");
                a = reader.next();
            }
            String result = create_Transportation(new_json, targets);


//            add false

            System.out.println("Would you like to add a site for the transportation? ");
            System.out.println("Enter 'yes' or 'no': ");
            answer = reader.next();
        }
    }

    public static String choose_truck(){

        System.out.println("Please choose an Truck:");

        JsonObject new_trucks = Transportation_manager_controller.choose_truck();

        if (new_trucks.size() == 0)
            return null;

        return print_to_user(new_trucks.size(), new_trucks);
    }

    public static String choose_driver(String truck){

        System.out.println("Please choose a Driver:");

        JsonObject new_drivers = Transportation_manager_controller.choose_driver(truck);

        if (new_drivers.size() == 0)
            return null;

        return print_to_user(new_drivers.size(), new_drivers);
    }

    public static String choose_area(){

        System.out.println("Please choose an Shipping area:");

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

        System.out.println("Name of the item:");
        j.addProperty("Name", reader.next());

        while (true) {
            System.out.println("Weight of the item:");
            String Weight = reader.next();
            try {
                Double.parseDouble(Weight);
                j.addProperty("Weight", Weight);
                break;
            }
            catch (Exception e) {
                System.out.println("Wrong input! try again..");
            }
        }

        while (true) {
            System.out.println("Amount of the items");
            String Amount = reader.next();
            try {
                Integer.parseInt(Amount);
                j.addProperty("Amount", Amount);
                break;
            }
            catch (Exception e) {
                System.out.println("Wrong input! try again..");
            }
        }

        Transportation_manager_controller.create_items_list(j);
    }
    public static void create_document(String site, String type, String area){
        JsonObject j = new JsonObject();

        j.addProperty("Site", site);
        j.addProperty("Type", type);
        j.addProperty("Area", area);

        Transportation_manager_controller.create_document(j);
    }
    public static String create_Transportation(JsonObject j, ArrayList<String> a){
        return Transportation_manager_controller.create_transport(j, a);
    }
    public static String choose_solution(){

        Scanner reader = new Scanner(System.in);

        System.out.println("There is OverWeight, Please choose from the options bellow:\n");
        System.out.println("Press '1' to - Change Sites.");
        System.out.println("Press '2' to - Change Truck.");
        System.out.println("Press '3' to - Drop Sites.");
        System.out.println("Press '4' to - Drop Items.\n");

        String s = reader.next();

        while (!s.equals("1") && !s.equals("2") && !s.equals("3") && !s.equals("4")){
            System.out.println("Wrong input! try again..\n");
            System.out.println("There is OverWeight, Please choose from the options bellow:\n");
            System.out.println("Press '1' to - Change Sites.");
            System.out.println("Press '2' to - Change Truck.");
            System.out.println("Press '3' to - Drop Sites.");
            System.out.println("Press '4' to - Drop Items.\n");

            s = reader.next();
        }
        return s;
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
        return j.get(answer).getAsString();
    }
}
