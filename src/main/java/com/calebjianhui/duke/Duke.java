package com.calebjianhui.duke;

import com.calebjianhui.duke.commands.Command;
import com.calebjianhui.duke.commands.ExitCommand;
import com.calebjianhui.duke.parser.Parser;
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
        ui.printWelcomeMessage();
        readCommandTillExitLoop();
    }

    /**
     * Perform a loop to constantly read the user's input till termination
     * **/
    private void readCommandTillExitLoop() {
        Parser pr = new Parser(new Scanner(System.in));
        Command command = null;

        // Constantly loop till break command is received
        while (!ExitCommand.isSelectedCommand(command)) {
            command = pr.parseCommand();
            command.execute();
        }
    }

}
