package zhixuan.duke.commands;

import zhixuan.duke.data.task.TaskManager;

public class MarkCommand extends Command {

    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";

    private final int index;
    private String command;

    public MarkCommand(String command, int index) {
        this.command = command;
        this.index = index;
    }

    @Override
    public boolean execute() {
        return TaskManager.getInstance().markTask(command, index);

    }
    
}
