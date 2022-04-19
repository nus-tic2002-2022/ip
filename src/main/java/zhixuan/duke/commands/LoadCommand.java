package zhixuan.duke.commands;

import zhixuan.duke.data.task.TaskManager;

/**
 * Load new file
 */
public class LoadCommand extends Command {

    public static final String LOAD_COMMAND = "load";

    @Override
    public boolean execute() {
        TaskManager.getInstance().loadNewFile();
        return false;
    }
    
}
