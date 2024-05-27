package presentation;

import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;

import java.util.Scanner;

public class Transportation_manager {
    public static void transportation_manager() {

        Scanner reader = new Scanner(System.in);

        System.out.println("Enter password:");
        String password = reader.next();

        while (!password.equals("123456789")) {
            System.out.println("Wrong password, try again..");
            System.out.println("Enter password: ");
            password = reader.next();
        }

        System.out.println("Hello Transportation Manager! What do you want to do?");
        System.out.println("""
                Add driver - '1'.
                Add truck - '2'.
                Add store - '3'.
                Add supplier - '4'.
                Make transportation - '5'.""");
        String answer = reader.next();

        while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3") && !answer.equals("4") && !answer.equals("5")){
            System.out.println("Wrong input! please write your ans ware again..");
            System.out.println("""
                Add driver - '1'.
                Add truck - '2'.
                Add store - '3'.
                Add supplier - '4'.
                Make transportation - '5'.""");
            answer = reader.next();
        }
        JsonObject new_json = new JsonObject();

        switch (answer) {
            case "1" -> add_driver();
            case "2" -> add_truck();
            case "3" -> add_store(new_json);
            case "4" -> add_supplier(new_json);
            case "5" -> make_transportation();
        }
    }

    public static void add_driver(){
        Add_Driver.add_driver();
    }

    public static void add_truck(){
        Add_Truck.add_truck();
    }

    public static void add_store(JsonObject j){
        j = add_site();
        Transportation_manager_controller.add_store(j);
    }

    public static void add_supplier(JsonObject j){
        j = add_site();
        Transportation_manager_controller.add_supplier(j);
    }

    public static void make_transportation(){
        Create_Transportation.create_Transport();
    }

    public static JsonObject add_site(){
        JsonObject new_json = new JsonObject();

        Scanner reader = new Scanner(System.in);

        System.out.println("Enter name:");
        new_json.addProperty("name", reader.next());

        System.out.println("Enter address:");
        new_json.addProperty("address", reader.next());

        System.out.println("Enter phone number:");
        new_json.addProperty("phone number", reader.next());

        System.out.println("Enter contact:");
        new_json.addProperty("contact", reader.next());

        System.out.println("Enter Shipping area:");
        new_json.addProperty("Shipping area", reader.next());

        reader.close();

        return new_json;
    }
}

