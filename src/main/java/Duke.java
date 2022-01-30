import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // Constants
    final static String DUKE_MESSAGE_INDENTATION = "~\t";
    // Variables
    static ArrayList<String> taskList = new ArrayList<>();

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
        System.out.println(DUKE_MESSAGE_INDENTATION + message.replaceAll("\n", "\n" + DUKE_MESSAGE_INDENTATION));
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
        printDukeReply("Hello! I'm Duke\nWhat can I do for you?");
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

    /**
     * Add a task to the task list
     *
     * @param task Task to be added
     * **/
    public static void addToTaskList(String task) {
        taskList.add(task);
        printDukeReply("Added: " + task);
    }

    /**
     * List all the task in the task queue
     * **/
    public static void listTask() {
        if (taskList.isEmpty()) {
            printDukeReply("You have no pending task. Add one now?");
        } else {
            String allTask = "";
            for (int i = 0; i < taskList.size(); i++) {
                if (i != 0) {
                    allTask = allTask.concat("\n");
                }
                allTask = allTask.concat(String.valueOf(i+1)).concat(". ").concat(taskList.get(i));
            }
            printDukeReply(allTask);
        }
    }

    public static void main(String[] args) {
        String command = "";
        Scanner in = new Scanner(System.in);
        // Welcome Message
        printWelcomeMessage();
        while (true) {
            command = in.nextLine();
            if (checkTerminatingWord(command)) {
                break;
            } else if (command.equals("list")) {
                listTask();
            } else {
                addToTaskList(command);
            }
        }
        // Ending Message
        printEndingMessage();
    }
}
