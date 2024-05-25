package Domain;

import java.util.List;
import java.util.Map;

public abstract class Shift {
    String shiftManagerID;
    dayTypeEnum day;
    Map<JobTypeEnum, List<String>> employees;
}
