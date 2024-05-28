package domain;

import java.util.HashMap;
import java.util.Map;

public class Document {
    private static int count = 1;
    private int id;
    private Site target;
    private Map<Item, Integer> item_map = new HashMap<>();

    public Document(Site target, Map<Item, Integer> items) {
        this.id = count++;
        this.target = target;
        this.item_map = items;
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
}
