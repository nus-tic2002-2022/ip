package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.taskmanager.TaskManager;
import com.calebjianhui.duke.ui.Messages;

/**
 * This command allows the ease of duplicating task in the task manager
 **/
public class CloneCommand extends Command {
    // Literal command given by user
    public static final String COMMAND = "clone";
    // Help page
    public static final String HELP_PAGE =
            Messages.DIVIDER_UNDERSCORE_EXTENDED + " Clone an existing task in the task list.\n"
                    + " The index specified should correspond to the index in the normal listing of tasks.\n"
                    + " Type 'list' to view the normal listing of tasks.\n\n"
                    + " Usage:\n\tclone <index of task>\n\n"
                    + " Example:\n"
                    + " \tclone 1\n"
                    + Messages.DIVIDER_UNDERSCORE_EXTENDED;

    // Variables needed:
    // - Index of task to be cloned
    private final int index;

    /**
     * CloneCommand constructor
     *
     * @param index Index of the current task (in the task list) to be cloned
     */
    public CloneCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the specified command.
     *
     * @return Whether the command made any changes to the task list
     */
    @Override
    public boolean execute() {
        return TaskManager.getInstance().cloneTask(index);
    }

}
