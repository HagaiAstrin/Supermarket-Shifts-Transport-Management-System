package Presentation;

import Domain.BuildObjectsController;
import com.google.gson.JsonObject;

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
                        case 1 -> BuildObjectsController.AddDriver(j);
                        case 2 -> BuildObjectsController.AddSite(j);
                        case 3 -> BuildObjectsController.AddTruck(j);
                    }
                }
            }
            catch (Exception e){
                System.out.println("File not found "+ choose);
            }
        }
}
