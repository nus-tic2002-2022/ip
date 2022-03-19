package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.taskmanager.TaskManager;

public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the specified command
     */
    @Override
    public void execute() {
        TaskManager.getInstance().deleteTask(index);
    }
}
