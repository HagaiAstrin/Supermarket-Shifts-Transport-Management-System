package presentation;
import java.util.concurrent.TimeUnit;

public class TruckAnimation {

    /**
     * Showing a truck drive ib the console when a Driver start a Transport
     */
    public static void print_leave() throws InterruptedException {
        System.out.println();
        String truck = "🚚"; // Original truck emoji
        int consoleWidth = 10; // Adjust this to your console width

        for (int i = 0; i < consoleWidth; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            System.out.print(truck);
            TimeUnit.MILLISECONDS.sleep(100); // Adjust the speed of the truck
        }
    }
}
