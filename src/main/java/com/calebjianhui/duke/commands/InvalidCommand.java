package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.ui.DukeUI;

public class InvalidCommand extends Command {
    private final static String DUKE_INVALID_INDEX_REPLY = "Invalid task selected. Please select a valid task.";
    private final static String DUKE_UNKNOWN_COMMAND_REPLY = "Hmm, I don't understand what that means. Can you explain again?";

    private final String commandType;

    public InvalidCommand(String commandType) {
        this.commandType = commandType;
    }

    private String decipherType() {
        switch (commandType) {
            case "invalid_index":
                return DUKE_INVALID_INDEX_REPLY;
            default:
                return DUKE_UNKNOWN_COMMAND_REPLY;
        }
    }

    /**
     * Check if the input command is of the same type
     *
     * @param instance Main command to be compared with
     */
    public static boolean isSelectedCommand(Command instance) {
        return (instance instanceof InvalidCommand);
    }


    /**
     * Execute the specified command
     */
    public boolean execute() {
        new DukeUI().formatDukeReply(decipherType());
        return false;
    }
}
