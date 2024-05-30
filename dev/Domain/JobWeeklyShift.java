package Domain;

import java.util.ArrayList;

public class JobWeeklyShift {
    private final JobTypeEnum JobName;
    private final Employee[][] WeeklyShifts;
    private ArrayList<Employee> PotentialEmployee;

    public JobWeeklyShift(JobTypeEnum jobName) {
        this.JobName = jobName;
        this.WeeklyShifts = new Employee[IO_Data.amount_days][IO_Data.amount_shifts]; // Initializes a 5x2 array with nulls
        this.PotentialEmployee = new ArrayList<>();

        for (Integer id : IO_Data.currEmployees.keySet()) {
            Employee e = IO_Data.currEmployees.get(id);
            ArrayList<JobTypeEnum>  JobTypeArray = e.getJobType();
            for (JobTypeEnum JTA : JobTypeArray){
                if(jobName.toString().equals(JTA.toString())){
                    PotentialEmployee.add(e);
                    break;
                }
            }
        }
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
        if (day > 0 && day < IO_Data.amount_days && shift > 0 && shift < IO_Data.amount_shifts) {
            WeeklyShifts[day][shift] = emp;
        }
        //TODO raise error
    }
}
