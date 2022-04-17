package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.ui.DukeUI;

/**
 * This command indicates that the given command is invalid.
 **/
public class InvalidCommand extends Command {
    // Message to be displayed to user
    public static final String INVALID_INDEX_MESSAGE = "Invalid task selected. Please select a valid task.";
    public static final String UNKNOWN_COMMAND_MESSAGE =
            "Hmm, I don't understand what that means. Can you explain again?\n *Type 'help' to view the help page.";

    // Variables needed:
    private final String message;

    /**
     * InvalidCommand constructor
     *
     * @param message message to be displayed to user
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Execute the specified command by displaying the result to user.
     *
     * @return Default return false as this command does not make changes to the task list
     */
    public boolean execute() {
        new DukeUI().formatDukeReply(message);
        return false;
    }

    /**
     * Returns a custom invalid parameter message.
     *
     * @param command The command that raises the invalid parameter error
     * @return Message indicating the issue and advising them to visit the relevant help page
     */
    public static String getInvalidParametersMessage(String command) {
        String helpCommand = command.equals("help") ? "'help'" : "'help " + command + "'";
        return "Hmm, I don't understand that parameter you specified.\n *Type " + helpCommand
                + " to view the help page.";
    }
}
