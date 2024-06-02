package Domain;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class JobWeeklyShift {
    private final JobTypeEnum JobName;
    private ArrayList[][] WeeklyShifts;
    protected ArrayList<Employee> PotentialEmployee;


    public ArrayList<Employee>[][] getWeeklyShifts() {
        return this.WeeklyShifts;
    }

    public JobWeeklyShift(JobTypeEnum jobName) {
        this.JobName = jobName;
        WeeklyShifts = new ArrayList[IO_Data.amount_shifts][IO_Data.amount_days]; // Initializes a 5x2 array with nulls
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
    protected StringBuilder getWorkingShiftString(int day, int shift){
        StringBuilder output = new StringBuilder();
        ArrayList<Employee> emArray = WeeklyShifts[shift][day]; // getJobWeeklyShift checks day and shift input
        if (emArray == null){
            output.append("No Employee\n");
            return output;
        }

        for (Employee e : emArray){
            output.append(e.getName()).append("(id: ").append(e.getId()).append(")\n");
        }
        return output;
    }

    public void checkFull(int amount) throws Exception{
        for (int day = 0; day < IO_Data.amount_days; day++) {
            for (int shift = 0; shift < IO_Data.amount_shifts; shift++) {
                //There are no enough employee in the shift
                if (WeeklyShifts[shift][day] == null || WeeklyShifts[shift][day].size() != amount) {
                    throw new Exception("There are no enough employee in " + JobName.toString());  // TODO
                }
            }
        }
    }

    public List<JsonObject> getEmployeeArray(int day, int shift) {
        List<JsonObject> ja = new ArrayList<>();
        for (Employee e : PotentialEmployee){
            if (e.WeekPreferences[shift][day].equals("1")){
                ja.add(e.toJson());
            }
        }
        return ja;
    }

    public JsonObject toJsonWeek() { // TODO
        JsonObject jsonObject = new JsonObject();
        int maxLen = getMaxLen();
        StringBuilder output = new StringBuilder();
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        // Print days of the week as header
        for (String day : daysOfWeek) {
            output.append(String.format("%-" + maxLen + "s", day));
        }
        output.append("\n");

        // Iterate over shifts and build the table
        for (List<Employee>[] dailyShifts : WeeklyShifts) {
            StringBuilder shiftRow = new StringBuilder();
            for (List<Employee> shift : dailyShifts) {
                StringBuilder shiftCell = new StringBuilder();
                for (Employee employees : shift) {
//                    for (Employee employee : employees) {
//                        shiftCell.append(employee.getName()).append(" (").append(employee.getJobType()).append("), ");
//                    }
                }
                // Remove last comma and space
                if (!shiftCell.isEmpty()) {
                    shiftCell.setLength(shiftCell.length() - 2);
                }
                shiftRow.append(String.format("%-" + maxLen + "s", shiftCell.toString()));
            }
            output.append(shiftRow.toString()).append("\n");
        }

        jsonObject.addProperty("weekSchedule", output.toString());
        return jsonObject;
    }

    private int getMaxLen(){
        int i = 0;
        for (Employee e : IO_Data.currEmployees.values()){
            int tempLen = e.getName().length();
            if (tempLen > i){
                i = tempLen;
            }
        }
        return i;
    }

    public String getJobName() {
        return JobName.toString();
    } // TODO delete uml

    public JobTypeEnum getJobNameEnum() {
        return JobName;
    }


    public void setWeeklyShifts(int day, int shift, int id){
        // Validate day and shift
        if (day >= 0 && day < IO_Data.amount_days && shift >= 0 && shift < IO_Data.amount_shifts) {
            //Create Array In the slot
            ArrayList<Employee> specificShift;
            if (WeeklyShifts[shift][day] == null){
                specificShift = new ArrayList<>();
            }
            else{
                specificShift = WeeklyShifts[shift][day];
            }
            specificShift.add(IO_Data.currEmployees.get(id));
            WeeklyShifts[shift][day] = specificShift;
            return;
        }
        throw new RuntimeException("Invalid day or shift value: day=" + day + ", shift=" + shift);
    }

    protected boolean remove(int day, int shift, int id){
        if (!(day >= 0 && day < IO_Data.amount_days && shift >= 0 && shift < IO_Data.amount_shifts)){
            throw new RuntimeException("Invalid day or shift value: day=" + day + ", shift=" + shift);
        }
        ArrayList<Employee> specificShift = WeeklyShifts[shift][day];
        if (specificShift != null){
            for (Employee e : specificShift){
                if (e.getId().equals(id + "")){
                    specificShift.remove(e);
                    return true;
                }
            }
        }
        return false;
    }

    public String getJobType() {
        return JobName.toString();
    }

}
