package presentation;

import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;

import java.util.Scanner;

public class Transportation_manager {
    public static void transportation_manager() {

        Scanner reader = new Scanner(System.in);


//        System.out.println("Enter password:");
//        String password = reader.next();
//
//        while (!password.equals("123456789")) {
//            System.out.println("Wrong password, try again..");
//            System.out.println("Enter password: ");
//            password = reader.next();
//        }
        while (true) {

            System.out.println("Hello Transportation Manager! What do you want to do?\n");
            System.out.println("Choose from the options bellow:\n");
            System.out.println("'1' - Add Driver\n");
            System.out.println("'2' - Add Truck.\n");
            System.out.println("'3' - Add Store.\n");
            System.out.println("'4' - Add Supplier\n");
            System.out.println("'5' - Make Transportation.\n");
            System.out.println("'9' - To Exit.\n");

            String answer = reader.next();

            while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3") && !answer.equals("4")
                                                              && !answer.equals("5") && !answer.equals("9")) {
                System.out.println("Wrong input! please write your ans ware again..\n");
                System.out.println("Choose from the options bellow:\n");
                System.out.println("'1' - Add driver\n");
                System.out.println("'2' - Add store.\n");
                System.out.println("'3' - Add supplier.\n");
                System.out.println("'4' - Add driver\n");
                System.out.println("'5' - Make transportation.\n");
                System.out.println("'9' - To Exit.\n");

                answer = reader.next();
            }

            if (answer.equals("9"))
                break;

            switch (answer) {
                case "1" -> add_driver();
                case "2" -> add_truck();
                case "3" ->{
                    String a = "yes";
                    while (a.equals("yes")){
                        add_store();
                        System.out.println("Added Store successful!");
                        System.out.println("Would you like to add another Store? Enter 'yes' or 'no'.");
                        a = reader.next();
                    }
                }
                case "4" -> {
                    String b = "yes";
                    while (b.equals("yes")){
                        add_store();
                        System.out.println("Added Supplier successful!");
                        System.out.println("Would you like to add another Supplier? Enter 'yes' or 'no'.");
                        b = reader.next();
                    }
                }
                case "5" -> make_transportation();
            }
        }
    }

    public static void add_driver(){
        Add_Driver.add_driver();
    }

    public static void add_truck(){
        Add_Truck.add_truck();
    }

    public static void add_store(){

        JsonObject j = add_site();
        j.addProperty("type", "store");
        Transportation_manager_controller.add_store(j);
    }

    public static void add_supplier(){
        add_site().addProperty("type", "supplier");
        Transportation_manager_controller.add_supplier(add_site());
    }

    public static void make_transportation(){
        Create_Transportation.create_Transport();
    }

    public static JsonObject add_site(){
        JsonObject new_json = new JsonObject();

        Scanner reader = new Scanner(System.in);

        System.out.println("Enter name:");
        String name = reader.next();

        System.out.println("Enter address:");
        String address = reader.next();

        System.out.println("Enter phone number:");
        String phone = reader.next();

        System.out.println("Enter contact:");
        String contact = reader.next();

        System.out.println("Enter Shipping area:");
        String Shipping_area = reader.next();

        new_json.addProperty("name", name);
        new_json.addProperty("address", address);
        new_json.addProperty("phone number", phone);
        new_json.addProperty("contact", contact);
        new_json.addProperty("Shipping area", Shipping_area);

        return new_json;
    }
}

