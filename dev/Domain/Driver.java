package Domain;

import java.util.ArrayList;

public class Driver {
    private String Name;
    private int DriverNumber;
    private String License;
    private String Password;
    private boolean availability;
    private boolean hold;
    private Truck Truck;
    private static int num = 10;
    private Transportation tran;
    private ArrayList<Document> documents;
    private String list;



    /**
     * Constructor for Driver
     */
    public Driver(String name, String license, String password) {
        this.Name = name;
        this.DriverNumber = num++;
        this.License = license;
        this.Password = password;
        this.availability = true;
        this.Truck = null;
        this.hold = false;
        this.documents = null;
    }

//    get methods:
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
        return Truck;
    }
    /**
     * Getter transport
     */
    public Transportation getTran() {
        return tran;
    }
    /**
     * Return the List of the driving schedule
     */
    public String getList() {
        return list;
    }


//    set methods:
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
        if(!availability) this.documents = null;
        this.availability = availability;
    }
    /**
     * Getter using_truck
     */
    public void setUsing_truck(Truck using_truck) {
        this.Truck = using_truck;
    }
    /**
     * Setter transport
     */
    public void setTran(Transportation tran) {
        this.tran = tran;
    }
    /**
     * Setter Driver list order
     */
    public void setDocuments(ArrayList<Document> list) {
        if(list != null)
            this.documents = new ArrayList<>(list);
    }
    public void setList() {

        StringBuilder new_s = new StringBuilder();

        if(!isAvailability() && !isHold()){
            new_s.append("\nWe hope your trip goes well!\n");
            this.list = new_s.toString();
            return;
        }

        new_s.append("\nYou got a Transportation list!\n\n");
        int count = 1;
        if(documents == null) {
            this.list = null;
            return;
        }
        for (Document d : documents) {
            String s = count + " - " + d.to_string() + "\n";
            new_s.append(s);
            count++;
        }
        this.list = new_s.toString();
    }


    //    checking methods:
    /**
     * @return true if the Driver availability for drive
     */
    public boolean isAvailability() {
        return availability;
    }
    /**
     * @return true if the Manager choose the driver to drive, false otherwise
     */
    public boolean isHold() {
        return hold;
    }


//    print method:
    /**
     * @return String representation of the Driver
     */
    public String to_String(){
        return ("Worker number: " + DriverNumber + ", Name:" + Name + ", Licence Level: " + License + ".");
    }
}
