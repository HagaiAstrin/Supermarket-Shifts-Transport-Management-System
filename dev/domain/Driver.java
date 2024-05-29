package domain;

public class Driver {
    private String name;
    private int worker_num;
    private String license;
    private String password;
    private boolean availability;
    private boolean hold;
    private Truck using_truck;
    private static int num = 10;

    public Driver(String name, String license, String password) {
        this.name = name;
        this.worker_num = num++;
        this.license = license;
        this.password = password;
        this.availability = true;
        this.using_truck = null;
        this.hold = false;
    }

    public int getId() {
        return worker_num;
    }
    public boolean isHold() {
        return hold;
    }
    public String getName() {
        return name;
    }
    public int getLicense() {
        switch (license){
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
    public String getPassword() {
        return password;
    }
    public Truck getUsing_truck() {
        return using_truck;
    }
    public boolean isAvailability() {
        return availability;
    }
    public void setHold(boolean hold) {
        this.hold = hold;
    }
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    public void setUsing_truck(Truck using_truck) {
        this.using_truck = using_truck;
    }
    public String to_String(){
        return ("Worker number: " + worker_num + ", Name:" + name + ", Licence Level: " + license + ".");
    }
}
