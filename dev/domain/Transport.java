package domain;
import java.util.ArrayList;
public class Transport {
    private String date;
    private String leaving_time;
    private Truck truck;
    private Driver driver;
    private String source;
    private ArrayList<Document> targets = null;
    private double max_weight;

    public Transport(String date, String leaving_time, Truck truck, Driver driver, String source, ArrayList<Document> t) {
        this.date = date;
        this.leaving_time = leaving_time;
        this.truck = truck;
        this.driver = driver;
        this.targets = t;
        this.source = source;
        this.max_weight = truck.getNet_weight();
    }

    public void add_target(Document dc) {
//        double weight = dc.cul_weight();
//        if(dc.getTarget() instanceof Store)
//            this.cur_weight-=weight;
//        else
//            this.cur_weight+=weight;
        targets.add(dc);
    }

    public void del_target(Document dc) {
//        double weight = dc.cul_weight();
//        if(dc.getTarget() instanceof Store)
//            this.cur_weight+=weight;
//        else
//            this.cur_weight-=weight;
        targets.remove(dc);
    }
    public boolean Is_Over_Weight() {
        boolean bool = true;
        double count = this.truck.getNet_weight();
        for (Document d : targets) {
            Site new_site = d.getTarget();
            if (new_site.getType().equals("Supplier")) {
                count += d.cul_weight();
                if(count > this.max_weight){
                    this.max_weight = count;
                }
                if (count > this.truck.getMax_weight()) {
                    bool = false;
                }
            } else {
                count -= d.cul_weight();
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
}
