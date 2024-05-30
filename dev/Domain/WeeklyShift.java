package Domain;

import java.util.HashMap;
import java.util.Map;

public class WeeklyShift {
    int weekID;
    Map<String, JobTypeEnum> currEmployees = new HashMap<>();
    JobWeeklyShift[] currWeeklyShift;

    public WeeklyShift(int weekID, Map<JobTypeEnum, JobWeeklyShift> currEmployees) {
        this.weekID = weekID;
        //this.currEmployees = currEmployees;
    }
}
