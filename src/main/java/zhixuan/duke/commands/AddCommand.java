package zhixuan.duke.commands;
import zhixuan.duke.data.task.TaskManager;
import zhixuan.duke.common.EnumTask;

/**
 * Adds task to task list
 **/
public class AddCommand extends Command {

    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    private final String type;
    private final String command;
    private final boolean isDone;
    private final boolean isSilent;

    /**
     * Constructor for normal add task
     */
    public AddCommand(String type, String command) {
        this.type = type.toUpperCase();
        this.command = command;
        this.isSilent = false;
        this.isDone = false;
    }

    /**
     * Constructor for add task when loading file
     */
    public AddCommand(boolean isSilent, String type, boolean isDone, String command) {
        this.isSilent = isSilent;
        this.type = type.toUpperCase();
        this.isDone = isDone;
        this.command = command;
    }

    @Override
    public boolean execute() {
        return TaskManager.getInstance().addToTaskList(isSilent, EnumTask.valueOf(type), isDone, command);
    }
}
