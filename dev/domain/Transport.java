package domain;
import java.util.ArrayList;
public class Transport {
    private String date;
    private String leaving_time;
    private Truck truck;
    private Driver driver;
    private String source;
    private ArrayList<Document> targets;
    private double max_weight;

    public Transport(String date, String leaving_time, Truck truck, Driver driver, String source, ArrayList<Document> t) {
        this.date = date;
        this.leaving_time = leaving_time;
        this.truck = truck;
        this.driver = driver;
        this.targets = t;
        this.source = source;
    }


    public boolean Is_Over_Weight() {
        boolean bool = true;
        double count = truck.getNet_weight();
        for (Document d : targets) {
            Site new_site = d.getTarget();
            if (new_site.getType().equals("Supplier")) {
                count += d.getDoc_weight();
                if(count > max_weight + truck.getNet_weight()){
                    max_weight = count - truck.getNet_weight();
                }
                if (count > truck.getMax_weight()) {
                    bool = false;
                }
            } else {
                count -= d.getDoc_weight();
            }
        }
        return bool;
    }

    public void set_Truck(Truck truck) {
        this.truck = truck;
    }

    public void set_Driver(Driver dr) {
        this.driver = dr;
    }


    public double get_transport_Max_weight() {
        return this.max_weight;
    }

    public Truck getTr() {
        return truck;
    }

    public void add_document(Document d) {
        targets.add(d);
    }

    public void remove_document(Document d) {
        targets.remove(d);
    }

    public String to_String_tran(){
        StringBuilder new_s = new StringBuilder();
        String s = "Date : "+this.date+"\nNumber of Truck : "+this.truck.getLicence_number()+"\n";
        new_s.append(s);
        s = "Leaving time : "+this.leaving_time+"\nDriver name : "+this.driver.getName()+"\n";
        new_s.append(s);
        s = "Address of the Source: "+this.source;
        new_s.append(s);
        new_s.append("\nTargets : \n");
        int count = 1;
        for(Document d : targets){
            Site site = d.getTarget();
            s = "    "+count++ +". Name : "+site.getName()+"\n       Address : "+site.getAddress()+"\n       " +
                    "Contact name : "+site.getContact()+"\n       Phone : "+site.getPhone();
            new_s.append(s);
        }
        return new_s.toString();
    }
}
