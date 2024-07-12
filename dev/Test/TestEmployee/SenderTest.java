package Test.TestEmployee;

import Presentation.PresentationEmployee.Sender;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class SenderTest {

    @Test
    public void testEmployeeToJson() {
        // Test data
        String id = "123";
        String name = "John Doe";
        String bankID = "987654";
        int salary = 50000;
        int restDays = 10;
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        String workType = "CASHIER";

        // Convert to JSON
        JsonObject json = Sender.EmployeeToJson(id, name, bankID, salary, restDays, startDate, workType);

        // Verify JSON properties
        assertEquals("123", json.get("id").getAsString());
        assertEquals("John Doe", json.get("name").getAsString());
        assertEquals("987654", json.get("bankID").getAsString());
        assertEquals(50000, json.get("salary").getAsInt());
        assertEquals(10, json.get("restDays").getAsInt());
        assertEquals("2023-01-01", json.get("startDate").getAsString());
        assertEquals("CASHIER", json.get("jobType").getAsString());
    }
}
