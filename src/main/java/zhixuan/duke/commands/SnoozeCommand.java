package zhixuan.duke.commands;

import zhixuan.duke.common.EnumDateTime;
import zhixuan.duke.data.exceptions.InvalidTaskException;
import zhixuan.duke.data.task.TaskManager;
import zhixuan.duke.ui.DukeUI;

/**
 * Snooze due date of task
 **/
public class SnoozeCommand extends Command {

    public static final String SNOOZE_COMMAND = "snooze";
    private final int index;
    private final String input;
    private final int amount;

    /**
     * Constructor for normal snooze task
     */
    public SnoozeCommand(int index, String input, int amount) {
        this.index = index;
        this.input = input.toUpperCase();
        this.amount = amount;
    }

    /**
     * Checks if input has correct type of date/time
     */
    public boolean containsEnum(String input) {
        for (EnumDateTime option : EnumDateTime.values()) {
            if (option.name().equals(input)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean execute() {
        if (containsEnum(input)) {
            return TaskManager.getInstance().snoozeTask(index, EnumDateTime.valueOf(input), amount);
        }
        new DukeUI().showToUser(InvalidTaskException.REPLY_INVALID_DATETIME_ENUM);
        return false;
    }
}
