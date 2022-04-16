package zhixuan.duke.data.exceptions;

public class InvalidTaskException extends Exception {

    public static final String REPLY_TODO_EMPTY = "Todo cannot be empty.";
    public static final String REPLY_EVENT_INVALID_DATE = "Use the correct command (event DESCRIPTION /at EVENT_DATE).";
    public static final String REPLY_EVENT_INVALID_DESC = "Include description (event DESCRIPTION /at EVENT_DATE).";
    public static final String REPLY_EVENT_INVALID_LENGTH = "Include description and event date (event DESCRIPTION /at EVENT_DATE).";
    public static final String REPLY_DEADLINE_INVALID_DATE = "Use the correct command (deadline DESCRIPTION /by DEADLINE).";
    public static final String REPLY_DEADLINE_INVALID_DESC = "Include description (deadline DESCRIPTION /by DEADLINE).";
    public static final String REPLY_DEADLINE_INVALID_LENGTH = "Include deadline (deadline DESCRIPTION /by DEADLINE).";

    String error;

    public InvalidTaskException(String errorMessage) {
        super(errorMessage);
        error = errorMessage;
    }

    @Override
    public String getMessage () {
        return error;
    }

}
