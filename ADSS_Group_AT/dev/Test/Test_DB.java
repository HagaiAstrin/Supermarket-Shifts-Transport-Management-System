package Test;

import Domain.Controller.DataController;
import Domain.Employee;
import Domain.IO_Data;
import Domain.JobTypeEnum;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class Test_DB {
    @Test
    public void testSizeOfEmployees() throws IOException {
        IO_Data.SetBranchName("Test_Data");
        IO_Data.ImportEmployees();
        List<Employee> employees = IO_Data.GetCurrEmployees();
        assertEquals(21, employees.size());
    }
    @Test
    public void testImportEmployees() throws IOException {
        IO_Data.SetBranchName("Test_Data");
        IO_Data.ImportEmployees();
        List<Employee> employees = IO_Data.GetCurrEmployees();

        assertNotNull(employees);
        assertEquals(21, employees.size());

        Employee employee1 = employees.get(0);
        assertEquals("hagai", employee1.getName());
        assertEquals("1", employee1.getId());

        Employee employee2 = employees.get(1);
        assertEquals("asaf", employee2.getName());
        assertEquals("2", employee2.getId());

        Employee employee3 = employees.get(2);
        assertEquals("shira", employee3.getName());
        assertEquals("3", employee3.getId());
    }

    @Test
    public void testImportPreferences() throws IOException {
        String[][] preferences1 = {{"0","1","1","1","1"},
                                   {"1","1","0","1","1"}};

        String[][] preferences2 = {{"1","1","1","1","0"},
                                   {"1","0","1","1","1"}};

        IO_Data.SetBranchName("Test_Data");

        String[][] out1 = IO_Data.ImportEmployeePreferences("1");
        String[][] out2 = IO_Data.ImportEmployeePreferences("2");
        assertEquals(out1, preferences1);
        assertEquals(out2, preferences2);
    }

    @Test
    public void testAdminPassword() throws IOException {
        IO_Data.SetBranchName("Test_Data");

        String pass1 = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
        boolean flag1 = DataController.Authenticate("hagai", pass1, "1", "1");

        assertTrue(flag1);
    }

    @Test
    public void testUserPassword() throws IOException {
        IO_Data.SetBranchName("Test_Data");

        String pass1 = "4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a";
        boolean flag1 = DataController.Authenticate("ramzi", pass1, "4", "4");

        assertTrue(flag1);
    }

    @Test
    public void testAddUserToDB() throws Exception {
        IO_Data.SetBranchName("Test_Data");
        IO_Data.ImportEmployees();

        String id = "21";
        String name = "John Doe";
        String bankID = "987654";
        int salary = 50000;
        int restDays = 10;
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        ArrayList<JobTypeEnum> jobType = new ArrayList<>(List.of(new JobTypeEnum[]{JobTypeEnum.CASHIER}));

        // Create Employee
        Employee employee = new Employee(id, name, bankID, salary, restDays, startDate, jobType);
        DataController.addEmployee(employee.toJson());
        String s = DataController.createPreferencesTable(employee.toJson());

        assertNotNull(DataController.importEmployeePreferences("21"));
    }

    @Test
    public void testDeleteUserFromDB() throws IOException {
        String[][] preferences = {{null,null,null,null,null},
                                  {null,null,null,null,null}};
        IO_Data.SetBranchName("Test_Data");
        IO_Data.ImportEmployees();

        String id = "21";
        DataController.removeEmployee(id);
        DataController.removeEmployeePreferences(id);

        assertEquals(preferences,DataController.importEmployeePreferences("21"));
    }

    @Test
    public void testAllPreferences() throws IOException {
        String[][] preferences = {{null,null,null,null,null},
                                  {null,null,null,null,null}};
        IO_Data.SetBranchName("Test_Data");
        IO_Data.ImportEmployees();

        List<Employee> employees = IO_Data.GetCurrEmployees();
        for (Employee employee : employees) {
            assertNotEquals(preferences, DataController.importEmployeePreferences(employee.getId()));
        }
    }

    @Test
    public void testEditInPreferences() throws IOException {
        IO_Data.SetBranchName("Test_Data");

        String[][] preferences = {{"0","1","1","1","1"},
                                  {"1","1","0","1","1"}};
        String[][] preferences1 = {{"0","1","0","0","0"},
                                   {"1","1","0","1","1"}};

        String[][] out = IO_Data.ImportEmployeePreferences("1");
        assertEquals(preferences,out);

        DataController.updatePreferencesToDB(preferences1, "1");

        String[][] out1 = IO_Data.ImportEmployeePreferences("1");
        assertEquals(preferences1,out1);

        DataController.updatePreferencesToDB(preferences, "1");
    }

    @Test
    public void testEditInEmployee() throws IOException {
        IO_Data.SetBranchName("Test_Data");
        IO_Data.ImportEmployees();
        List<Employee> employees = IO_Data.GetCurrEmployees();

        assertEquals(employees.get(0).getBankID(),  "1234");

        DataController.updateEmployeeField("1", "bankID", "8888");
        employees = IO_Data.GetCurrEmployees();
        assertEquals(employees.get(0).getBankID(), "8888");

        DataController.updateEmployeeField("1", "bankID", "1234");
    }
}
