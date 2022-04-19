package zhixuan.duke.commands;

import zhixuan.duke.data.task.TaskManager;

/**
 * Find task based on specified day
 */
public class FindCommand extends Command {

    public static final String FIND_COMMAND = "find";

    private final String date;

    /**
     * Constructor with date string
     */
    public FindCommand(String date) {
        this.date = date;
    }

    @Override
    public boolean execute() {
        TaskManager.getInstance().findTask(date);
        return false;
    }
}
