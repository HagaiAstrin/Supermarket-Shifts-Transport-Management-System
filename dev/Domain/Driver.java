package Domain;

import java.util.ArrayList;

public class Driver {
    private String Name;
    private int WorkerNumber;
    private String License;
    private String Password;
    private String truckLicenceNumber;
    private int transportID;
    private String Route;
    private String Status;



    /**
     * Constructor for Driver
     */
    public Driver(String name, String license, String password, String truckLicenceNumber,
                  int workerNumber, int TransportID, String route) {
        this.Name = name;
        this.WorkerNumber = workerNumber;
        this.License = license;
        this.Password = password;
        this.truckLicenceNumber = truckLicenceNumber;
        this.Status = "available";
        this.transportID = TransportID;
        this.Route = route;
    }

//    get methods:
    public String getName() {
        return Name;
    }
    public String getLicense() {
        return License;
    }
    public String getPassword() {
        return Password;
    }
    public Truck getTruck() {
        if (truckLicenceNumber.equals("000-00-000"))
            return null;
        return DataController.getTruck(truckLicenceNumber);
    }
    public TransportDocument getTransportDocument() {
        if (transportID == 0)
            return null;
        return DataController.getTransportDoc(String.valueOf(transportID));
    }
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
    public void setTruckLicenceNumber(String t) {
        this.truckLicenceNumber = t;
    }
    public void setTransportID(int tranID) {
        this.transportID = tranID;
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
    public void createRoute(ArrayList<Document> Targets) {

        StringBuilder new_s = new StringBuilder();

        if(Status.equals("On the road")){
            new_s.append("\nWe hope your trip goes well!\n");
            this.Route = new_s.toString();
            return;
        }

        new_s.append("\nYou got a Transportation list!\n\n");
        int count = 1;
        if(Targets == null) {
            this.Route = null;
            return;
        }
        for (Document d : Targets) {
            String s = count + " - " + d.to_string() + "\n";
            new_s.append(s);
            count++;
        }
        this.Route = new_s.toString();
    }
}
