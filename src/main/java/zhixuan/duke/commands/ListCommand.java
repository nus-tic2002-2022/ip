package zhixuan.duke.commands;

import zhixuan.duke.data.task.TaskManager;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {

    public static final String LIST_COMMAND = "list";

    @Override
    public boolean execute() {
        TaskManager.getInstance().listTask();
        return false;
    }
    
}
