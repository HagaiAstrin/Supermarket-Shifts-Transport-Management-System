package Domain;
import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.util.*;

public class Employee {
    String id;
    String name;
    ArrayList<JobTypeEnum> jobType;
    String bankID;
    Contract contract;
    String[][] WeekPreferences;

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
    } //TODO delte UML

    public void addJobType(JobTypeEnum jobType) {
        this.jobType.add(jobType);
    } //TODO delte UML

    public void setBankID(String bankID) {
        this.bankID = bankID;
    } //TODO delte UML



    public void setContract(Contract contract) {
        this.contract = contract;
    } //TODO delte UML

    public String getBankID() {
        return bankID;
    } //TODO delte UML



    public Contract getContract() {
        return contract;
    } //TODO delte UML

    public Employee(String id, String name, String bankID, int salary, int restDays, LocalDate startDate,  ArrayList<JobTypeEnum> jobTypes) {
        // TODO: Fine-tune this approach
        this.jobType = new ArrayList<>();
        this.jobType = jobTypes;// ------------------------
        this.id = id;
        this.name = name;
        this.bankID = bankID;
        this.contract = new Contract(startDate, salary, restDays);
        this.WeekPreferences = IO_Data.ImportEmployeePreferences(id);
    }

    public String toString() {
        return "Person{Name='" + name +'}';
    }

    public void setSalary(int salary) {
        contract.setGlobalSalary(salary);
    } //TODO delte UML

    public void setRestDays(int restDays) {
        contract.setRestDays(restDays);
    } //TODO delte UML

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", name);

        // Join job types into a single string separated by '/'
        String jobTypeString = String.join("/",
                jobType.stream()
                        .map(JobTypeEnum::toString)
                        .toArray(String[]::new)
        );
        jsonObject.addProperty("jobType", jobTypeString);
        jsonObject.addProperty("bankID", bankID);
        jsonObject.addProperty("restDays", contract.getRestDays());
        jsonObject.addProperty("salary", contract.getSalary());
        jsonObject.addProperty("startDate", contract.getStartDate().toString());
        // Add other fields as necessary
        return jsonObject;

    }

    public static Employee JsonToEmployee(JsonObject j) {
        // Parse job types from JSON
        String jobTypeString = j.get("jobType").getAsString();
        String[] jobTypeArray = jobTypeString.split("/");
        ArrayList<JobTypeEnum> jobTypes = new ArrayList<>();
        for (String jobType : jobTypeArray) {
            try {
                jobTypes.add(JobTypeEnum.valueOf(jobType.trim().toUpperCase().replace(" ", "_")));

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid job type in JSON: " + jobType);
            }
        }

        return new Employee(
                j.get("id").getAsString(),
                j.get("name").getAsString(),
                j.get("bankID").getAsString(),
                Integer.parseInt(j.get("salary").getAsString()),
                Integer.parseInt(j.get("restDays").getAsString()),
                IO_Data.StringToDate(j.get("startDate").getAsString()),
                jobTypes
        );
    }
}

