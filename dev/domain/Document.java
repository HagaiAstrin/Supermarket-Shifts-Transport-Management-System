package domain;

import java.util.HashMap;
import java.util.Map;

public class Document {
    private static int count = 1;
    private int id;

    private Site target;
    private Map<Item, Integer> item_map = new HashMap<>();

    /**
     * A constructor to Document
     * @param target - the destination
     */
    public Document(Site target) {
        this.id = count++;
        this.target = target;
    }

    /**
     *
     * @param it - the Item to put in the map
     * @param count - counter of the Item
     */
    public void add_Item(Item it, int count){
        item_map.put(it, count);
    }

    public Site getTarget() {
        return target;
    }

    /**
     * @return the weight of the Document
     */
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
