package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
public class Ui {

    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showByeMessage() {
        showUser(MESSAGE_BYE);
    }

    public String getUserCommand() {
        out.print("Please enter your command: ");
        String fullLine = in.nextLine();

        while (shouldIgnore(fullLine)) {
            fullLine = in.nextLine();
        }

        //showUser("Command received:" + fullLine);
        return fullLine;
    }

    private boolean shouldIgnore(String userInput) {
        return userInput.trim().isEmpty();
    }

    public void showUser(String... message) {
        for (String m : message) {
            out.println("\t" + m);
        }
    }

    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void show(String message) {
        out.println(message);
    }

    public void showLine() {
        out.println("\t----------------------------------------");
    }
}
