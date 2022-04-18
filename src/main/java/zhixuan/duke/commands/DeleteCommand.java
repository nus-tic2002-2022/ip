package zhixuan.duke.commands;

import zhixuan.duke.data.task.TaskManager;

public class DeleteCommand extends Command {

    public static final String DELETE_COMMAND = "delete";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean execute() {
        return TaskManager.getInstance().deleteTask(index);
    }
}
