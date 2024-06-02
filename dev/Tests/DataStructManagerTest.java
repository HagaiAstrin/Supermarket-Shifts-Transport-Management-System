package Tests;

import com.google.gson.JsonObject;
import domain.DataStructManager;

import domain.Driver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataStructManagerTest {
    private JsonObject driver_1 = new JsonObject();
    private JsonObject driver_2 = new JsonObject();
    private JsonObject driver_3 = new JsonObject();

    private JsonObject truck_1 = new JsonObject();
    private JsonObject truck_2 = new JsonObject();
    private JsonObject truck_3 = new JsonObject();

    private JsonObject store_1 = new JsonObject();
    private JsonObject store_2 = new JsonObject();

    private JsonObject supplier_1 = new JsonObject();
    private JsonObject supplier_2 = new JsonObject();

    public DataStructManagerTest(){
        this.driver_1.addProperty("Name", "Omer");
        this.driver_1.addProperty("Licence", "A");
        this.driver_1.addProperty("Password", "12121");

        this.driver_2.addProperty("Name", "Segal");
        this.driver_2.addProperty("Licence", "B");
        this.driver_2.addProperty("Password", "23232");

        this.driver_3.addProperty("Name", "David");
        this.driver_3.addProperty("Licence", "C");
        this.driver_3.addProperty("Password", "45454");

        DataStructManager.add_driver(driver_1);
        DataStructManager.add_driver(driver_2);
        DataStructManager.add_driver(driver_3);

        this.truck_1.addProperty("Licence number", "11111111");
        this.truck_1.addProperty("Licence level", "A");
        this.truck_1.addProperty("Net weight", 2000);
        this.truck_1.addProperty("Max weight", 10000);

        this.truck_2.addProperty("Licence number", "22222222");
        this.truck_2.addProperty("Licence level", "B");
        this.truck_2.addProperty("Net weight", 3000);
        this.truck_2.addProperty("Max weight", 15000);

        this.truck_3.addProperty("Licence number", "33333333");
        this.truck_3.addProperty("Licence level", "C");
        this.truck_3.addProperty("Net weight", 6000);
        this.truck_3.addProperty("Max weight", 17500);

        DataStructManager.add_truck(this.truck_1);
        DataStructManager.add_truck(this.truck_2);
        DataStructManager.add_truck(this.truck_3);

        this.store_1.addProperty("Name", "Shupersal");
        this.store_1.addProperty("Address", "Uri tzvi");
        this.store_1.addProperty("Phone number", "03-9629128");
        this.store_1.addProperty("Contact", "Shlomi");
        this.store_1.addProperty("Shipping area", "Jerusalem");
        this.store_1.addProperty("Type", "Store");

        this.store_2.addProperty("Name", "Tiv Taam");
        this.store_2.addProperty("Address", "King Solomon 2");
        this.store_2.addProperty("Phone number", "03-3124567");
        this.store_2.addProperty("Contact", "Ido");
        this.store_2.addProperty("Shipping area", "Jerusalem");
        this.store_2.addProperty("Type", "Store");

        this.supplier_1.addProperty("Name", "Matzot");
        this.supplier_1.addProperty("Address", "Rotshild 1");
        this.supplier_1.addProperty("Phone number", "03-6125348");
        this.supplier_1.addProperty("Contact", "Itamar");
        this.supplier_1.addProperty("Shipping area", "Jerusalem");
        this.supplier_1.addProperty("Type", "Supplier");

        this.supplier_2.addProperty("Name", "Osem");
        this.supplier_2.addProperty("Address", "jerusalem 3");
        this.supplier_2.addProperty("Phone number", "03-7642318");
        this.supplier_2.addProperty("Contact", "Arad");
        this.supplier_2.addProperty("Shipping area", "Jerusalem");
        this.supplier_2.addProperty("Type", "Supplier");

        DataStructManager.add_Site(this.store_1);
        DataStructManager.add_Site(this.store_2);
        DataStructManager.add_Site(this.supplier_1);
        DataStructManager.add_Site(this.supplier_2);

    }
    @Test
    void add_check_driver_Test() {
        assertEquals("Omer", DataStructManager.check_driver(driver_1));
        assertEquals("Segal", DataStructManager.check_driver(driver_2));
        assertEquals("David", DataStructManager.check_driver(driver_3));

    }

    @Test
    void update_leaving_back_driver() {

    }

    @Test
    void add_Shipping_area() {
        DataStructManager.add_Shipping_area("East");
        DataStructManager.add_Shipping_area("North");
        assertTrue(DataStructManager.manager_Map.containsKey("Jerusalem"));
        assertTrue(DataStructManager.manager_Map.containsKey("East"));
        assertTrue(DataStructManager.manager_Map.containsKey("North"));
    }

    @Test
    void add_Site() {
        JsonObject j1 = new JsonObject();
        JsonObject j2 = new JsonObject();

        j1.addProperty("Name", "Spray");
        j1.addProperty("Address", "King Shaul 45");
        j1.addProperty("Phone number", "03-1236215");
        j1.addProperty("Contact", "Ido");
        j1.addProperty("Shipping area", "Jerusalem");
        j1.addProperty("Type", "Store");

        j2.addProperty("Name", "Coca Cola");
        j2.addProperty("Address", "Trumpeldor 1");
        j2.addProperty("Phone number", "03-6125348");
        j2.addProperty("Contact", "Naftali");
        j2.addProperty("Shipping area", "Jerusalem");
        j2.addProperty("Type", "Supplier");

        DataStructManager.add_Site(j1);
        DataStructManager.add_Site(j2);

        assertTrue(DataStructManager.manager_Map.get("Jerusalem").get("Store").containsKey("Spray"));
        assertTrue(DataStructManager.manager_Map.get("Jerusalem").get("Store").containsKey("Shupersal"));
        assertTrue(DataStructManager.manager_Map.get("Jerusalem").get("Store").containsKey("Tiv Taam"));

        assertTrue(DataStructManager.manager_Map.get("Jerusalem").get("Supplier").containsKey("Coca Cola"));
        assertTrue(DataStructManager.manager_Map.get("Jerusalem").get("Supplier").containsKey("Osem"));
        assertTrue(DataStructManager.manager_Map.get("Jerusalem").get("Supplier").containsKey("Matzot"));

        assertFalse(DataStructManager.manager_Map.get("Jerusalem").get("Store").containsKey("Spry"));


    }

//    @Test
//    void add_truck() {
//        JsonObject j1 = new JsonObject();
//        j1.addProperty("Licence number", "44444444");
//        j1.addProperty("Licence level", "B");
//        j1.addProperty("Net weight", 3000);
//        j1.addProperty("Max weight", 15000);
//
//        DataStructManager.add_truck(j1);
//        assertTrue(DataStructManager.trucks.contains());
//    }

    @Test
    void create_items_list() {
    }

    @Test
    void create_document() {
    }

    @Test
    void create_transportation() {
    }

    @Test
    void choose_truck() {
    }

    @Test
    void choose_driver() {
    }

    @Test
    void choose_area() {
    }

    @Test
    void choose_supplier() {
    }

    @Test
    void choose_store() {
    }

    @Test
    void all_transport() {
    }
}