package Domain;

import java.time.LocalDate;
import java.util.Date;

public class Contract {
    LocalDate startDate;
    int salary;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setGlobalSalary(int salary) {
        this.salary = salary;
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

    public Contract(LocalDate startDate, int salary, int restDays) {
        this.startDate = startDate;
        this.salary = salary;
        this.restDays = restDays;
    }
}
