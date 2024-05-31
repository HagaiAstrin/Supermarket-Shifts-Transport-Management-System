package domain;

import java.util.HashMap;
import java.util.Map;

public class Document {
    private static int count = 1;
    private int id;
    private Site target;
    private Map<Item, Integer> item_map;
    private double doc_weight;

    public Document(Site target, Map<Item, Integer> items) {
        this.id = count++;
        this.target = target;
        this.item_map = new HashMap<>(items);
        this.doc_weight = cul_weight();
    }

    public Site getTarget() {
        return target;
    }

    public double cul_weight(){
        double count = 0.0;
        for (Map.Entry<Item, Integer> iter : item_map.entrySet()){
            Item t = iter.getKey();
            int counter_item = iter.getValue();
            count+=(t.getWeight() * counter_item);
        }
        return count;
    }

    public double getDoc_weight() {
        return doc_weight;
    }

    public Map<Item, Integer> getItem_map() {
        return item_map;
    }

    public String to_string(){
        return ( "ID: " + id + ", Target: " + target.getName() + ", Total weight: " + this.doc_weight + ".");
    }

    public String item_String(Item t){
        return ( t.to_string() + ", Count : "+ item_map.get(t) +", Total weight : "+item_map.get(t)*t.getWeight());
    }
}
