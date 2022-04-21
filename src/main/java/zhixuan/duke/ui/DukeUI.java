package zhixuan.duke.ui;

import java.io.PrintStream;

import zhixuan.duke.common.Messages;

/**
 * Text UI of the application.
 */
public class DukeUI {

    private final PrintStream out;

    public DukeUI() {
        this(System.out);
    }

    public DukeUI(PrintStream out) {
        this.out = out;
    }

    /** Shows message(s) to the user */
    public void showToUser(String message) {
        out.println(message + "\n" + Messages.MESSAGE_LINE);
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     *
     * @param success true if file exists and loaded into application, else false
     */
    public void printWelcomeMessage(boolean success) {
        showToUser(Messages.MESSAGE_LOGO);
        showToUser(Messages.getWelcomeMessage(success));
    }

    /**
     * Generates and prints the exit message upon the end of the application.
     */
    public void printEndingMessage() {
        showToUser(Messages.MESSAGE_GOODBYE);
    }

}
