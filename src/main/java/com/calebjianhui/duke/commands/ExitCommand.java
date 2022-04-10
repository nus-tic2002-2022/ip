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
     * Execute the specified command
     */
    public boolean execute() {
        new DukeUI().printEndingMessage();
        return false;
    }
}
