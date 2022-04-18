package zhixuan.duke.commands;

import zhixuan.duke.data.task.TaskManager;

/**
 * Mark task based on index
 */
public class MarkCommand extends Command {

    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";

    private final int index;
    private String command;

    /**
     * Constructor with command (mark/unmark) and index
     */
    public MarkCommand(String command, int index) {
        this.command = command;
        this.index = index;
    }

    @Override
    public boolean execute() {
        return TaskManager.getInstance().markTask(command, index);

    }
    
}
