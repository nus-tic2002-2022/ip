package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Command line user interface
 */
public class Ui {

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Get user input from input stream
     * @return user input
     */
    public String getUserCommand() {
        out.print("Please enter your command: ");
        String fullLine = in.nextLine();

        while (shouldIgnore(fullLine)) {
            fullLine = in.nextLine();
        }

        return fullLine.trim();
    }

    /**
     * Check if user input is empty
     * @param userInput string to compare
     * @return true if empty
     */
    private boolean shouldIgnore(String userInput) {
        return userInput.trim().isEmpty();
    }

    /**
     * Print message
     * @param message information to show
     */
    public void show(String message) {
        out.println(message);
    }

    /**
     * Print Welcome message
     */
    public void showWelcomeMessage() {

        String logo = "             " + " ____        _\n"
                + "             " + "|  _ \\ _   _| | _____\n"
                + " Hello! I am " + "| | | | | | | |/ / _ \\\n"
                + "             " + "| |_| | |_| |   <  __/\n"
                + "             " + "|____/ \\__,_|_|\\_\\___|\n";
        out.println(logo);
        out.print("What's your name? ");
        out.println("Hello " + in.nextLine() + "!");
    }

}
