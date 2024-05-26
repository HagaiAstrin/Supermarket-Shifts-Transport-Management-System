package Domain;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Employee {
    String id;
    String name;
    JobTypeEnum jobType;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public JobTypeEnum getJobType() {
        return jobType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJobType(JobTypeEnum jobType) {
        this.jobType = jobType;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public void setGivenShifts(Map<Integer, List<Shift>> givenShifts) {
        this.givenShifts = givenShifts;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getBankID() {
        return bankID;
    }

    public Map<Integer, List<Shift>> getGivenShifts() {
        return givenShifts;
    }

    public Contract getContract() {
        return contract;
    }

    String bankID;
    Map<Integer, List<Shift>> givenShifts;
    Contract contract;

    public Employee(String id, String name, String bankID, int salary, int restDays, Date startDate, String workType) {
        this.jobType = JobTypeEnum.valueOf(workType.toUpperCase().replaceAll(" ", "_"));
        this.id = id;
        this.name = name;
        this.bankID = bankID;
        this.contract = new Contract(startDate, workType, salary, salary, restDays);
    }

    public String toString() {
        return "Person{Name='" + name +'}';
    }

    public void setSalary(int salary) {
        contract.setGlobalSalary(salary);
    }

    public void setRestDays(int restDays) {
        contract.setRestDays(restDays);
    }
}

