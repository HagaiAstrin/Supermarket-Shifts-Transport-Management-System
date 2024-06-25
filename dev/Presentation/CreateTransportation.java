package Presentation;

import com.google.gson.JsonObject;
import Domain.TransportationManagerController;

import java.util.Scanner;

public class CreateTransportation {
    private static JsonObject new_json = new JsonObject();
    /**
     * Create new Transport with the Inputs from the user
     */
    public static void create_Transport() {

        Scanner reader = new Scanner(System.in);

        String answer = "yes";

        while (answer.equals("yes")) {

            System.out.println("\nPlease enter the address of the source place of the transportation:");
            new_json.addProperty("Source", reader.nextLine());

            System.out.println("\nPlease enter the address of the date of the transportation:");
            new_json.addProperty("Date", reader.nextLine());

            System.out.println("\nPlease enter the address of the leaving time of the transportation:");
            new_json.addProperty("Leaving time", reader.nextLine());

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

            create_Transportation(new_json);

            String area = choose_area();

            String a = "yes";


            while (a.equals("yes")) {

                choose_site(area);
                System.out.println("\nDo you want to add another site for the transport?\nEnter 'yes' or 'no'.");
                a = reader.nextLine();
                while (!a.equals("yes") && !a.equals("no")) {
                    System.out.println("\nWrong input!, try again..");
                    System.out.println("\nDo you want to add another site for the transport?\nEnter 'yes' or 'no'.");
                    a = reader.nextLine();
                }
            }

            int result = check_transportation();

            boolean bool = true;
            //HeadLine Weight Solution
            while (result != 0) {
                switch (result) {
                    case 1 -> {
                        System.out.println("\nThere are still products in the truck!\nPlease continue with the Transportation making");
                        System.out.println("Please add sites to the transportation!\n");
                        choose_site(area);

                    }
                    case 2 -> {
                        String sol = choose_solution();
                        switch (sol) {

                            case "1" -> Change_Sites(area);
                            case "2" -> {
                                if (!Change_Truck()) continue;
                            }
                            case "3" -> {
                                bool = Drop_sites();
                            }
                            case "4" -> bool = Drop_Items();

                        }
                    }
                }

                if(!bool) break;
                result = check_transportation();
            }

            if(bool)
                System.out.println("\nTransportation added successfully!\n");

            else
                System.out.println("\nThe Transportation canceled.\n");

            System.out.println("Would you like to make a new Transportation?");
            System.out.println("Enter 'yes' or 'no':");
            answer = reader.nextLine();
        }
    }


    /**
     * Selection methods from the User
     */
    public static String choose_truck() {

        System.out.println("\nPlease choose an Truck:");

        JsonObject new_trucks = TransportationManagerController.choose_truck();

        if (new_trucks.size() == 0)
            return null;

        return Print_to_user(new_trucks.size(), new_trucks);
    }
    public static String choose_driver(String truck) {

        System.out.println("\nPlease choose a Driver:");

        JsonObject new_drivers = TransportationManagerController.choose_driver(truck);

        if (new_drivers.size() == 0)
            return null;

        return Print_to_user(new_drivers.size(), new_drivers);
    }
    public static String choose_area() {

        System.out.println("\nPlease choose a Shipping area:");

        JsonObject new_areas = TransportationManagerController.choose_area();

        return Print_to_user(new_areas.size(), new_areas);
    }
    public static String choose_supplier_or_store_pres(String area, String type) {

        System.out.println("\nPlease choose a " + type + " :");

        JsonObject new_suppliers = TransportationManagerController.choose_supplier_or_store(area, type);

        return Print_to_user(new_suppliers.size(), new_suppliers);
    }
    public static void choose_site(String area) {

        Scanner reader = new Scanner(System.in);

        StringBuilder str_Sup_Sto = new StringBuilder();

        str_Sup_Sto.append("\nPlease choose Supplier or Store:");
        str_Sup_Sto.append("\nPress '1' to - Supplier");
        str_Sup_Sto.append("\nPress '2' to - Store");
        System.out.println(str_Sup_Sto);

        String site = reader.next();
        while (!site.equals("1") && !site.equals("2")) {
            System.out.println("\nWrong input!, try again..\n");
            System.out.println(str_Sup_Sto);
            site = reader.next();
        }

        //HeadLine Supplier / Store choose
        switch (site) {
            case "1" -> {
                String supplier = choose_supplier_or_store_pres(area, "Supplier");
                String it = "yes";
                while (it.equals("yes")) {
                    AddItem.add_items();

                    System.out.println("\nItem added successfully!\n");

                    System.out.println("\nDo you want to add another item?\nEnter 'yes' or 'no'.");
                    it = reader.next();
                    while (!it.equals("yes") && !it.equals("no")) {
                        System.out.println("\nWrong input!, try again..");
                        System.out.println("\nDo you want to add another item?\nEnter 'yes' or 'no'.");
                        it = reader.next();
                    }
                }
                create_document(supplier, "Supplier", area);
            }

            case "2" -> {
                JsonObject Item_j = new JsonObject();

                String store = choose_supplier_or_store_pres(area, "Store");
                String it = "yes";
                while (it.equals("yes")) {

                    JsonObject j = TransportationManagerController.choose_items();

                    if (j.size() != 0) {
                        System.out.println();
                        String item = Print_to_user(j.size(), j);

                        int amount = TransportationManagerController.amount_items(item);

                        System.out.println();

                        System.out.println("Please enter amount: (The maximum products you can order is - " + amount + ").");
                        int a = reader.nextInt();

                        while (a > amount) {
                            System.out.println("\nYou tried to order too much of this product!, try again..");
                            System.out.println("Please enter amount:");
                            a = reader.nextInt();
                        }
                        Item_j.addProperty("Item", item);
                        Item_j.addProperty("Amount", String.valueOf(a));

                        TransportationManagerController.add_Item(Item_j, "Store");

                        System.out.println("\nItem added successfully!\n");
                        System.out.println("\nDo you want to  add another item?\nEnter 'yes' or 'no'.");
                        it = reader.next();
                        while (!it.equals("yes") && !it.equals("no")) {
                            System.out.println("\nWrong input!, try again..");
                            System.out.println("\nDo you want to add another item?\nEnter 'yes' or 'no'.");
                            it = reader.next();
                        }
                    }
                    else {
                        System.out.println("There are no more product in the truck!");
                        break;
                    }
                }
                create_document(store, "Store", area);
            }
        }
    }


