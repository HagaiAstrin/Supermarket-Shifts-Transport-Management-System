package domain;
import java.util.ArrayList;
public class Transport {
    private String date;
    private String out_time;
    private Truck tr;
    private Driver dr;
    private String source;
    private ArrayList<Document> targets = null;
    private double Exit_weight;

    public Transport(String date, String out_time, Truck tr, Driver dr, String source) {
        this.date = date;
        this.out_time = out_time;
        this.tr = tr;
        this.dr = dr;
        this.source = source;
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
        double count = this.tr.getNet_weight();
        for (Document d : targets) {
            Site new_site = d.getTarget();
            if (new_site instanceof Supplier) {
                count += d.cul_weight();
                if (count > this.tr.getMax_weight()) {
                    return false;
                }
            } else {
                count -= d.cul_weight();
            }
        }
        return true;
    }

    public void set_Truck(Truck truck) {
        this.tr = truck;
    }

    public void set_Driver(Driver dr) {
        this.dr = dr;
    }


    public double getExit_weight() {
        return 1.2;
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
