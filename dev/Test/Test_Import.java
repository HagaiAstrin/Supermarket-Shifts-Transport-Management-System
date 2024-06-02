package Test;

import Domain.Employee;
import Domain.IO_Data;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class Test_Import {
    private static final String TEST_CSV = "Test_Employees.csv";

    @Before
    public void setUp() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_CSV))) {
            writer.write("John Doe,001\n");
            writer.write("Jane Smith,002\n");
            writer.write("Alice Johnson,003\n");
        }
    }

    @Test
    public void testImportEmployees() throws IOException {
        IO_Data.branch = "Test_Data";
        IO_Data.ImportEmployees();
        List<Employee> employees = IO_Data.GetCurrEmployees();

        assertNotNull(employees);
        assertEquals(3, employees.size());

        Employee employee1 = employees.get(0);
        assertEquals("John Doe", employee1.getName());
        assertEquals("1", employee1.getId());

        Employee employee2 = employees.get(1);
        assertEquals("Jane Smith", employee2.getName());
        assertEquals("2", employee2.getId());

        Employee employee3 = employees.get(2);
        assertEquals("Alice Johnson", employee3.getName());
        assertEquals("3", employee3.getId());
    }

}
