package Domain;

import java.util.Date;

public class Contract {
    Date startDate;
    String workType;
    int globalSalary;
    int hourlySalary;
    int restDays;

    public Contract(Date startDate, String workType, int globalSalary, int hourlySalary, int restDays) {
        this.startDate = startDate;
        this.workType = workType;
        this.globalSalary = globalSalary;
        this.hourlySalary = hourlySalary;
        this.restDays = restDays;
    }
}
