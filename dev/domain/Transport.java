package domain;
import java.util.ArrayList;
public class Transport {
    private String date;
    private String out_time;
    private Truck tr;
    private Driver dr;
    private String source;
    private ArrayList<Document> targets = null;
    private double max_weight;

    public Transport(String date, String out_time, Truck tr, Driver dr, String source) {
        this.date = date;
        this.out_time = out_time;
        this.tr = tr;
        this.dr = dr;
        this.source = source;
        this.max_weight = tr.getNet_weight();
    }

    /**
     * @param dc = a Document
     */
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

    /**
     * @return true if the Truck can make the Transport, false otherwise
     */
    public boolean is_Weight_Good() {
        boolean bool = true;
        double count = this.tr.getNet_weight();
        for (Document d : targets) {
            Site new_site = d.getTarget();
            if (new_site instanceof Supplier) {
                count += d.cul_weight();
                if(count > this.max_weight){
                    this.max_weight = count;
                }
                if (count > this.tr.getMax_weight()) {
                    bool = false;
                }
            } else {
                count -= d.cul_weight();
            }
        }
        return bool;
    }

    public void set_Truck(Truck truck) {
        this.tr = truck;
    }

    public void set_Driver(Driver dr) {
        this.dr = dr;
    }


    public double get_transport_Max_weight() {
        return this.max_weight;
    }

    public Truck getTr() {
        return tr;
    }

    public void add_document(Document d) {
        targets.add(d);
    }

    public void remove_document(Document d) {
        targets.remove(d);
    }
}
