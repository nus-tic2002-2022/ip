package zhixuan.duke.common;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_GOODBYE = "Bye bye!";
    public static final String MESSAGE_LINE = "---------------------------------------";
    public static final String MESSAGE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String MESSAGE_WELCOME = "I'm Duke\n" + "What can I do for you?";
    public static final String MESSAGE_WELCOME_LOADED = "I'm Duke\n " + "I have loaded your saved tasks.\n "
        + "If you wish to load another file, please use the 'load' command!\n" + "What can I do for you today?";
    public static final String REPLY_LIST = "Here are the tasks in your list: \n";
    public static final String REPLY_ADD_TASK = "Roger. I will add this to your list: \n";
    public static final String REPLY_MARK_TASK = "Nice! I've marked this task as done: ";
    public static final String REPLY_UNMARK_TASK = "OK, I've marked this task as not done yet: ";
    public static final String REPLY_ALR_MARK_TASK = "Task is already marked as done: ";
    public static final String REPLY_ALR_UNMARK_TASK = "Task is already marked as not done: ";
    public static final String REPLY_DELETE_TASK = "Deleted this task: \n";
    public static final String REPLY_NO_TASK_FOUND = "No matching tasks found.";
    public static final String REPLY_TASK_FOUND = "Here are the matching tasks in your list: \n";
    public static final String REPLY_FILE_LOADED = "File is successfully loaded.";
    public static final String REPLY_DUEDATETIME_ADDED = "Added ";
    public static final String REPLY_DUEDATETIME_TO = " to the date.\n" + "The new date is: ";
    public static final String REPLY_CREATED_DIRECTORY = "I have created a new file directory, 'user-files'.\n" +
            "It will contain the list of tasks and load automatically upon boot!";

    /**
     * Getter for welcome message upon start of application
     *
     * @param successMessage boolean to display success message after loading saved file
     * @return welcome message string
     **/
    public static String getWelcomeMessage(boolean successMessage) {
        if (successMessage) {
            return MESSAGE_WELCOME_LOADED;
        } else {
            return MESSAGE_WELCOME;
        }
    }
}
