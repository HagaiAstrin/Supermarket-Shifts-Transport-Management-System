package Test.TestEmployee;

import Domain.DomainEmployee.Employee;
import Domain.DomainEmployee.IO_Data;
import Domain.DomainEmployee.JobTypeEnum;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Test_Employees {

    @Test
    public void testEmployeeCreation() {
        IO_Data.branch = "Test_Data";
        // Test data
        String id = "123";
        String name = "John Doe";
        String bankID = "987654";
        int salary = 50000;
        int restDays = 10;
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        ArrayList<JobTypeEnum> jobType = new ArrayList<>(List.of(new JobTypeEnum[]{JobTypeEnum.CASHIER}));

        // Create Employee
        Employee employee = new Employee(id, name, bankID, salary, restDays, startDate, jobType);

        // Verify Employee properties
        assertEquals("123", employee.getId());
        assertEquals("John Doe", employee.getName());
        assertEquals("987654", employee.getBankID());
        assertEquals(salary, employee.getContract().getSalary());
        assertEquals(restDays, employee.getContract().getRestDays());
        assertEquals(startDate, employee.getContract().getStartDate());
    }

    @Test
    public void testEmployeeWithMultipleJobTypes() {
        IO_Data.branch = "Test_Data";
        // Test data
        String id = "2";
        String name = "Jane Smith";
        String bankID = "654321";
        int salary = 60000;
        int restDays = 12;
        LocalDate startDate = LocalDate.of(2022, 6, 15);
        String jobType1 = "CASHIER";
        String jobType2 = "SHIFT_MANAGER";
        ArrayList<JobTypeEnum> jobType = new ArrayList<>(List.of(new JobTypeEnum[]{JobTypeEnum.CASHIER, JobTypeEnum.SHIFT_MANAGER}));
        // Create Employee with multiple job types
        Employee employee = new Employee(id, name, bankID, salary, restDays, startDate, jobType);
        employee.addJobType(JobTypeEnum.SHIFT_MANAGER);

        // Verify Employee properties
        assertEquals("2", employee.getId());
        assertEquals("Jane Smith", employee.getName());
        assertEquals("654321", employee.getBankID());
        assertEquals(salary, employee.getContract().getSalary());
        assertEquals(restDays, employee.getContract().getRestDays());
        assertEquals(startDate, employee.getContract().getStartDate());
    }

    @Test
    public void testSettersAndGetters() {
        IO_Data.branch = "Test_Data";
        // Test data
        String id = "3";
        String name = "Alice Johnson";
        String bankID = "321987";
        int salary = 55000;
        int restDays = 14;
        LocalDate startDate = LocalDate.of(2021, 9, 30);
        ArrayList<JobTypeEnum> jobType = new ArrayList<>(List.of(new JobTypeEnum[]{JobTypeEnum.STOCK_KEEPER}));

        // Create Employee
        Employee employee = new Employee(id, name, bankID, salary, restDays, startDate, jobType);

        // Use setters to update values
        employee.setId("890");
        employee.setName("Alice Williams");
        employee.setBankID("987123");
        employee.setSalary(58000);
        employee.setRestDays(15);
        employee.addJobType(JobTypeEnum.DRIVER);

        // Verify updated properties
        assertEquals("890", employee.getId());
        assertEquals("Alice Williams", employee.getName());
        assertEquals("987123", employee.getBankID());
        assertEquals(58000, employee.getContract().getSalary());
        assertEquals(15, employee.getContract().getRestDays());
    }
}
