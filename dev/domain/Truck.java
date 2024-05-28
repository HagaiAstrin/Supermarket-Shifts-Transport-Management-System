package domain;

public class Truck {
    private String licence_number;
    private char licence_level;
    private double net_weight;
    private double max_weight;
    private boolean availability;

    public Truck(String id, char licence, double net_weight, double max_weight) {
        this.licence_number = id;
        this.licence_level = licence;
        this.net_weight = net_weight;
        this.max_weight = max_weight;
        this.availability = true;
    }

    public String getLicence_number() {
        return licence_number;
    }

    public char getLicence_level() {
        return licence_level;
    }

    public double getNet_weight() {
        return net_weight;
    }

    public double getMax_weight() {
        return max_weight;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String to_String(){
        return ("Licence Number: " + licence_number + ", Licence Level: "
                + licence_level + ", Max weight: " + max_weight + ".");
    }
}
