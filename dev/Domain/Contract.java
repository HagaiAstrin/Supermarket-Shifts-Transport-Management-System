package Domain;

import java.time.LocalDate;

public class Contract {
    LocalDate startDate;
    int salary;
    int restDays;

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setGlobalSalary(int salary) {
        this.salary = salary;
    }

    public int getRestDays() {
        return restDays;
    }

    public void setRestDays(int restDays) {
        this.restDays = restDays;
    }

    public Contract(LocalDate startDate, int salary, int restDays) {
        this.startDate = startDate;
        this.salary = salary;
        this.restDays = restDays;
    }
}
