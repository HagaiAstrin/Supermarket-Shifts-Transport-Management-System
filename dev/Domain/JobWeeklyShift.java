package Domain;

public class JobWeeklyShift {
    private final JobTypeEnum JobName;
    private Employee[][] WeeklyShifts;
    int days = 5;
    int shifts = 2;

    public JobWeeklyShift(JobTypeEnum jobName) {
        this.JobName = jobName;
        this.WeeklyShifts = new Employee[days][shifts]; // Initializes a 5x2 array with nulls
    }

    public String getJobName() {
        return JobName.toString();
    }

    public JobTypeEnum getJobNameEnum() {
        return JobName;
    }

    public Employee[][] getWeeklyShifts() {
        return WeeklyShifts;
    }

    public void setWeeklyShifts(int day, int shift, Employee emp){
        if (day > 0 && day < days && shift > 0 && shift < shifts) {
            WeeklyShifts[day][shift] = emp;
        }

    }
}
