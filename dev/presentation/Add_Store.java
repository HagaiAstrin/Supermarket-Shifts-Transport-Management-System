package presentation;

import domain.DataStructManager;
import domain.Store;

public class Add_Store {

    /**
     * adding a new Store to the Data Structure
     */
    public static void add_store(String name, String address, int Phone, String contact,String Shipping_area) {

//        need to do Jsons!!!

        Store new_store_to_put = new Store(name, address, Phone, contact, Shipping_area);
        DataStructManager.add_Store(Shipping_area, new_store_to_put);
    }
}

//        Scanner reader = new Scanner(System.in);
//        Map<Item, Integer> map = new HashMap<>();
//
//        int count = 1;
//        while(true) {
//            System.out.println("Please enter the name of the missing Item number : "+count +
//                    ".\nif there is no more Items to order press 9");
//            String item_name = reader.next();
//            if(item_name.equals("9")) break;
//            System.out.println("Please enter the weight of the missing Item number : "+count);
//            double item_weight = reader.nextDouble();
//            Item it = new Item(item_name, item_weight);
//            System.out.println("Please write down how many times to order the Item number : "+count++);
//            int item_count = reader.nextInt();
//            map.put(it, item_count);
//        }
// add map
