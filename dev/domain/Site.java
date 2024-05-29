package domain;

public class Site {
    private String name;
    private String address;
    private String phone;
    private String contact;
    private String shipping_area;
    private String type;

    public Site(String name, String address, String phone, String contact, String shipping_area, String type) {
        this.type = type;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.contact = contact;
        this.shipping_area = shipping_area;
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String to_string(){
        return ("Address: " + address + ", Name: " + name + ", Shipping area: " + shipping_area);
    }

}
