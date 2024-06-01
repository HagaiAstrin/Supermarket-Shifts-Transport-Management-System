package Domain;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeeklyShift {
    static int weekID;
    static Map<JobTypeEnum, JobWeeklyShift> shiftByJob = new HashMap<>();

    public static void startWeek(int id) {
        weekID = id;

        for (JobTypeEnum JTE : JobTypeEnum.values()) {
            if (JTE != null) {
                JobWeeklyShift JWS = new JobWeeklyShift(JTE);
                shiftByJob.put(JTE, JWS);
            }
        }
    }

    static public void checkEmployeeForEachJobType(int amount) throws Exception {
        if (shiftByJob.isEmpty()) {
            throw new Exception("There is now Weekly Shift");
        }
        StringBuilder outputError = new StringBuilder();
        for (JobTypeEnum JTE : shiftByJob.keySet()) {
            JobWeeklyShift temp = shiftByJob.get(JTE);
            try {
                temp.checkFull(amount);
            }
            catch (Exception e) {
                outputError.append(e.getMessage()).append("\n");
            }
        }
        if (!outputError.isEmpty()) {
            throw new Exception(outputError.toString());
        }
    }
    static public String iterAllJobWeeklyShift(){
        StringBuilder output = new StringBuilder();
        for(int job=0; job < JobTypeEnum.values().length; job++){
            output.append(getJobWeeklyShift(job));
        }
        return output.toString();
    }

    static public String getJobWeeklyShift(int job) {
        StringBuilder output = new StringBuilder();

        if (job < 0 || job >= JobTypeEnum.values().length) {
            return "Wrong input";
        }
        JobWeeklyShift jws = getWeeklyShift(job);
        if (jws == null) {
            return "Wrong input, jws was null";
        }
        output.append(JobTypeEnum.values()[job]).append(":\n");
        for (int day=0; day<dayTypeEnum.values().length; day++) {

            output.append(dayTypeEnum.values()[day]).append("\n");
            for (int shift=0 ; shift<IO_Data.amount_shifts; shift++){
                output.append(ShiftType.values()[shift].toString()).append("\n");
                output.append(jws.getWorkingShiftString(day, shift)); // Adding the Shift
            }
            String line = "--------------------------------------------------------------\n";
            output.append(line);

        }
        return output.toString();
    }
    static protected boolean removeEmployee(int job, int day, int shift, int id){
        JobWeeklyShift jws = getWeeklyShift(job);
        // Removing Employee from Shift
        return jws.remove(day, shift, id);
    }
    static protected void setEmployeeHowCanWork(int job, int day, int shift, int id){
        JobWeeklyShift jws = getWeeklyShift(job);
        // Adding Employee to Shift
        jws.setWeeklyShifts(day, shift, id);

        // now we need to remove employee because he cant work in this shift
        // Schedules will be 2 in his chart
        IO_Data.currEmployees.get(id).WeekPreferences[day][shift] = "2";
    }

    static protected List<JsonObject> getEmployeeForShift(int job, int day, int shift) {
        return getWeeklyShift(job).getEmployeeArray(day, shift);
    }

    static protected JobWeeklyShift getWeeklyShift(int job){
        // Helper function to prevent code multiplication - giving a job and returning the Week calendar to the specific job
        JobTypeEnum[] jtpInWeeklyShift = JobTypeEnum.values();
        if (job >= jtpInWeeklyShift.length || job < 0) {
            throw new IllegalArgumentException("Invalid job index: " + job);
        }
        JobTypeEnum goToJob = jtpInWeeklyShift[job];
        JobWeeklyShift jwsInWeeklyShift = shiftByJob.get(goToJob);
        return jwsInWeeklyShift;
    }


    static public String getEmployeeIDForShift(int job) {
        StringBuilder output = new StringBuilder();
        if (!(job >= 0 && job < JobTypeEnum.values().length)) {
            throw new IllegalArgumentException("Invalid job index: " + job);
        }
        JobTypeEnum[] jobs = JobTypeEnum.values();
        JobTypeEnum currentJob = jobs[job];
        ArrayList<Employee> potentialEmp = shiftByJob.get(currentJob).PotentialEmployee;
        for (Employee e : potentialEmp) {
            output.append(e.getName()).append("\n");
        }
        return output.toString();
    }

    static public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        for (JobTypeEnum job : shiftByJob.keySet()) {
            jsonObject.add(job.toString(), shiftByJob.get(job).toJsonWeek());
        }
        return jsonObject;
    }
}


