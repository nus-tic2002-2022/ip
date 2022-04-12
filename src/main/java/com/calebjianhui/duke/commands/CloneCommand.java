package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.taskmanager.TaskManager;

/**
 * This command allows the ease of duplicating task in the task manager
 **/
public class CloneCommand extends Command {
    // Literal command given by user
    public static final String COMMAND = "clone";

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
