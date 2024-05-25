package Domain;

import java.util.List;
import java.util.Map;

public class Shift {
    String ShiftManagerID;
    dayTypeEnum day;
    ShiftType shiftType;
    Map<JobTypeEnum, List<String>> employees;

    public Map<JobTypeEnum, List<String>> getEmployees() {
        return employees;
    }

    public Shift(String shiftManagerID, dayTypeEnum day, ShiftType shiftType, Map<JobTypeEnum, List<String>> employees) {
        ShiftManagerID = shiftManagerID;
        this.day = day;
        this.shiftType = shiftType;
        this.employees = employees;
    }

    // Overloaded constructor with default value for employees
    public Shift(String shiftManagerID, dayTypeEnum day, ShiftType shiftType) {
        this(shiftManagerID, day, shiftType, null); // Call the main constructor with default value
    }

    public void setEmployees(Map<JobTypeEnum, List<String>> employees) {
        this.employees = employees;
    }
}
