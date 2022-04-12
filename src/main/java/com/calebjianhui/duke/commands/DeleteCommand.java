package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.taskmanager.TaskManager;

/**
 * This command allows deleting of task in the task manager.
 **/
public class DeleteCommand extends Command {
    // Literal command given by user
    public static final String COMMAND = "delete";

    // Variables needed:
    // - Index of task to be deleted
    private final int index;

    /**
     * DeleteCommand constructor
     *
     * @param index Index of the current task (in the task list) to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the specified command.
     *
     * @return Whether the command made any changes to the task list
     */
    @Override
    public boolean execute() {
        return TaskManager.getInstance().deleteTask(index);
    }
}
