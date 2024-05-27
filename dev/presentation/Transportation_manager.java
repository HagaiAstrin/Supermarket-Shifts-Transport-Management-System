package presentation;

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

        switch (answer) {
            case "1" -> add_driver();
            case "2" -> add_truck();
            case "3" -> add_store();
            case "4" -> add_supplier();
            case "5" -> make_transportation();
        }
    }

    public static void add_driver(){
        Add_Driver.add_driver();
    }

    public static void add_truck(){
        Add_Truck.add_truck();
    }

    public static void add_store(){
        String[] arr = add_site();
        int Phone = Integer.parseInt(arr[2]);
        Add_Store.add_store(arr[0],arr[1], Phone, arr[3], arr[4]);
    }

    public static void add_supplier(){
        String[] arr = add_site();
        int Phone = Integer.parseInt(arr[2]);
        Add_Supplier.add_supplier(arr[0],arr[1], Phone, arr[3], arr[4]);
    }

    public static void make_transportation(){
        new Create_Transportation();
    }

    public static String[] add_site(){
        String[] arr = new String[5];
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter name:");
        arr[0] = reader.next();

        System.out.println("Enter address:");
        arr[1] = reader.next();

        System.out.println("Enter phone number:");
        arr[2] = reader.next();

        System.out.println("Enter contact:");
        arr[3] = reader.next();

        System.out.println("Enter Shipping area:");
        arr[4] = reader.next();

        reader.close();

        return arr;
    }
}

