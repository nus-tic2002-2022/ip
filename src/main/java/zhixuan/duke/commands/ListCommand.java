package zhixuan.duke.commands;

import zhixuan.duke.data.task.TaskManager;

public class ListCommand extends Command {

    public static final String COMMAND = "list";

    public boolean execute() {
        TaskManager.getInstance().listTask();
        return false;
    }
    
}
