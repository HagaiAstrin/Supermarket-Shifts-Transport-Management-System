package Domain.DomainTransport.Controllers;
import DAL.DALTransport.DriversDAO;
import DAL.DALTransport.TrucksDAO;
import Domain.DomainEmployee.JobTypeEnum;
import Domain.DomainTransport.Obejects.*;

import com.google.gson.JsonObject;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TransportationController {

    public static Transportation Transport;

    public static int getNumberId() throws SQLException {
        if ((DataController.getAmount("Transport")) == 0)
            return 1000;

        return (DataController.getAmount("Transport")) + 1000;
    }


    public static void AddItem(JsonObject j, String s){
        Transport.add_Item(j, s);
    }
    public static void AddDocument(JsonObject j) throws SQLException {

        String site = j.get("Site").getAsString();

        Site s = DataController.getSite(site);

        Map<Item, Integer> new_map = new HashMap<>(Transport.getItems());

        if (!new_map.isEmpty()) {
            Document d = new Document(s, new_map);

            Transport.clear_Items();
            Transport.add_Document(d);
        }
    }


    public static void createTransport(JsonObject j) throws SQLException {

        String source = j.get("Source").getAsString();
        String day = j.get("Day").getAsString();
        String shift = j.get("Shift").getAsString();

        Truck truck = DataController.getTruck(j.get("Truck").getAsString());
        Driver driver = DataController.getDriver(j.get("Driver").getAsString());

        Transport = new Transportation(truck, driver, source, day, shift);
    }
    public static int checkTransport() throws SQLException {

        int result = Transport.WeightCheck();

        if (result == 0) {

            Transport.setId(getNumberId() + 1);

            String DriverShifts [][] = DriversDAO.GET_DRIVER_PREFERENCES(Transport.getDriver().getDriverID());
            String TruckShifts [][] = TrucksDAO.GET_TRUCK_TABLE(Transport.getTruck().getLicence_number());

            JsonObject DriverJson = new JsonObject();
            JsonObject TruckJson = new JsonObject();

            int day = Integer.parseInt(Transport.getDay());
            int shift = Integer.parseInt(Transport.getShift());

            DriverShifts[shift-1][day-1] = "2";
            TruckShifts[shift-1][day-1] = "2";

            DriversDAO.UPDATE_DRIVER_PREFERENCES(DriverShifts, Transport.getDriver().getDriverID());
            TrucksDAO.UPDATE_TRUCK_TABLE(TruckShifts, Transport.getTruck().getLicence_number());

            for (Document d : Transport.getTargets()){
                if (d.getTarget().getType().equals("Store")){
                    Domain.DomainEmployee.Controller.DataController.SetDB(d.getTarget().getName());
                    Domain.DomainEmployee.Controller.DataController.InsertToTemplateTable(day - 1,
                            shift -1, String.valueOf(Transport.getDriver().getDriverID()), JobTypeEnum.DRIVER);
                }
            }

            Transport.getDriver().createRoute(Transport.getTargets());
            Transport.getDriver().setTruckLicenceNumber(Transport.getTruck().getLicence_number());
            Transport.getDriver().setTransportID(Transport.getId());

            DriverJson.addProperty("Driver ID", Transport.getDriver().getDriverID());
            DriverJson.addProperty("Status", Transport.getDriver().getStatus());
            DriverJson.addProperty("Route", Transport.getDriver().getRoute());
            DriverJson.addProperty("Transport ID", Transport.getId());
            DriverJson.addProperty("Truck Licence Number", Transport.getTruck().getLicence_number());

            TruckJson.addProperty("Licence number", Transport.getTruck().getLicence_number());
            TruckJson.addProperty("Status", Transport.getTruck().getStatus());

            DataController.updateDriver(DriverJson);
            DataController.updateTruck(TruckJson);

            TransportDocument t = new TransportDocument(Transport.getStatus(),
                                 Transport.getDetails(),Transport.getId(), day, shift);
            DataController.AddTransportDocument(t);
            Transport = null;
            return 0;
        }
        return result;
    }

    public static JsonObject chooseItems(){
        JsonObject j = new JsonObject();
        int count = 1;

        for (Map.Entry<Item, Integer> iter: Transport.getAll_transport_items().entrySet()){
            if (iter.getValue() != 0)
                j.addProperty(String.valueOf(count++),iter.getKey().to_string());
        }
        return j;
    }
    public static int amountOfItems(String s) {

        for (Map.Entry<Item, Integer> iter: Transport.getAll_transport_items().entrySet()){
            if (iter.getKey().to_string().equals(s))
                return iter.getValue();
        }
        return 0;
    }

}
