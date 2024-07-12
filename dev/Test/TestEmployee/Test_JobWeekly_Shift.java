package Test.TestEmployee;

import Domain.DomainEmployee.Employee;
import Domain.DomainEmployee.JobTypeEnum;
import Domain.DomainEmployee.JobWeeklyShift;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.*;

import Domain.DomainEmployee.IO_Data;

import static org.junit.Assert.*;

public class Test_JobWeekly_Shift {
    private JobWeeklyShift cashierShifts;
    private Employee employee1;
    private Employee employee2;

    @Before
    public void setUp() {
        IO_Data.branch = "Test_Data";
        LocalDate today = LocalDate.of(1999,2,2);
        cashierShifts = new JobWeeklyShift(JobTypeEnum.CASHIER);
        employee1 = new Employee("1", "Hagai", "123",
                500, 18,
                LocalDate.of(2000, 9, 11)
                , new ArrayList<JobTypeEnum>(JobTypeEnum.CASHIER.ordinal()));
        employee2 = new Employee("2", "Asaf", "123",
                500, 18,
                LocalDate.of(2000, 9, 11),
                new ArrayList<JobTypeEnum>(JobTypeEnum.CASHIER.ordinal()));

        Map<Integer, Employee> currEmployees = new HashMap<>();
        currEmployees.put(1, employee1);
        currEmployees.put(2, employee2);
        //IO_Data.currEmployees = currEmployees;
        IO_Data.SetCurrEmployees(currEmployees);
    }

    @Test
    public void testJobWeeklyShiftCreation() {
        assertEquals(JobTypeEnum.CASHIER.toString(), cashierShifts.getJobType());
        assertNotNull(cashierShifts.getWeeklyShifts());
    }

    @Test
    public void testAddEmployeeToShift() {
        cashierShifts.setWeeklyShifts(0, 0, Integer.parseInt(employee1.getId()));
        cashierShifts.setWeeklyShifts(0, 1, Integer.parseInt(employee2.getId()));

        assertEquals(1, cashierShifts.getWeeklyShifts()[0][0].size());
        assertEquals(1, cashierShifts.getWeeklyShifts()[1][0].size());
        assertEquals(employee1, cashierShifts.getWeeklyShifts()[0][0].get(0));
        assertEquals(employee2, cashierShifts.getWeeklyShifts()[1][0].get(0));
    }

    @Test
    public void testGetEmployeesForShift() {
        cashierShifts.setWeeklyShifts(0, 0, Integer.parseInt(employee1.getId()));
        cashierShifts.setWeeklyShifts(0, 0, Integer.parseInt(employee2.getId()));

        ArrayList<Employee>[][] employees = cashierShifts.getWeeklyShifts();
        assertEquals(2, employees[0][0].size());
        assertTrue(employees[0][0].contains(employee1));
        assertTrue(employees[0][0].contains(employee2));
    }

    @Test
    public void testAddEmployeeToInvalidShift() {
        try {
            cashierShifts.setWeeklyShifts(0, 5, Integer.parseInt(employee1.getId()));
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Invalid day or shift value: day=0, shift=5", e.getMessage());
        }

        try {
            cashierShifts.setWeeklyShifts(0, 2, Integer.parseInt(employee2.getId()));
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Invalid day or shift value: day=0, shift=2", e.getMessage());
        }
    }

    @Test
    public void testGetEmployeesForInvalidShift() {
        List<JsonObject> employees = cashierShifts.getEmployeeArray(5, 0);
        assertTrue(employees.isEmpty());

        employees = cashierShifts.getEmployeeArray(0, 2);
        assertTrue(employees.isEmpty());
    }

    @Test
    public void testSetWeeklyShifts() {
        cashierShifts.setWeeklyShifts(0, 0, 1);
        cashierShifts.setWeeklyShifts(0, 1, 2);

        assertEquals(1, cashierShifts.getWeeklyShifts()[0][0].size());
        assertEquals(1, cashierShifts.getWeeklyShifts()[1][0].size());
        assertEquals(employee1, cashierShifts.getWeeklyShifts()[0][0].get(0));
        assertEquals(employee2, cashierShifts.getWeeklyShifts()[1][0].get(0));
    }

    @Test
    public void testSetWeeklyShiftsInvalid() {
        try {
            cashierShifts.setWeeklyShifts(5, 0, 1);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Invalid day or shift value: day=5, shift=0", e.getMessage());
        }

        try {
            cashierShifts.setWeeklyShifts(0, 2, 2);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Invalid day or shift value: day=0, shift=2", e.getMessage());
        }
    }
}
