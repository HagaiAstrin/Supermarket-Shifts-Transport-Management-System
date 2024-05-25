package Domain;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Employee {
    String id;
    String name;
    JobTypeEnum jobType;
    String bankID;
    Map<Integer, List<Shift>> givenShifts;
    Contract contract;

    public Employee(String id, String name, String bankID, int salary, int restDays, Date startDate, String workType) {
        this.id = id;
        this.name = name;
        this.bankID = bankID;
        this.contract = new Contract(startDate, workType, salary, salary, restDays);
    }
}

