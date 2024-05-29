package presentation;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSV_reader {


    public class CsvReaderExample {
        public static void reader() {
            String csvFile = "path/to/your/file.csv";
            try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
                List<String[]> records = reader.readAll();
                for (String[] record : records) {
                    System.out.println("Record: " + String.join(", ", record));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
