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
    private Transport tran;

    /**
     * Constructor for Driver
     */
    public Driver(String name, String license, String password) {
        this.name = name;
        this.worker_num = num++;
        this.license = license;
        this.password = password;
        this.availability = true;
        this.using_truck = null;
        this.hold = false;
    }

    /**
     * @return true if the Manager choose the driver to drive, false otherwise
     */
    public boolean isHold() {
        return hold;
    }

    /**
     * @return the name of the Driver
     */
    public String getName() {
        return name;
    }

    /**
     * @return int represent of the Driver License
     */
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

    /**
     * @return the password of the Driver
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the using Truck of the Driver
     */
    public Truck getUsing_truck() {
        return using_truck;
    }

    /**
     * @return true if the Driver availability for drive
     */
    public boolean isAvailability() {
        return availability;
    }

    /**
     * Setter hold
     */
    public void setHold(boolean hold) {
        this.hold = hold;
    }

    /**
     * Setter availability
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    /**
     * Getter using_truck
     */
    public void setUsing_truck(Truck using_truck) {
        this.using_truck = using_truck;
    }

    /**
     * Setter transport
     */
    public void setTran(Transport tran) {
        this.tran = tran;
    }

    /**
     * Getter transport
     */
    public Transport getTran() {
        return tran;
    }

    /**
     * @return String representation of the Driver
     */
    public String to_String(){
        return ("Worker number: " + worker_num + ", Name:" + name + ", Licence Level: " + license + ".");
    }
}
