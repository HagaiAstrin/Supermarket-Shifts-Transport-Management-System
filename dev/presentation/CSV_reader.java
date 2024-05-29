package presentation;

import com.google.gson.JsonObject;
import controller.Transportation_manager_controller;
import java.io.BufferedReader;
import java.io.FileReader;

public class CSV_reader {
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
                        case 1 -> Transportation_manager_controller.add_driver(j);
                        case 2 -> Transportation_manager_controller.add_site(j);
                        case 3 -> Transportation_manager_controller.add_truck(j);
                    }
                }
            }
            catch (Exception e){
                System.out.println("File not found "+ choose);
            }
        }
}
