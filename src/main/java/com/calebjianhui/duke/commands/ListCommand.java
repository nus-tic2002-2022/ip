package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.taskmanager.TaskManager;

public class ListCommand extends Command {
    public static final String COMMAND = "list";

    /**
     * Execute the specified command
     */
    public void execute() {
        TaskManager.getInstance().listTask();
    }
}
