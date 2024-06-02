package domain;
import java.util.ArrayList;
import java.util.Map;

import static domain.DataStructManager.all_items;

public class Transport {
    private int id;
    private String date;
    private String leaving_time;
    private Truck truck;
    private Driver driver;
    private String source;
    private ArrayList<Document> targets;
    private double max_weight;


    /**
     * Constructor of Transport
     */
    public Transport(Truck truck, Driver driver, String source, ArrayList<Document> t) {
        this.truck = truck;
        this.driver = driver;
        this.targets = new ArrayList<>(t);
        this.source = source;
    }


    /**
     * Getter max_weight
     */
    public double get_transport_Max_weight() {
        return this.max_weight;
    }
    /**
     * Setter id
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Setter date
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * Setter leaving_time
     */
    public void setLeaving_time(String leaving_time) {
        this.leaving_time = leaving_time;
    }
    /**
     * @return false if the truck in Over weight, true if it f+good to go
     */
    public int Is_Over_Weight() {
        int result = 0;
        double count = truck.getNet_weight();
        for (Document d : targets) {
            Site new_site = d.getTarget();
            if (new_site.getType().equals("Supplier")) {
                count += d.getDoc_weight();
                if (count > max_weight + truck.getNet_weight()) {
                    max_weight = count - truck.getNet_weight();
                }
                if (count > truck.getMax_weight()) {
                    result = 2;
                }
            } else {
                count -= d.getDoc_weight();
            }
        }
        if (result == 2)
            return result;

        if (count != truck.getNet_weight())
            return 1;

        return result;
    }
    /**
     * @return String represent of the Transport
     */
    public String to_String_tran() {
        StringBuilder new_s = new StringBuilder();
        String s = "Transport number : " + this.id + "\nDate : " + this.date + "\nNumber of Truck : " + this.truck.getLicence_number() + "\n";
        new_s.append(s);
        s = "Leaving time : " + this.leaving_time + "\nDriver name : " + this.driver.getName() + "\n";
        new_s.append(s);
        s = "Address of the Source: " + this.source;
        new_s.append(s);
        s = "\nMax weight of the truck during the drive : " + max_weight;
        new_s.append(s);
        new_s.append("\nTargets : \n");
        int count = 1;
        for (Document d : targets) {
            Site site = d.getTarget();
            if (site.getType().equals("Store")) {
                s = "        " + count++ + ". Dropped products at Store : " + site.getName() + "\n";
                new_s.append(s);
            } else {
                s = "        " + count++ + ". Collect products at Supplier : " + site.getName() + "\n";
                new_s.append(s);
            }
            s = "           - Address: " + site.getAddress() + "\n           " +
                    "- Contact name: " + site.getContact() + "\n           - Phone: " + site.getPhone() + "\n" +
                    "           - Shipping weight: " + d.getDoc_weight() + "\n";

            new_s.append(s);
            new_s.append("\n           - Items: \n");
            for (Map.Entry<Item, Integer> iter : d.getItem_map().entrySet()) {
                s = "                 Name: " + iter.getKey().getName() + ", Amount: " + iter.getValue() + "\n";
                new_s.append(s);
            }
            s = "\n\n";
            new_s.append(s);
        }
        return new_s.toString();
    }
}
