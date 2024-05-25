package Domain;

import java.util.Map;

public class WeeklyShift {
    int weekID;
    ShiftType[] morningShifts = new ShiftType[5];
    ShiftType[] nightShifts = new ShiftType[5];


    public WeeklyShift() {
        for (int i = 0; i < morningShifts.length; i++) {
            morningShifts[i] = null;
            nightShifts[i] = null;
        }
        weekID = 0; // TODO - read from a file
    }
}
