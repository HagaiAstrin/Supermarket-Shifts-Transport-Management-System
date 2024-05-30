package Domain;
import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Employee {
    String id;
    String name;
    ArrayList<JobTypeEnum> jobType;
    String bankID;
    Map<Integer, List<Shift>> givenShifts;
    Contract contract;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<JobTypeEnum> getJobType() {
        return jobType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addJobType(JobTypeEnum jobType) {
        this.jobType.add(jobType);
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

    public Employee(String id, String name, String bankID, int salary, int restDays, LocalDate startDate, String workType) {
        // TODO: Fine-tune this approach
        this.jobType = new ArrayList<>();
        this.jobType.add(JobTypeEnum.valueOf(workType.toUpperCase().replaceAll(" ", "_")));
        // ------------------------
        this.id = id;
        this.name = name;
        this.bankID = bankID;
        this.contract = new Contract(startDate, salary, restDays);
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

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("jobType", jobType.toString());
        jsonObject.addProperty("bankID", bankID);
        // TODO : Check real toString() representation
        jsonObject.addProperty("restDays", contract.getRestDays());
        jsonObject.addProperty("salary", contract.getSalary());
        jsonObject.addProperty("startDate", contract.getStartDate().toString());
        // Add other fields as necessary
        return jsonObject;
    }

    public static Employee JsonToEmployee(JsonObject j){
        return new Employee(j.get("id").getAsString(),
                j.get("name").getAsString(),
                j.get("bankID").getAsString(),
                Integer.parseInt(j.get("salary").getAsString()),
                Integer.parseInt(j.get("restDays").getAsString()),
                IO_Data.StringToDate(j.get("startDate").getAsString()),
                j.get("jobType").getAsString());
    }
}

