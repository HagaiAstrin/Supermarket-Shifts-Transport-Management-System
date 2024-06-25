package Presentation;

import com.google.gson.JsonObject;
import Domain.TransportationManagerController;
import java.io.BufferedReader;
import java.io.FileReader;

public class CSV_Reader {

    /**
     * Start the program - CSV reader
     * @param file - the file to read from
     * @param choose - file type :
     *               1 : Driver
     *               2 : Site
     *               3 : Truck
     */
        public static void reader(String file, int choose) {
            BufferedReader reader;
            String line;
            try{
                reader = new BufferedReader(new FileReader(file));

                line = reader.readLine();
                String[] first_line = line.split(",");
                while ((line = reader.readLine()) != null){
                    String[] row = line.split(",");
                    JsonObject j = new JsonObject();
                    for(int i = 0; i < first_line.length; i++) {
                        j.addProperty(first_line[i], row[i]);
                    }
                    switch (choose){
                        case 1 -> TransportationManagerController.add_driver(j);
                        case 2 -> TransportationManagerController.add_site(j);
                        case 3 -> TransportationManagerController.add_truck(j);
                    }
                }
            }
            catch (Exception e){
                System.out.println("File not found "+ choose);
            }
        }
}
