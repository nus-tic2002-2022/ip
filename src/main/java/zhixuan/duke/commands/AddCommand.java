package zhixuan.duke.commands;
import zhixuan.duke.data.task.TaskManager;
import zhixuan.duke.common.EnumTask;

public class AddCommand extends Command {

    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    private final String type;
    private final String command;
    private boolean isDone;

    public AddCommand(String type, String command) {
        this.type = type.toUpperCase();
        this.command = command;
    }

    public AddCommand(String type, boolean isDone, String command) {
        this.type = type.toUpperCase();
        this.isDone = isDone;
        this.command = command;
    }

    @Override
    public boolean execute() {
        return TaskManager.getInstance().addToTaskList(EnumTask.valueOf(type), isDone, command);
    }
    
}
