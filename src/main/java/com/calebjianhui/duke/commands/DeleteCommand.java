package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.taskmanager.TaskManager;

/**
 * This command allows deleting of task in the task manager.
 **/
public class DeleteCommand extends Command {
    // Literal command given by user
    public static final String COMMAND = "delete";
    // Parameter indicating to delete all
    public static final String PARAMS_DELETE_ALL = "all";

    // Variables needed:
    private final boolean isDeleteAll;
    private final int index;

    /**
     * DeleteCommand constructor
     *
     * @param isDeleteAll Whether to delete all the tasks in the task list
     * @param index Index of the current task (in the task list) to be deleted
     */
    public DeleteCommand(boolean isDeleteAll, int index) {
        this.isDeleteAll = isDeleteAll;
        this.index = index;
    }

    /**
     * Execute the specified command.
     *
     * @return Whether the command made any changes to the task list
     */
    @Override
    public boolean execute() {
        return TaskManager.getInstance().deleteTask(isDeleteAll, index);
    }
}
