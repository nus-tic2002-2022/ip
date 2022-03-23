package duke.constants;
import java.util.regex.Pattern;

public class DukeConstants {
    public static final Pattern ARCHIVE = Pattern.compile("^archive.*$");
    public static final Pattern MARK = Pattern.compile("^mark\\s[0-9]*$");
    public static final Pattern UNMARK = Pattern.compile("^unmark\\s[0-9]*$");
    public static final Pattern REMOVE = Pattern.compile("^delete\\s[0-9]*$");
    public static final Pattern TODO = Pattern.compile("^todo.*$");
    public static final Pattern DEADLINE = Pattern.compile("^deadline.*$");
    public static final Pattern EVENT = Pattern.compile("^event.*$");
    public static final Pattern FIND = Pattern.compile("^find.*$");
    public static final Pattern STORAGE_EVENT = Pattern.compile("^\\[E\\].*$");
    public static final Pattern STORAGE_DEADLINE = Pattern.compile("^\\[D\\].*$");
    public static final Pattern STORAGE_TODO = Pattern.compile("^\\[T\\].*$");
    public static final Pattern STORAGE_ISMARKED = Pattern.compile("^\\[X\\]\\s.*$");
}
