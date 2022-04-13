package com.calebjianhui.duke.ui;

import static com.calebjianhui.duke.ui.Messages.MESSAGE_LOGO;
import static com.calebjianhui.duke.ui.Messages.getWelcomeMessage;

import java.io.PrintStream;

/**
 * Handle all UI related matters in duke
 */
public class DukeUI {

    /**
     * Indentation used at the start of every line of reply
     * - To allow Duke to reply with a ~ and tab at the front
     */
    private static final String DUKE_MESSAGE_INDENTATION = "~\t";

    /**
     * Divider in underscore style
     */
    private static final String DIVIDER_UNDERSCORE = "____________________________________________________________\n";

    /**
     * Divider in dash style
     */
    private static final String DIVIDER_DASH = "------------------------------------------------------------\n";

    private final PrintStream out;

    public DukeUI() {
        this(System.out);
    }

    private DukeUI(PrintStream out) {
        this.out = out;
    }

    /**
     * Print the given message to the user
     * @param message Message to be print to User
     **/
    private void printToUser(String message) {
        out.print(message);
    }

    /**
     * Format and print system reply in a unique accent
     * @param message Message to be formatted and print to User
     **/
    public void formatDukeReply(String message) {
        String output = DIVIDER_UNDERSCORE;
        output += DUKE_MESSAGE_INDENTATION + message.replaceAll("\n", "\n" + DUKE_MESSAGE_INDENTATION);
        output += "\n" + DIVIDER_DASH;
        printToUser(output);
    }

    /**
     * Print welcome message
     * - Used at start of program
     *
     * @param savedSuccess Whether to show loading of file success
     **/
    public void printWelcomeMessage(boolean savedSuccess) {
        String output = DIVIDER_UNDERSCORE;
        output += MESSAGE_LOGO + "\n";
        output += "............................................................\n";
        printToUser(output);
        formatDukeReply(getWelcomeMessage(savedSuccess));
    }

    /**
     * Print ending message
     * - Used in program termination
     **/
    public void printEndingMessage() {
        formatDukeReply("Bye. Hope to see you again soon!");
    }

}
