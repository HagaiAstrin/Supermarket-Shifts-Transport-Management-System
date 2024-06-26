package Domain;

import java.util.HashMap;
import java.util.Map;

public class Document {
    private static int count = 1;
    private int id;
    private Site target;
    private Map<Item, Integer> item_map;
    private double doc_weight;


    /**
     * Constructor of Document
     */
    public Document(Site target, Map<Item, Integer> items) {
        this.id = count++;
        this.target = target;
        this.item_map = new HashMap<>(items);
        this.doc_weight = cul_weight();
    }

    /**
     * @return The destination of the Document
     */
    public Site getTarget() {
        return target;
    }
    /**
     * @return the weight of Document
     */
    public double getDoc_weight() {
        return doc_weight;
    }
    /**
     * @return the Item , count Map of the Document
     */
    public Map<Item, Integer> getItem_map() {
        return item_map;
    }
    /**
     * Calculate all the weight of Document
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
    /**
     * Dropped an Item from the Document
     * @param t - Item argument
     */
    public void drop_Item(Item t){
        item_map.remove(t);
        this.doc_weight = cul_weight();
    }
    /**
     * @return String that represent the Document
     */
    public String to_string(){
        return ( "ID: " + id + ", Target: " + target.getName() + ", Address:"
                + target.getAddress()  + ", Total weight: " + this.doc_weight + ".");
    }
}
