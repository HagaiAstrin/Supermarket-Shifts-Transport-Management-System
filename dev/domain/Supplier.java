package domain;

public class Supplier extends Site{

    public Supplier(String name, String address, int phone, String contact, String shipping_area) {
        super(name, address, phone, contact, shipping_area);
    }

    @Override
    public String get_class() {
        return "Supplier";
    }
}
