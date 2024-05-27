package presentation;

import java.util.Scanner;

public class Solve_problem {

    public static void solve(){
        Scanner reader = new Scanner(System.in);
        System.out.println("There is 'Over weight' in the truck!");
        System.out.println("Please choose from the following options:");
        System.out.println("Change sites - '1'\nChange truck - '2'\nDrop sites - '3'\nDrop items - '4'");

        int answer = reader.nextInt();

        while (answer > 4 || answer < 1){
            System.out.println("Wrong input! try again..");
            System.out.println("Change sites - '1'\nChange truck - '2'\nDrop sites - '3'\nDrop items - '4'");
            answer = reader.nextInt();
        }
    }
}
