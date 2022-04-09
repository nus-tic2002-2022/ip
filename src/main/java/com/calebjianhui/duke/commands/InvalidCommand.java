package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.ui.DukeUI;

public class InvalidCommand extends Command {
    public final static String INVALID_INDEX_MESSAGE = "Invalid task selected. Please select a valid task.";
    public final static String UNKNOWN_COMMAND_MESSAGE = "Hmm, I don't understand what that means. Can you explain again?";
    public final static String UNKNOWN_PARAMETERS_MESSAGE = "Hmm, I don't understand that parameter you specified. Can you check again?";

    private final String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Execute the specified command
     */
    public boolean execute() {
        new DukeUI().formatDukeReply(message);
        return false;
    }
}
