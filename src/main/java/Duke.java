import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // Constants
    final static String DUKE_MESSAGE_INDENTATION = "~\t";
    // Variables
    static ArrayList<Task> taskList = new ArrayList<>();

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
    // Commands
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
        taskList.add(new Task(task));
        printDukeReply("Added: " + task);
    }

    /**
     * List all the task in the task queue
     * **/
    public static void listTask() {
        if (taskList.isEmpty()) {
            printDukeReply("You have no pending task. Add one now?");
        } else {
            String allTask = "These are your current task:\n";
            for (int i = 0; i < taskList.size(); i++) {
                if (i != 0) {
                    allTask = allTask.concat("\n");
                }
                allTask = allTask.concat(String.valueOf(i+1)).concat(". [");
                Task current = taskList.get(i);
                allTask = allTask.concat(current.getDoneStatus() ? "X] " : " ] ");
                allTask = allTask.concat(current.getDescription());
            }
            printDukeReply(allTask);
        }
    }

    /**
     * Update a task status
     *
     * @param command Determine to mark task
     * **/
    public static void updateTaskStatus(String command) {
        if (taskList.isEmpty()) {
            printDukeReply("You do not have any ongoing task. Add one first?");
            return;
        }

        // Filter out command
        boolean isMark = command.startsWith("mark");
        command = command.replaceFirst(isMark ? "mark" : "unmark", "");
        // Process command
        // - Ensure that it is an integer position
        try {
            int index = Integer.parseInt(command.strip());
            index--;
            if (index < 0 || index >= taskList.size()) {
                printDukeReply("Task is not found. Please provide a valid index.");
                listTask();
                return;
            }
            Task selected = taskList.get(index);
            if (selected.getDoneStatus() == isMark) {
                printDukeReply("There are no changes to be made!");
                listTask();
                return;
            }
            selected.setDoneStatus(isMark);
            String reply = isMark ? "Nice! I've marked this task as done:\n" : "Ok, I've marked this task as not done yet:\n";
            reply = reply.concat(isMark ? "\t[X] ": "\t[ ] ").concat(selected.getDescription());
            printDukeReply(reply);
        } catch (NumberFormatException e) {
            printDukeReply("Please provide the index of the task that you wish to remove.");
        }

    }

    //================================================================================
    // Main
    //================================================================================

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
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                updateTaskStatus(command);
            } else {
                addToTaskList(command);
            }
        }
        // Ending Message
        printEndingMessage();
    }
}
