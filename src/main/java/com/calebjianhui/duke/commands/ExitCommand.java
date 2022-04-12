package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.ui.DukeUI;

import java.util.Arrays;
import java.util.List;

/**
 * This command exits the program gracefully
 **/
public class ExitCommand extends Command {

    /**
     * Check if the given command input given by user matches any accepted terminating words
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
