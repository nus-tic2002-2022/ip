package duke.exception;

/**
 * Allow customised exception message
 */
public class DukeException extends Exception{


    public static final String INVALID_COMMAND = "\t/!\\ You've entered an invalid command: ";
    public static final String INVALID_DATE_FORMAT = "\t/!\\ The date should be in d MMM yyyy format. /!\\";
    public static final String INVALID_DATETIME_FORMAT = "\t/!\\ The date should be in yyyy-MM-dd HH:mm format. /!\\";
    public static final String INVALID_DEADLINE_FORMAT = "\t/!\\ The deadline command requires a description, /by specific date. /!\\";
    public static final String INVALID_EVENT_FORMAT = "\t/!\\ The event command requires a description, /at specific datetime. /!\\";
    public static final String INVALID_TODO_FORMAT = "\t/!\\ The todo command requires a description. /!\\";
    public static final String INVALID_FIND_FORMAT = "\t/!\\ The find command requires a keyword. ";
    public static final String INVALID_TASK_NUMBER = "\t/!\\ You've entered an invalid task number: ";
    public static final String MISSING_TASK_NUMBER = "\t/!\\ You've not entered a task number: ";
    public static final String INVALID_DATE_FORMAT_FILE = "\t/!\\ please check file for invalid date format. /!\\";
    public static final String INVALID_DATETIME_FORMAT_FILE = "\t/!\\ please check file for invalid datetime format. /!\\";
    public static final String INVALID_TASK_STATUS_FILE = "\t/!\\ Expected 0 or 1 but getting: ";
    public static final String INVALID_TASK_TYPE_FILE = "\t/!\\ please check file for invalid task type: ";


    private final String message;

    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Get exception message when errors occurred
     * @return customised exception message
     */
    @Override
    public String getMessage() {
        return message;
    }
}