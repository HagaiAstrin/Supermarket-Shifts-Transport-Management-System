package Test.TestEmployee;

import Presentation.PresentationEmployee.Printer;
import com.google.gson.JsonObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PrinterTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void restoreSystemOutput() {
        System.setOut(originalOut);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    public void testPrintAllEmployees() {
        List<JsonObject> employees = new ArrayList<>();

        JsonObject employee1 = new JsonObject();
        employee1.addProperty("id", "1");
        employee1.addProperty("name", "John Doe");
        employee1.addProperty("jobType", "CASHIER");

        JsonObject employee2 = new JsonObject();
        employee2.addProperty("id", "2");
        employee2.addProperty("name", "Jane Smith");
        employee2.addProperty("jobType", "MANAGER");

        employees.add(employee1);
        employees.add(employee2);

        Printer.PrintAllEmployees(employees);

        String output = getOutput();
        assertTrue(output.contains("Employees List:"));
        assertTrue(output.contains("---- ID: 1"));
        assertTrue(output.contains("Name: John Doe"));
        assertTrue(output.contains("Job Type: CASHIER"));
        assertTrue(output.contains("---- ID: 2"));
        assertTrue(output.contains("Name: Jane Smith"));
        assertTrue(output.contains("Job Type: MANAGER"));
    }
    @Test
    public void testConvertMatrix() {
        String[][] matrix = {
                {"Sun", "Mon", "Tue", "Wed", "Thu"},
                {"1", "0", "1", "0", "1"},
                {"0", "0", "0", "1", "1"}
        };

        String[][] expectedMatrix = {
                {"Sun", "Mon", "Tue", "Wed", "Thu"},
                {"x", " ", "x", " ", "x"},
                {" ", " ", " ", "x", "x"}
        };

        String[][] result = Printer.convertMatrix(matrix);

        assertArrayEquals(expectedMatrix, result);
    }
}
