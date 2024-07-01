package Domain.Obejects;

import java.util.HashMap;
import java.util.Map;

public class Document {
    private static int count = 1;
    private int id;
    private Site target;
    private Map<Item, Integer> AllItems;
    private double totalWeight;


    /**
     * Constructor of Document
     */
    public Document(Site target, Map<Item, Integer> items) {
        this.id = count++;
        this.target = target;
        this.AllItems = new HashMap<>(items);
        this.totalWeight = getWeight();
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
    public double getTotalWeight() {
        return totalWeight;
    }
    /**
     * @return the Item , count Map of the Document
     */
    public Map<Item, Integer> getAllItems() {
        return AllItems;
    }
    /**
     * Calculate all the weight of Document
     */
    public double getWeight(){
        double count = 0.0;
        for (Map.Entry<Item, Integer> iter : AllItems.entrySet()){
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
    public void dropItem(Item t){
        AllItems.remove(t);
        this.totalWeight = getWeight();
    }
    /**
     * @return String that represent the Document
     */
    public String to_string(){
        return ( "ID: " + id + ", Target: " + target.getName() + ", Address:"
                + target.getAddress()  + ", Total weight: " + this.totalWeight + ".");
    }
}
