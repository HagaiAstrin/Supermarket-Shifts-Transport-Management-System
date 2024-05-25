package Domain;

import java.util.List;
import java.util.Map;

public class Shift {
    String ShiftManagerID; // ID of the shift manager
    dayTypeEnum day; // Type of day (e.g., weekday, weekend)
    ShiftType shiftType; // Type of shift (e.g., morning shift, evening shift)
    Map<JobTypeEnum, List<String>> employees; // Map of job types to lists of employee IDs

    // Constructor with parameters for initializing all attributes
    public Shift(String shiftManagerID, dayTypeEnum day, ShiftType shiftType, Map<JobTypeEnum, List<String>> employees) {
        ShiftManagerID = shiftManagerID;
        this.day = day;
        this.shiftType = shiftType;
        this.employees = employees;
    }

    // Overloaded constructor with default value for employees
    public Shift(String shiftManagerID, dayTypeEnum day, ShiftType shiftType) {
        this(shiftManagerID, day, shiftType, null); // Call the main constructor with default value for employees
    }

    // Getter method for employees map
    public Map<JobTypeEnum, List<String>> getEmployees() {
        return employees;
    }

    // Setter method for employees map
    public void setEmployees(Map<JobTypeEnum, List<String>> employees) {
        this.employees = employees;
    }
}
