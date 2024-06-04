package domain;

import java.util.ArrayList;

public class Driver {
    private String Name;
    private int worker_num;
    private String License;
    private String Password;
    private boolean availability;
    private boolean hold;
    private Truck using_truck;
    private static int num = 10;
    private Transport tran;
    private ArrayList<Document> list;

    /**
     * Constructor for Driver
     */
    public Driver(String name, String license, String password) {
        this.Name = name;
        this.worker_num = num++;
        this.License = license;
        this.Password = password;
        this.availability = true;
        this.using_truck = null;
        this.hold = false;
        this.list = null;
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
        return Name;
    }
    /**
     * @return int represent of the Driver License
     */
    public int getLicense() {
        switch (License){
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
        return Password;
    }
    /**
     * @return the using Truck of the Driver
     */
    public Truck getUsing_truck() {
        return using_truck;
    }
    /**
     * Getter transport
     */
    public Transport getTran() {
        return tran;
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
        if(!availability) this.list = null;
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

    public StringBuilder getList() {
        StringBuilder new_s = new StringBuilder();
        int count = 1;
        if(list == null) {
            return null;
        }
        for (Document d : list) {
            String s = count + " - " + d.to_string() + "\n";
            new_s.append(s);
            count++;
        }

        return new_s;
    }

    public void setList(ArrayList<Document> list) {
        if(list != null)
            this.list = new ArrayList<>(list);
    }
    /**
     * @return true if the Driver availability for drive
     */
    public boolean isAvailability() {
        return availability;
    }
    /**
     * @return String representation of the Driver
     */
    public String to_String(){
        return ("Worker number: " + worker_num + ", Name:" + Name + ", Licence Level: " + License + ".");
    }
}
