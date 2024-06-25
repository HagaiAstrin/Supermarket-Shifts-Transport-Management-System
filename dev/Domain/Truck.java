package Domain;

public class Truck {
    private String licence_number;
    private String licence_level;
    private double net_weight;
    private double max_weight;
    private boolean availability;
    private boolean hold;


    /**
     * Constructor of Truck
     */
    public Truck(String id, String licence, double net_weight, double max_weight) {
        this.licence_number = id;
        this.licence_level = licence;
        this.net_weight = net_weight;
        this.max_weight = max_weight;
        this.availability = true;
        this.hold = false;
    }

    /**
     * Getter net_weight
     */
    public double getNet_weight() {
        return net_weight;
    }
    /**
     * Getter max_weight
     */
    public double getMax_weight() {
        return max_weight;
    }
    /**
     * Getter licence_level
     */
    public int getLicence_level() {
        switch (licence_level){
            case "A" -> {
                return  1;
            }
            case "B" -> {
                return  2;
            }
            case "C" -> {
                return  3;
            }
        }
        return 0;
    }
    /**
     * Getter licence_number
     */
    public String getLicence_number() {
        return licence_number;
    }

    public void setHold(boolean hold) {
        this.hold = hold;
    }
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }



    /**
     * Getter availability
     */
    public boolean isAvailability() {
        return availability;
    }
    /**
     * Getter hold
     */
    public boolean isHold() {
        return hold;
    }
    /**
     * @return String represent of the Truck
     */
    public String to_String(){
        return ("Licence Number: " + licence_number + ", Licence Level: "
                + licence_level + ", Max weight: " + max_weight + ".");
    }
}
