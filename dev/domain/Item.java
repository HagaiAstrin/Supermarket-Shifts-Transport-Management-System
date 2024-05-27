package domain;

public class Item {
    private String name;
    private double weight;

    public Item(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    /**
     * @return the name of the Item
     */
    public String getName() {
        return name;
    }

    /**
     * @return the Weight of the Item
     */
    public double getWeight() {
        return weight;
    }
}