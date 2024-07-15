package Domain.DomainTransport.Obejects;

public class TransportDocument {
    private String Status;
    private int ID;
    private String Details;
    private int Day;
    private int Shift;


    public TransportDocument(String s, String d, int id, int D, int S){
        this.Status = s;
        this.Details = d;
        this.ID = id;
        this.Day = D;
        this.Shift = S;
    }

    public String getStatus() {
        return Status;
    }
    public int getID() {
        return ID;
    }
    public String getDetails() {
        return Details;
    }
    public int getDay() {
        return Day;
    }
    public int getShift() {
        return Shift;
    }


    public void setStatus(String status) {
        Status = status;
    }


    public String to_String() {
        StringBuilder new_s = new StringBuilder();
        String s = "Transport number: " + ID;
        new_s.append(s);
        s = "\nStatus: " + Status + "\n";;
        new_s.append(s);
        s = Details;
        new_s.append(s);

        return new_s.toString();
    }
}
