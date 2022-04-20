package zhixuan.duke.commands;

import zhixuan.duke.data.task.TaskManager;

/**
 * Find task based on specified date or keyword
 */
public class FindCommand extends Command {

    public static final String FIND_COMMAND = "find";

    private final String input;

    /**
     * Constructor with input string
     */
    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public boolean execute() {
        TaskManager.getInstance().findTask(input);
        return false;
    }
}
