package domain;

public class Site {
    private String name;
    private String address;
    private String phone;
    private String contact;
    private String shipping_area;
    private String type;

    /**
     * Constructor of Transport
     */
    public Site(String name, String address, String phone, String contact, String shipping_area, String type) {
        this.type = type;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.contact = contact;
        this.shipping_area = shipping_area;
    }

    /**
     * Getter Name
     */
    public String getName() {
        return name;
    }
    /**
     * Getter address
     */
    public String getAddress() {
        return address;
    }
    /**
     * Getter phone
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Getter Contact
     */
    public String getContact() {
        return contact;
    }
    public String getShipping_area() {
        return shipping_area;
    }
    /**
     * Getter type
     */
    public String getType() {
        return type;
    }


    /**
     * @return String represent of the Site
     */
    public String to_string(){
        return ("Address: " + address + ", Name: " + name + ", Shipping area: " + shipping_area + ", Type: " + getType());
    }

}
