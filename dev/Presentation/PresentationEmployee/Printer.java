package Presentation.PresentationEmployee;

import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

public class Printer {
    public static void PrintAllEmployees(List<JsonObject> employees){
        String str = "Employees List:\n";
        for(int i = 0; i < employees.size(); i++){
            str += JsonToEmployee(employees.get(i));
        }

        System.out.println(str);
    }

    public static String JsonToEmployee(JsonObject employee){
        String str = "";

        str += "\n---- ID: ";
        str += employee.get("id").getAsString();

        str += "\nName: " + employee.get("name").getAsString() + "\n";
        str += "Job Type: " + employee.get("jobType").getAsString() + "\n";

        return str;
    }

    /**
     * Used to update the preferences of a user.
     */
    public static String[][] convertMatrix(String[][] matrix) {
        String[][] friendlyMatrix = new String[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("1")) {
                    friendlyMatrix[i][j] = "x";
                } else if (matrix[i][j].equals("0")) {
                    friendlyMatrix[i][j] = " ";
                } else {
                    friendlyMatrix[i][j] = matrix[i][j];
                }
            }
        }
        return friendlyMatrix;
    }

    public static void PrintPreferences(String[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Calculate the maximum width of each column
        int[] maxColWidths = new int[cols];
        for (int j = 0; j < cols; j++) {
            int maxWidth = 0;
            for (int i = 0; i < rows; i++) {
                if (matrix[i][j].length() > maxWidth) {
                    maxWidth = matrix[i][j].length();
                }
            }
            maxColWidths[j] = maxWidth;
        }

        // Print the table with aligned columns
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("+");
                char[] line = new char[maxColWidths[j] + 2];
                Arrays.fill(line, '-');
                System.out.print(new String(line));
            }
            System.out.println("+");
            for (int j = 0; j < cols; j++) {
                System.out.print("| ");
                System.out.print(padRight(matrix[i][j], maxColWidths[j]));
                System.out.print(" ");
            }
            System.out.println("|");
        }

        // Print the bottom line
        for (int j = 0; j < cols; j++) {
            System.out.print("+");
            char[] line = new char[maxColWidths[j] + 2];
            Arrays.fill(line, '-');
            System.out.print(new String(line));
        }
        System.out.println("+");
    }

    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}
