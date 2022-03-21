package com.calebjianhui.duke;

import com.calebjianhui.duke.commands.Command;
import com.calebjianhui.duke.commands.ExitCommand;
import com.calebjianhui.duke.parser.InputParser;
import com.calebjianhui.duke.storage.FileHandler;
import com.calebjianhui.duke.ui.DukeUI;

import java.util.Scanner;

public class Duke {
    DukeUI ui;

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Main logic to run the program
     * **/
    public void run() {
        // Instantiate required packages
        ui = new DukeUI();

        // Start up tasks
        ui.printWelcomeMessage(FileHandler.readAndUpdateTask());
        readCommandTillExitLoop();
    }

    /**
     * Perform a loop to constantly read the user's input till termination
     * **/
    private void readCommandTillExitLoop() {
        InputParser pr = new InputParser(new Scanner(System.in));
        Command command = null;

        // Constantly loop till break command is received
        while (!ExitCommand.isSelectedCommand(command)) {
            command = pr.parseCommand();
            boolean isUpdated = command.execute();
            // Save to file should there be updates
            if (isUpdated) {
                FileHandler.saveTask();
            }
        }
    }

}
