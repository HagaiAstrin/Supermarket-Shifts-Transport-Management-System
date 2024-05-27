package domain;

public class Store extends Site{

    /**
     * @param name - the name of the Store
     * @param address - the address of the Store
     * @param phone - the phone of the contact
     * @param contact - the name of the contact
     * @param shipping_area - the shipping_area
//     * @param item_map - a map of the store. Key = items, Value = count of the missing Items
     */
    public Store(String name, String address, int phone, String contact, String shipping_area) {
        super(name, address, phone, contact, shipping_area);
    }

    @Override
    public String get_class() {
        return "Store";
    }

}
