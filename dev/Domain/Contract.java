package Domain;

import java.util.Date;

public class Contract {
    Date startDate;
    String workType;
    int globalSalary;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public int getGlobalSalary() {
        return globalSalary;
    }

    public void setGlobalSalary(int globalSalary) {
        this.globalSalary = globalSalary;
    }

    public int getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public int getRestDays() {
        return restDays;
    }

    public void setRestDays(int restDays) {
        this.restDays = restDays;
    }

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
