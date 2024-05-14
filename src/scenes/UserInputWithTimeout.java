package scenes;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class UserInputWithTimeout {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\nTime's up! Input process interrupted.");
                scanner.close(); // Close the scanner to release resources
                timer.cancel(); // Stop the timer
            }
        };

        timer.schedule(task, 5000); // Schedule the task to run after 5 seconds

        System.out.print("Enter your input: ");
        String userInput = scanner.nextLine();
        System.out.println("User input: " + userInput);

        scanner.close(); // Close the scanner after getting the input (if within 5 seconds)
        timer.cancel(); // Stop the timer
    }
}
