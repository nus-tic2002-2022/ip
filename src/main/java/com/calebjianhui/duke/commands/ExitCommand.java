package com.calebjianhui.duke.commands;

import java.util.Arrays;
import java.util.List;

import com.calebjianhui.duke.ui.DukeUI;
import com.calebjianhui.duke.ui.Messages;

/**
 * This command exits the program gracefully
 **/
public class ExitCommand extends Command {
    // Help page
    public static final String HELP_PAGE =
            Messages.DIVIDER_UNDERSCORE + " Exits the program.\n\n"
                    + " Usage:\n    (bye|quit|exit)\n"
                    + Messages.DIVIDER_UNDERSCORE;

    /**
     * Returns true if the user's given input matches any of the terminating words
     *
     * @param input Command input given by user
     * @return If the given input matches any accepted terminating words.
     */
    public static boolean isCommandWord(String input) {
        List<String> acceptedWords = Arrays.asList("bye", "quit", "exit");
        return acceptedWords.contains(input);
    }

    /**
     * Execute the specified command.
     *
     * @return Default return false as this command does not make changes to the task list
     */
    public boolean execute() {
        new DukeUI().printEndingMessage();
        return false;
    }
}
