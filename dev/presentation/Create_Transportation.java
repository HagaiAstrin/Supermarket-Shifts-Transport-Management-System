package presentation;

import domain.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static domain.DataStructManager.drivers;
import static domain.DataStructManager.trucks;

public class Create_Transportation {
    public static Transport new_t;
    public Create_Transportation(){

//        need to do Jsons!!!

        Scanner reader = new Scanner(System.in);

        System.out.println("Would you like to add a site for the transportation?\nEnter 'yes' or 'no'.");
        String answer = reader.next();

        System.out.println("Please enter the date of the transportation:");
        String date = reader.next();

        System.out.println("Please enter the leaving time of the transportation:");
        String l_time = reader.next();

        System.out.println("Please enter the source place of the transportation:");
        String source = reader.next();

        Truck t =  choose_truck();
        Driver d = choose_driver(t);

        if (t == null || d == null){
            System.out.println("Sorry but we can't arrange the transport!");
            return;
        }

        new_t = new Transport (date, l_time, t,d, source);

        while (answer.equals("yes")){
            System.out.println("Please choose an Shipping area:\n");
            Map<Integer, String> map_shipping = new HashMap<>();
            StringBuilder areas = new StringBuilder();
            int count = 1;
            for (Map.Entry<String, Map<String, Map<String, Site>>> iter : DataStructManager.manager_Map.entrySet()) {
                String s = "Press '"+count+"' for - "+iter.getKey()+".\n";
                areas.append(s);
                map_shipping.put(count++, iter.getKey());
            }

            System.out.println(areas);
            int shipping = reader.nextInt();

            while (shipping>count){
                System.out.println("Wrong input! try again..");
                System.out.println(areas);
                shipping = reader.nextInt();
            }

            String Shipping_area = map_shipping.get(shipping);

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

    public static Truck choose_truck(){
        Scanner reader = new Scanner(System.in);

        System.out.println("Enter a truck:");
        Map<Integer, Truck> trucks_map = new HashMap<>();
        StringBuilder Trucks = new StringBuilder();
        int count = 1;
        for (Truck a:trucks){
            if (a.isAvailability()){
                String s = "Press '" + count + "' for - " + a.getLicence_number() + ".";
                Trucks.append(s);
                trucks_map.put(count++, a);
            }
        }
        if (Trucks.isEmpty()){
            System.out.println("There are no available trucks!");
            return null;
        }
        System.out.println(Trucks);
        int answer = reader.nextInt();

        return trucks_map.get(answer);
    }

    public static Driver choose_driver(Truck truck) {
        if (truck == null)
            return null;

        Scanner reader = new Scanner(System.in);

        System.out.println("Enter a driver:");
        Map<Integer, Driver> drivers_map = new HashMap<>();
        StringBuilder Drivers = new StringBuilder();
        int count = 1;
        for (Driver a : drivers) {
            if (a.isAvailability() && a.getLicense() >= truck.getLicence_level()) {
                String s = "Press '" + count + "' for - " + a.getName() + ".";
                Drivers.append(s);
                drivers_map.put(count++, a);
            }
        }
        if (Drivers.isEmpty()){
            System.out.println("There are no available drivers!");
            return null;
        }
        System.out.println(Drivers);
        int answer = reader.nextInt();
        reader.close();

        return drivers_map.get(answer);
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
