package Domain.DomainEmployee;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeeklyShift {
    static Map<JobTypeEnum, JobWeeklyShift> shiftByJob = new HashMap<>();
    static private JobTypeEnum[] jobTypeArray  = JobTypeEnum.values();
    private static final String[] DAYS = {"Sun", "Mon", "Tue", "Wed", "Thu"};
    private static final String[] SHIFTS = {"Morning", "Evening"};

    public static void startWeek() {

        for (JobTypeEnum JTE : JobTypeEnum.values()) {
            if (JTE != null) {
                JobWeeklyShift JWS = new JobWeeklyShift(JTE);
                shiftByJob.put(JTE, JWS);
            }
        }
    }

//    public static void setByRole(int role, int day, int shift, int id) {
//        // {0, 1, 2, 3} -> {SHIFT_MANAGER, CASHIER, STOCK_KEEPER, DRIVER}
//
//    }

    public static Map<JobTypeEnum, JobWeeklyShift> GetShiftByJob() {
        return shiftByJob;
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
            } catch (Exception e) {
                outputError.append(e.getMessage()).append("\n");
            }
        }
        if (!outputError.isEmpty()) {
            throw new Exception(outputError.toString());
        }
    }

    static public String iterAllJobWeeklyShift() {
        StringBuilder output = new StringBuilder();
        for (int job = 0; job < JobTypeEnum.values().length; job++) {
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
        for (int day = 0; day < dayTypeEnum.values().length; day++) {

            output.append(dayTypeEnum.values()[day]).append("\n");
            for (int shift = 0; shift < IO_Data.amount_shifts; shift++) {
                output.append(ShiftType.values()[shift].toString()).append("\n");
                output.append(jws.getWorkingShiftString(day, shift)); // Adding the Shift
            }
            String line = "--------------------------------------------------------------\n";
            output.append(line);

        }
        return output.toString();
    }

    /**
     * Remove an employee from a shift.
     */
    static protected boolean removeEmployee(int job, int day, int shift, int id) {
        JobWeeklyShift jws = getWeeklyShift(job);
        // Removing Employee from Shift
        return jws.remove(day, shift, id);
    }

    static protected void setEmployeeHowCanWork(int job, int day, int shift, int id) {
        JobWeeklyShift jws = getWeeklyShift(job);
        // Adding Employee to Shift
        jws.setWeeklyShifts(day, shift, id);

        // now we need to remove employee because he cant work in this shift
        // Schedules will be 2 in his chart
        System.out.println("ID:" + id);
        IO_Data.currEmployees.get(id).WeekPreferences[shift][day] = "2";
    }

    static protected List<JsonObject> getEmployeeForShift(int job, int day, int shift) {
        return getWeeklyShift(job).getEmployeeArray(day, shift);
    }

    static protected JobWeeklyShift getWeeklyShift(int job) {
        // Helper function to prevent code multiplication - giving a job and returning the Week calendar to the specific job
        JobTypeEnum[] jtpInWeeklyShift = JobTypeEnum.values();
        if (job >= jtpInWeeklyShift.length || job < 0) {
            throw new IllegalArgumentException("Invalid job index: " + job);
        }
        JobTypeEnum goToJob = jtpInWeeklyShift[job];
        JobWeeklyShift jwsInWeeklyShift = shiftByJob.get(goToJob);
        return jwsInWeeklyShift;
    }

    private static String getEmployeesString(ArrayList<Employee> employees) {
        StringBuilder sb = new StringBuilder();
        for (Employee employee : employees) {
            if (!sb.isEmpty()) {
                sb.append(", ");
            }
            sb.append(employee.toString());
        }
        return sb.toString();
    }


    public static void setShiftedAllEmployees(List<List<List<String>>> lst) {
        List<List<List<List<String>>>> refactoredSchedule = new ArrayList<>();
        refactoredSchedule = refactorSchedule(lst);
        for (int job = 0; job < 4; job++) {
            for (int day = 0; day < 5; day++) {
                for (int shift = 0; shift < 2; shift++) {
                    List<String> ids = refactoredSchedule.get(job).get(shift).get(day);
                    for (String id : ids) {
                        if (!id.equals("")) {
                            setEmployeeHowCanWork(job, day, shift, Integer.parseInt(id));
                        }
                    }
                }
            }
        }
    }

    private static List<List<List<String>>> getbyjob(List<List<List<List<String>>>> lst, int i){
        //0 - Stock
        //1 - Shift Manager
        //2 - Cashier
        //3 - Driver
        return lst.get(i);
    }

    public static List<List<List<List<String>>>> refactorSchedule(List<List<List<String>>> schedule) {
        List<List<List<List<String>>>> refactoredSchedule = new ArrayList<>();
        //[[[1,2],[3],..]]] -> [[1,2],[3]],.....
        // Initialize the refactored structure with two lists (one for each shift)
        for (int i = 0; i < schedule.size(); i+=2) {
            refactoredSchedule.add(get_new_array(schedule,i));
        }

        return refactoredSchedule;
    }
    private static List<List<List<String>>> get_new_array(List<List<List<String>>> lst, int i){
        List<List<List<String>>> new_lst = new ArrayList<>();
        new_lst.add(lst.get(i));
        new_lst.add(lst.get(i+1));
        return new_lst;

    }
}


