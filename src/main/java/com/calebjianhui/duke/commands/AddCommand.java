package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.taskmanager.TaskManager;
import com.calebjianhui.duke.enums.TaskType;

public class AddCommand extends Command {
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";

    private final String type;
    private final String command;
    private final boolean isSilent;
    private final boolean isDone;

    public AddCommand(String type, String command) {
        this.type = type.toUpperCase();
        this.command = command;
        this.isSilent = false;
        this.isDone = false;
    }

    public AddCommand(boolean isSilent, String type, boolean isDone, String command) {
        this.type = type.toUpperCase();
        this.command = command;
        this.isSilent = isSilent;
        this.isDone = isDone;
    }

    /**
     * Execute the specified command
     */
    @Override
    public boolean execute() {
        return TaskManager.getInstance().addToTaskList(isSilent, TaskType.valueOf(type), isDone, command);
    }
}