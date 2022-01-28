import java.util.Scanner;

public class Duke {
    // Constants
    final static String DUKE_MESSAGE_INDENTATION = "|\t~ ";

    //================================================================================
    // Printing
    //================================================================================

    /**
     * Prints a single divider line
     *
     * @param underscore Determine whether to display _ or -
     * **/
    public static void printDivider(boolean underscore) {
        if (underscore) {
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("------------------------------------------------------------");
        }
    }

    /**
     * Prints system reply in a special accent
     * - Duke will reply with a ~ and dividers at top and bottom
     *
     * @param message Message from Duke
     * **/
    public static void printDukeReply(String message) {
        printDivider(true);
        System.out.println(DUKE_MESSAGE_INDENTATION + message);
        printDivider(false);
    }

    /**
     * Print welcome message
     * - Used at start of program
     * **/
    public static void printWelcomeMessage() {
        printDivider(true);
        String logo = "\t\t\t ____        _        \n"
                + "\t\t\t|  _ \\ _   _| | _____ \n"
                + "\t\t\t| | | | | | | |/ / _ \\\n"
                + "\t\t\t| |_| | |_| |   <  __/\n"
                + "\t\t\t|____/ \\__,_|_|\\_\\___|\tA variant";
        System.out.println(logo);
        System.out.println("............................................................");
        printDivider(true);
        System.out.println(DUKE_MESSAGE_INDENTATION + "Hello! I'm Duke");
        System.out.println(DUKE_MESSAGE_INDENTATION + "What can I do for you?");
        printDivider(false);
    }

    /**
     * Print ending message
     * - Used in program termination
     * **/
    public static void printEndingMessage() {
        printDukeReply("Bye. Hope to see you again soon!");
    }

    //================================================================================
    // Main
    //================================================================================

    /**
     * Check for any terminating words
     *
     * @param input The given string to check
     * @return Boolean value to determine whether to terminate the program (e.g. true to terminate)
     * **/
    public static boolean checkTerminatingWord(String input) {
        return input.equals("bye");
    }

    public static void main(String[] args) {
        String command = "";
        Scanner in = new Scanner(System.in);
        // Welcome Message
        printWelcomeMessage();
        // Read input from user
        command = in.nextLine();
        while (!checkTerminatingWord(command)) {
            printDukeReply(command);
            command = in.nextLine();
        }
        // Ending Message
        printEndingMessage();
    }
}
