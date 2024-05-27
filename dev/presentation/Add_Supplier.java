package presentation;

import domain.DataStructManager;
import domain.Supplier;

public class Add_Supplier {
    public static void add_supplier(String name, String address, int Phone, String contact,String Shipping_area){

//        need to do Jsons!!!

        Supplier new_Supplier_to_put = new Supplier(name, address, Phone, contact, Shipping_area);
        DataStructManager.add_Supplier(Shipping_area, new_Supplier_to_put);
    }
}




//        Scanner reader = new Scanner(System.in);
//        Set<String> set = new HashSet<>();

//        int count = 1;
//        while(true) {
//            System.out.println("Please write down the name of the product number : "+count+" that the supplier provides."+
//                    "\nif there is no more product to write press 9");
//            String item_name = reader.next();
//            if(item_name.equals("9")) break;
//            set.add(item_name);
//        }

//        add set
