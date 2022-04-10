package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.taskmanager.TaskManager;
import com.calebjianhui.duke.enums.TaskType;

public class AddCommand extends Command {
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String FIXED_DURATION_COMMAND = "fixed";

    private final TaskType type;
    private final String command;
    private final boolean isSilent;
    private final boolean isDone;

    public AddCommand(String type, String command) {
        this.type = getTaskTypeFromCommand(type);
        this.command = command;
        this.isSilent = false;
        this.isDone = false;
    }

    public AddCommand(boolean isSilent, TaskType type, boolean isDone, String command) {
        this.type = type;
        this.command = command;
        this.isSilent = isSilent;
        this.isDone = isDone;
    }

    private static TaskType getTaskTypeFromCommand(String command) {
        if (TODO_COMMAND.equals(command)) {
            return TaskType.TODO;
        } else if (DEADLINE_COMMAND.equals(command)) {
            return TaskType.DEADLINE;
        } else if (EVENT_COMMAND.equals(command)) {
            return TaskType.EVENT;
        } else if (FIXED_DURATION_COMMAND.equals(command)) {
            return TaskType.FIXED_DURATION;
        }
        // TODO
        return TaskType.FIXED_DURATION;
    }

    /**
     * Execute the specified command
     */
    @Override
    public boolean execute() {
        return TaskManager.getInstance().addToTaskList(isSilent, type, isDone, command);
    }
}