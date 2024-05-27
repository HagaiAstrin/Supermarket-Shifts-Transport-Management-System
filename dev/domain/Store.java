package domain;

public class Store extends Site{
//    private Map<Item, Integer> item_map = new HashMap<>();

    /**
     * @param name - the name of the Store
     * @param address - the address of the Store
     * @param phone - the phone of the contact
     * @param contact - the name of the contact
     * @param shipping_area - the shipping_area
//     * @param item_map - a map of the store. Key = items, Value = count of the missing Items
     */
//    add Map<Item, Integer> item_map
    public Store(String name, String address, int phone, String contact, String shipping_area) {
        super(name, address, phone, contact, shipping_area);
//        this.item_map = item_map;
    }

    /**
     * @return the map of the Store
     */
//    public Map<Item, Integer> getItem_map() {
//        return item_map;
//    }

    /**
     * @return true if the Store map is empty, false otherwise
     */
//    public boolean all_restored(){
//        return this.item_map.isEmpty();
//    }

    /**
     * remove all the items in the map which there counters equals 0
     */
//    public void dropped_items(){
//        for(Map.Entry<Item, Integer> it : this.item_map.entrySet()){
//            if(it.getValue() == 0){
//                this.item_map.remove(it.getKey());
//            }
//        }
//    }

    @Override
    public String get_class() {
        return "Store";
    }

}
