package Domain.DomainTransport.Obejects;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Transportation {
    private int id;
    private String source;
    private String day;
    private String shift;
    private Truck truck;
    private Driver driver;
    private String Status;
    private ArrayList<Document> targets = new ArrayList<>();
    private double max_weight;
    private Map<Item, Integer> items = new HashMap<>();
    public Map<Item, Integer> all_transport_items = new HashMap<>();



    /**
     * Constructor of Transport
     */
    public Transportation(Truck truck, Driver driver, String source, String day, String time) {
        this.truck = truck;
        this.driver = driver;
        this.source = source;
        this.day = day;
        this.shift = time;
        this.Status = "Pending";
    }


    /**
     * Getter max_weight
     */
    public double get_transport_Max_weight() {
        return this.max_weight;
    }
    /**
     * @return all the documents in the System
     */
    public Truck getTruck() {
        return truck;
    }
    public Driver getDriver() {
        return driver;
    }
    public ArrayList<Document> getTargets() {
        return targets;
    }
    public Map<Item, Integer> getAll_transport_items() {
        return all_transport_items;
    }
    public Map<Item, Integer> getItems() {
        return items;
    }
    public int getId() {
        return id;
    }
    public String getStatus() {
        return Status;
    }
    public String getDay() {
        return day;
    }
    public String getShift() {
        return shift;
    }

    public String getDayString() {

        String result = "";

        switch (day){
            case "1" -> result = "Sunday";
            case "2" -> result = "Monday";
            case "3" -> result = "Tuesday";
            case "4" -> result = "Wednesday";
            case "5" -> result = "Thursday";
        }
        return result;
    }
    public String getShiftString() {
        String result = "";

        switch (shift){
            case "1" -> result = "08:00";
            case "2" -> result = "16:00";
        }
        return result;
    }




    /**
     * Setter id
     */
    public void setId(int id) {
        this.id = id;
    }
    public void setMax_weight(double max_weight) {
        this.max_weight = max_weight;
    }
    public void setStatus(String status) {
        Status = status;
    }
    /**
     * Setter date
     */
    public void setDay(String day) {
        this.day = day;
    }
    /**
     * Setter leaving_time
     */
    public void setShift(String shift) {
        this.shift = shift;
    }
    public void setTruck(Truck truck) {
        this.truck = truck;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }



    /**
     * @return false if the truck in Over weight, true if it f+good to go
     */
    public int WeightCheck() {
        if (targets.isEmpty())
            return 4;
        int result = 0;
        double count = truck.getNet_weight();
        for (Document d : targets) {
            Site new_site = d.getTarget();
            if (new_site.getType().equals("Supplier")) {
                count += d.getTotalWeight();
                if (count > max_weight + truck.getNet_weight()) {
                    max_weight = count - truck.getNet_weight();
                }
                if (count > truck.getMax_weight()) {
                    result = 2;
                }
            } else {
                count -= d.getTotalWeight();
            }
        }
        if (result == 2)
            return result;

        if (count != truck.getNet_weight())
            return 1;

        return result;
    }
    public void add_Document(Document document) {targets.add(document);}
    public void add_Item (JsonObject j, String s) {

        switch (s){
            case "Supplier": {

                String name = j.get("Name").getAsString();

                double weight = j.get("Weight").getAsDouble();
                int amount = j.get("Amount").getAsInt();

                Item new_item = new Item(name, weight);
                items.put(new_item, amount);

                try{
                    all_transport_items.get(new_item);
                    all_transport_items.put(new_item, all_transport_items.get(new_item) + amount);
                }
                catch (Exception e){
                    all_transport_items.put(new_item, amount);
                }
                break;
            }
            case "Store": {
                for (Map.Entry<Item, Integer> iter: all_transport_items.entrySet()){
                    if (iter.getKey().to_string().equals(j.get("Item").getAsString())) {
                        items.put(iter.getKey(), j.get("Amount").getAsInt());
                        iter.setValue(iter.getValue() - j.get("Amount").getAsInt());
                    }
                }
            }
        }
    }
    public void clear_Items(){
        items.clear();
    }
    public void remove_Document(int i){
        targets.remove(i);
    }


    /**
     * @return String represent of the Transport
     */
    public String getDetails() {
        StringBuilder new_s = new StringBuilder();
        String s = "Day: " + getDayString() + "\nNumber of Truck: " + this.truck.getLicence_number() + "\n";
        new_s.append(s);
        s = "Maximum possible loading weight: " +(this.truck.getMax_weight() - this.truck.getNet_weight()) + "\n";
        new_s.append(s);
        s = "Leaving time: " + getShiftString() + "\nDriver name: " + this.driver.getName() + "\n";
        new_s.append(s);
        s = "Address of the Source: " + this.source;
        new_s.append(s);
        new_s.append("\nTargets: \n");
        int count = 1;
        for (Document d : targets) {
            Site site = d.getTarget();
            if (site.getType().equals("Store")) {
                s = "        " + count++ + ". Dropped products at Store: " + site.getName() + "\n";
                new_s.append(s);
            } else {
                s = "        " + count++ + ". Collect products at Supplier: " + site.getName() + "\n";
                new_s.append(s);
            }
            s = "           - Address: " + site.getAddress() + "\n           " +
                    "- Contact name: " + site.getContact() + "\n           - Phone: " + site.getPhone() + "\n" +
                    "           - Shipping weight: " + d.getTotalWeight() + "\n";

            new_s.append(s);
            new_s.append("\n           - Items: \n");
            for (Map.Entry<Item, Integer> iter : d.getAllItems().entrySet()) {
                s = "                 Name: " + iter.getKey().getName() + ", Amount: " + iter.getValue() + "\n";
                new_s.append(s);
            }
            s = "\n\n";
            new_s.append(s);
        }
        return new_s.toString();
    }
}
