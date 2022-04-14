package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.taskmanager.TaskManager;
import com.calebjianhui.duke.ui.Messages;

/**
 * This command allows deleting of task in the task manager.
 **/
public class DeleteCommand extends Command {
    // Literal command given by user
    public static final String COMMAND = "delete";
    // Parameter indicating to delete all
    public static final String PARAMS_DELETE_ALL = "all";
    // Help page
    public static final String HELP_PAGE =
            Messages.DIVIDER_UNDERSCORE_EXTENDED + " Delete all or a specific task in the task list.\n"
                    + " The index specified should correspond to the index in the normal listing of tasks.\n"
                    + " Type 'list' to view the normal listing of tasks.\n\n"
                    + " Usage:\n\tdelete (all|<index of task>)\n"
                    + " Example:\n"
                    + " \tdelete all\n"
                    + " \tdelete 1\n"
                    + Messages.DIVIDER_UNDERSCORE_EXTENDED;

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
