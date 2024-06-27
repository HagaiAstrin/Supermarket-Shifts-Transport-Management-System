package Domain;

import java.util.ArrayList;

public class Driver {
    private String Name;
    private int WorkerNumber;
    private String License;
    private String Password;
    private String truck;
    private static int num = 10;
    private Transportation transport;
    private ArrayList<Document> documents;
    private String Route;
    private String Status;



    /**
     * Constructor for Driver
     */
    public Driver(String name, String license, String password, String truck) {
        this.Name = name;
        this.WorkerNumber = num++;
        this.License = license;
        this.Password = password;
        this.truck = truck;
        this.documents = null;
        this.Status = "available";
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
    public Truck getTruck() {
        if (truck.equals("000-00-000"))
            return null;
        for (Truck t : TransportationController.getAllTrucks()){
            if (t.getLicence_number().equals(truck))
                return t;
        }
        return null;
    }
    /**
     * Getter transport
     */
    public Transportation getTransport() {
        return transport;
    }
    /**
     * Return the List of the driving schedule
     */
    public String getRoute() {
        return Route;
    }
    public String getStatus() {
        return Status;
    }
    public int getWorkerNumber() {
        return WorkerNumber;
    }


    //    set methods:
    /**
     * Getter using_truck
     */
    public void setTruck(String t) {
        this.truck = t;
    }
    /**
     * Setter transport
     */
    public void setTransport(Transportation tran) {
        this.transport = tran;
    }
    /**
     * Setter Driver list order
     */
    public void setDocuments(ArrayList<Document> list) {
        if(list != null)
            this.documents = new ArrayList<>(list);
    }
    public void setRoute(String list) {
        this.Route = list;
    }
    public void setStatus(String status) {
        Status = status;
    }



//    print method:
    /**
     * @return String representation of the Driver
     */
    public String to_String(){
        return ("Name:" + Name + ", Licence Level: " + License + ".");
    }
    public void createRoute() {

        StringBuilder new_s = new StringBuilder();

        if(Status.equals("On the road")){
            new_s.append("\nWe hope your trip goes well!\n");
            this.Route = new_s.toString();
            return;
        }

        new_s.append("\nYou got a Transportation list!\n\n");
        int count = 1;
        if(documents == null) {
            this.Route = null;
            return;
        }
        for (Document d : documents) {
            String s = count + " - " + d.to_string() + "\n";
            new_s.append(s);
            count++;
        }
        this.Route = new_s.toString();
    }
}
