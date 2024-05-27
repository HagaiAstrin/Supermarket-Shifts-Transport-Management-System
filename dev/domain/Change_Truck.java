package domain;

import java.util.ArrayList;

public class Change_Truck implements ITruck_Solution {
    private ArrayList<Truck> lst_tr;
    private Truck new_truck;
    @Override
    public void Solution(Transport tran){
        lst_tr = getLst_tr(tran);
        /////////////////////////
        tran.set_Truck(new_truck);
    }
    public ArrayList<Truck> getLst_tr(Transport tran) {
        ArrayList<Truck> lst = new ArrayList<>();
        for(Truck tr : DataStructManager.trucks){
            if(tr.isAvailability() && tr.getMax_weight() >=
                    (tran.get_transport_Max_weight() - tran.getTr().getNet_weight() + tr.getNet_weight())){
                lst.add(tr);
            }
        }
        return lst;
    }
}
