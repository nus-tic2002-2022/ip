package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.taskmanager.TaskManager;

public class UpdateCommand extends Command {
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";

    private final boolean isMark;
    private final int index;

    public UpdateCommand(boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    /**
     * Execute the specified command
     */
    @Override
    public void execute() {
        TaskManager.getInstance().updateTaskStatus(isMark, index);
    }
}
