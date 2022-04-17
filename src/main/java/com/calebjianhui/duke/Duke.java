package com.calebjianhui.duke;

import java.util.Scanner;

import com.calebjianhui.duke.commands.Command;
import com.calebjianhui.duke.commands.ExitCommand;
import com.calebjianhui.duke.parser.InputParser;
import com.calebjianhui.duke.storage.FileHandler;
import com.calebjianhui.duke.ui.DukeUI;

/**
 * The Duke program implements a task manager that keep tracks
 * of various tasks based on input given by user
 *
 * @author calebjianhui
 * @version 1.0
 */
public class Duke {

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Main method to start off the entire program
     * - Display a welcome message to user & read and act upon input given by user
     **/
    public void run() {
        // Start up tasks
        DukeUI ui = new DukeUI();
        ui.printWelcomeMessage(FileHandler.readAndUpdateTask());
        readCommandTillExitLoop();
    }

    /**
     * Constantly read and decipher user's input till an exit command is received from the user
     **/
    private void readCommandTillExitLoop() {
        InputParser pr = new InputParser(new Scanner(System.in));
        Command command = null;

        // Constantly loop till exit command is received
        while (!(command instanceof ExitCommand)) {
            command = pr.parseCommand();
            boolean isUpdated = command.execute();
            // Save to file should there be updates to the task list
            if (isUpdated) {
                FileHandler.saveTask();
            }
        }
    }

}
