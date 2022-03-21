package com.calebjianhui.duke.commands;

import com.calebjianhui.duke.ui.DukeUI;

import java.util.Arrays;
import java.util.List;

public class ExitCommand extends Command {

    /**
     * Return a list of accepted words used for command
     */
    public static boolean isCommandWord(String input) {
        List<String> acceptedWords = Arrays.asList("bye", "quit", "exit");
        return acceptedWords.contains(input);
    }

    /**
     * Check if the input command is of the same type
     *
     * @param instance Main command to be compared with
     */
    public static boolean isSelectedCommand(Command instance) {
        return (instance instanceof ExitCommand);
    }

    /**
     * Execute the specified command
     */
    public boolean execute() {
        new DukeUI().printEndingMessage();
        return false;
    }
}