    /**
     * Creation methods
     */
    public static void create_document(String site, String type, String area) {
        JsonObject j = new JsonObject();

        j.addProperty("Site", site);
        j.addProperty("Type", type);
        j.addProperty("Area", area);

        TransportationManagerController.create_document(j);
    }
    public static void create_Transportation(JsonObject j) {
        TransportationManagerController.create_transport(j);
    }
    public static int check_transportation() {
        return TransportationManagerController.check_transportation();
    }


    /**
     * Solution methods:
     */
    public static String choose_solution() {

    Scanner reader = new Scanner(System.in);
    StringBuilder new_string = new StringBuilder();
    new_string.append("\nThere is OverWeight, Please choose from the options bellow:\n");
    new_string.append("Press '1' to - Change Sites.\n");
    new_string.append("Press '2' to - Change Truck.\n");
    new_string.append("Press '3' to - Drop Sites.\n");
    new_string.append("Press '4' to - Drop Items.");

    String s = "0";

    while (!s.equals("1") && !s.equals("2") && !s.equals("3") && !s.equals("4")) {
        System.out.println(new_string);
        s = reader.next();
    }
    return s;
}
    public static boolean Drop_sites() {

        Scanner reader = new Scanner(System.in);
        String p = "yes";
        boolean bool = true;
        while (p.equals("yes")) {
            JsonObject sol_w = TransportationManagerController.Choose_Supplier_Target();
            System.out.println("\nWhich Supplier do you want to dropped ? ");
            String site_answer = Print_to_user(sol_w.size(), sol_w);
            TransportationManagerController.drop_Site(site_answer);
            if(sol_w.size() - 1 == 0){
                bool = false;
                break;
            }
            System.out.println("\nWould you like to drop another Site? ");
            System.out.println("Enter 'yes' or 'no':");
            p = reader.next();
        }
        if(!bool) System.out.println("\nYou dropped all the Sits from the Transport ! ");
        return bool;
    }
    public static boolean Drop_Items() {
        Scanner reader = new Scanner(System.in);

        JsonObject j_item;
        String p = "yes";
        boolean bool = true;
        JsonObject sol_w = TransportationManagerController.Choose_Supplier_Target();
        while (p.equals("yes")) {
            System.out.println("\nWhich Site do you want to dropped Items from ? ");
            String site_answer = Print_to_user(sol_w.size(), sol_w);
            System.out.println("\nWhich Item do you want to dropped ? ");
            j_item = TransportationManagerController.get_Items_Json(site_answer);
            String item_answer = Print_to_user(j_item.size(), j_item);

            TransportationManagerController.drop_Items(item_answer, site_answer);
            sol_w = TransportationManagerController.Choose_Supplier_Target();
            if(sol_w.size() == 0){
                bool = false;
                break;
            }
            System.out.println("\nWould you like to drop another Item? ");
            System.out.println("Enter 'yes' or 'no':");
            p = reader.next();
        }
        if(!bool) System.out.println("\nYou dropped all the Items from all the Sites ! ");

        return bool;
    }
    public static String Change_Truck_print(JsonObject j) {
        System.out.println("\nPlease choose a different Truck for the Transport: ");
        return Print_to_user(j.size(), j);
    }
    public static void Change_Sites(String area) {
        JsonObject s = TransportationManagerController.Choose_Supplier_Target();
        System.out.println("\nwhich Supplier you want to replace ? ");
        String site_answer = Print_to_user(s.size(), s);
        choose_site(area);
        TransportationManagerController.replace_Documents(site_answer);
    }
    public static boolean Change_Truck() {

        JsonObject j = new JsonObject();

        JsonObject sol_w = TransportationManagerController.choose_good_Truck();
        if (sol_w.size() == 0) {
            System.out.println("There is no available Trucks for this transportation, ");
            System.out.println("please choose other solution");
            return false;
        }
        String s = Change_Truck_print(sol_w);
        j.addProperty("Truck", s);

        String d = choose_driver(s);

        if (d == null){
            System.out.println("There is no available Drivers for this transportation!\n");
            System.out.println("please choose other solution!");
            return false;
        }
        j.addProperty("Driver", d);

        TransportationManagerController.change_truck(j);
        return true;
    }


    /**
     * Prints to the user the possible choices depending on the stage he is in
     */
    public static String Print_to_user(int size, JsonObject j) {
        Scanner reader = new Scanner(System.in);

        String answer;

        while (true) {
            for (int i = 1; i <= size; i++) {
                System.out.println("press " + i + " for - " + j.get(String.valueOf(i)));
            }

            answer = reader.nextLine();

            try {
                Integer.parseInt(answer);
                if (Integer.parseInt(answer) > size || Integer.parseInt(answer) <= 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("\nWrong input! try again..");
            }
        }
        return j.get(answer).getAsString();
    }
}
