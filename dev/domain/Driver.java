package domain;

import com.google.gson.JsonObject;

public class Driver {
    private String name;
    private int id;
    private String license;
    private String password;
    private boolean availability;
    private boolean hold;
    private Truck using_truck;
    private static int num = 10;

    public Driver(String name, String license, String password) {
        this.name = name;
        this.id = num++;
        this.license = license;
        this.password = password;
        this.availability = true;
        this.using_truck = null;
        this.hold = false;
    }

    public String getName() {
        return name;
    }

    public String getLicense() {
        return license;
    }

    public String getPassword() {
        return password;
    }
    public Truck getUsing_truck() {
        return using_truck;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setUsing_truck(Truck using_truck) {
        this.using_truck = using_truck;
    }
}
