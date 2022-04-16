import java.util.regex.Pattern;

public class Constant {
    public static final Pattern MARK = Pattern.compile("^mark[\\s][0-9]*$");
    public static final Pattern UNMARKED = Pattern.compile("^unmark[\\s][0-9]*$");
    public static final Pattern TODO = Pattern.compile("^todo[\\s][\\s\\S]*$");
    public static final Pattern DELETE = Pattern.compile("^delete[\\s][0-9]*$");
    public static final Pattern EVENT = Pattern.compile("^event[\\s][\\s\\S]*$");
    public static final Pattern DEADLINE = Pattern.compile("^deadline[\\s][\\s\\S]*$");
    public static final Pattern DATE = Pattern.compile("[^\\s]*\\s*(.*)");
    public static final Pattern READER = Pattern.compile("\\[([A-Z]|\\s*)\\]\\s*\\[([A-Z]|\\s*)\\]\\s*(.*)");
    public static final Pattern TAG = Pattern.compile("^tag[\\s][\\s\\S]*$");
    public static final Pattern UNTAG = Pattern.compile("^untag[\\s][\\s\\S]*$");
    public static final Pattern TAGCONTENT = Pattern.compile("[^\\s]*\\s*(.*)");
    public static final Pattern FIND = Pattern.compile("^find[\\s][\\s\\S]*$");

    public static final String TODO_CANNOT_BE_EMPTY = "The description of a todo task cannot be empty";
    public static final String DEADLINE_CANNOT_BE_EMPTY = "The description of a deadline task cannot be empty";
    public static final String DATE_TIME_CANNOT_BE_FOUND = "The date and time cannot be found";
    public static final String EVENT_CANNOT_BE_EMPTY = "The description of a event task cannot be empty";
    public static final String TASKTAG_CANNOT_BE_EMPTY = "Please choose a task to tag.";
    public static final String UNTAG_CANNOT_BE_EMPTY = "Please choose a task to untag.";
    public static final String DELETE_CANNOT_BE_EMPTY = "Please choose a task to delete.";
    public static final String TAGCONTENT_CANNOT_BE_EMPTY = "Please key in content for the tag.";
}
