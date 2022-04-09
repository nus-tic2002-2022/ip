package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.taskmanager.TaskManager;

public class CloneCommand extends Command {
    public static final String COMMAND = "clone";

    private final int index;

    public CloneCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the specified command
     */
    @Override
    public boolean execute() {
        return TaskManager.getInstance().cloneTask(index);
    }
}
