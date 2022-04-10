package zhixuan.duke.commands;

public abstract class AddCommand {

    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    private final String type;
    private final String command;

    public AddCommand(String type, String command) {
        this.type = type.toUpperCase();
        this.command = command;
    }

    @Override
    public void execute() {
        TaskManager.getInstance().addToTaskList(TaskType.valueOf(type), command);
    }
    
}
