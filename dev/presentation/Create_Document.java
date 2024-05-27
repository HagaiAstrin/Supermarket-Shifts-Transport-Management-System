package presentation;

import domain.Document;
import domain.Item;
import domain.Site;
import domain.Transport;

import java.util.Scanner;


public class Create_Document {
    public static Document d;
    public static void create(Site s, Transport transport){
        Scanner reader = new Scanner(System.in);

        d = new Document(s);

        String a = "yes";

        while(a.equals("yes")) {
            System.out.println("Please enter the name of thee product:");
            String name = reader.next();

            System.out.println("Please enter the weight of this product:");
            double weight = reader.nextDouble();

            System.out.println("Please enter the amount of products would you like to order:");
            int amount = reader.nextInt();

            Item item = new Item(name, weight);

            d.add_Item(item, amount);

            System.out.println("Would you like to add products?\nPlease enter 'yes' or 'no'.");
            a = reader.next();
        }

        transport.add_document(d);
        if (transport.is_Over_Wight()){
            Solve_problem.solve();
        }
    }
}
