package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeeklyShift {
    int weekID;
    Map<JobTypeEnum, JobWeeklyShift> ShiftByJob = new HashMap<>();

    public WeeklyShift(int weekID) {
        this.weekID = weekID;

        for (JobTypeEnum JTE : JobTypeEnum.values()) {
            if (JTE != null) {
                JobWeeklyShift JWS = new JobWeeklyShift(JTE);
                assert false;
                ShiftByJob.put(JTE, JWS);
            }
        }
    }
}
