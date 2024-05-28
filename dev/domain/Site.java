package domain;

public abstract class Site {
    private String name;
    private String address;
    private int phone;
    private String contact;
    private String shipping_area;

    public Site(String name, String address, int phone, String contact, String shipping_area) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.contact = contact;
        this.shipping_area = shipping_area;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public String getContact() {
        return contact;
    }

    public String getShipping_area() {
        return shipping_area;
    }

    public abstract String get_class();
    public String to_string(){
        return ("Address: " + address + ", Name: " + name + ", Shipping area: " + shipping_area);
    }

}
