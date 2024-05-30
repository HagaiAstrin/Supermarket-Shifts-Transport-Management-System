package presentation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;

import java.util.Map;
import java.util.Scanner;

public class Create_Transportation {
    public static void create_Transport() {

        JsonObject new_json = new JsonObject();

        Scanner reader = new Scanner(System.in);


        String answer = "yes";

        while (answer.equals("yes")) {

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

            while (a.equals("yes")) {
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
                        String b = "yes";
                        while (b.equals("yes")) {
                            add_items();
                            System.out.println("Do you want to add another item?");
                            b = reader.next();
                        }
                        create_document(supplier, "Supplier", area);
                    }
                    case "2" -> {
                        String store = choose_store(area);
                        String b = "yes";
                        while (b.equals("yes")) {
                            add_items();
                            System.out.println("Do you want to add item?");
                            b = reader.next();
                        }
                        create_document(store, "Store", area);
                    }
                }
                System.out.println("Do you want to add anther site?\nEnter 'yes' or 'no'.");
                a = reader.next();
            }
            boolean result = create_Transportation(new_json);
            while (!result) {
                JsonObject sol_w;
                String sol = choose_solution();
                switch (sol) {
                    case "1" ->{

                    }
                    //TODO Truck change
                    case "2" -> {
                        sol_w = Transportation_manager_controller.choose_good_Truck();
                        if (sol_w.size() == 0)
                            System.out.println("There is no available Trucks for this transportation, " +
                                    "please choose other solution");
                        String s = change_Truck_print(sol_w);
                        new_json.remove("Truck");
                        new_json.addProperty("Truck", s);
                        String d = choose_driver(s);

                        if (d == null) System.out.println("There is no available Trucks for this transportation, " +
                                "please choose other solution");
                        new_json.remove("Driver");
                        new_json.addProperty("Driver", d);
                    }
                    //TODO Dropped Sites
                    case "3" ->{
                        sol_w = Transportation_manager_controller.Choose_Drop_Target();
                        JsonObject j = new JsonObject();
                        int j_count = 1;
                        String p = "yes";
                        while (p.equals("yes")){
                            String site_answer = print_to_user(sol_w.size(), sol_w);
                            boolean bool = true;
                            if(j_count != 1) {
                                for (int i = 1; i < j_count; i++){
                                    if (j.get(String.valueOf(i)).getAsString().equals(site_answer)) {
                                        System.out.println("Tou already dropped this site ! ");
                                        bool = false;
                                        break;
                                    }
                                }
                            }
                            if(bool) {
                                j.addProperty(String.valueOf(j_count++), site_answer);
                                System.out.println("\nWould you like to drop another site? ");
                                System.out.println("Enter 'yes' or 'no':");
                                p = reader.next();
                            }
                        }
                        Transportation_manager_controller.drop_Documents(j);
                    }
//                    case "4" ->

                }
                result = create_Transportation(new_json);
            }
            System.out.println("Transportation added successfully!\n");
            System.out.println("Would you like to make a new Transportation?\n");
            System.out.println("Enter 'yes' or 'no':");
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
    public static boolean create_Transportation(JsonObject j){
        return Transportation_manager_controller.create_transport(j);
    }
    public static String choose_solution(){

        Scanner reader = new Scanner(System.in);
        StringBuilder new_string = new StringBuilder();
        new_string.append("\nThere is OverWeight, Please choose from the options bellow:\n");
        new_string.append("Press '1' to - Change Sites.\n");
        new_string.append("Press '2' to - Change Truck.\n");
        new_string.append("Press '3' to - Drop Sites.\n");
        new_string.append("Press '4' to - Drop Items.\n");

        String s = "0";

        while (!s.equals("1") && !s.equals("2") && !s.equals("3") && !s.equals("4")){
            System.out.println(new_string);
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

    public static String change_Truck_print(JsonObject j){
        System.out.println("Please choose a different Truck for the Transport: ");
        return print_to_user(j.size(), j);
    }

    public static JsonObject dropped_Json_string(JsonObject j, String valueToRemove){
        for (Map.Entry<String, JsonElement> entry : j.entrySet()) {
            if (entry.getValue().getAsString().equals(valueToRemove)) {
                String keyToRemove = entry.getKey();
                j.remove(keyToRemove);
                break;
            }
        }
        return j;
    }
//    public static JsonObject dropped_Json_string(JsonObject j, int count){
//        for (Map.Entry<String, JsonElement> entry : j.entrySet()) {
//            if (entry.getValue().getAsString().equals(valueToRemove)) {
//                String keyToRemove = entry.getKey();
//                j.remove(keyToRemove);
//                break;
//            }
//        }
//        return j;
//}

}
