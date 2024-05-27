package domain;

public class Supplier extends Site{
//    private Set<String> supplier_Set = new HashSet<>();

    /**
     * @param name - of the supplier
     * @param address - address
     * @param phone
     * @param contact
     * @param shipping_area
//     * @param supplier_Set
     */

//    add Set<String> supplier_Set
    public Supplier(String name, String address, int phone, String contact, String shipping_area) {
        super(name, address, phone, contact, shipping_area);
//        this.supplier_Set = supplier_Set;
    }
//    /**
//     * @return the set of the supplier
//     */
//    public Set<String> getSupplier_Set() {
//        return supplier_Set;
//    }

//    /**
//     * add a String to the set
//     * @param name - the name of the Item
//     */
//    public void add_Item(String name){
//        supplier_Set.add(name);
//    }

//    /**
//     * @param name - the name of the item
//     * @return true if the Item inside the set, false otherwise
//     */
//    public boolean contain(String name){
//        return supplier_Set.contains(name);
//    }

    @Override
    public String get_class() {
        return "Supplier";
    }
}
