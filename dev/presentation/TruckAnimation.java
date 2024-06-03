package presentation;
import java.util.concurrent.TimeUnit;

public class TruckAnimation {

    public static void print() throws InterruptedException {
        System.out.println();
        String truck = "🚚"; // Original truck emoji
        String flippedTruck = "\uD83D\uDE9A"; // Emoji for truck facing left (U+1F69A)
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
