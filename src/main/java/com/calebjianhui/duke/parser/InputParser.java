package com.calebjianhui.duke.parser;

import com.calebjianhui.duke.commands.*;
import com.calebjianhui.duke.ui.DukeUI;

import java.util.Scanner;

/**
 * Parser to interpret given inputs from user
 * - Convert to different types of commands which will then perform their own actions
 * - Perform minor checks, does not check with task list. This is done is the task manager.
 */
public class InputParser {
    private Scanner in ;
    DukeUI ui;

    private InputParser() {
        ui = new DukeUI();
    }

    public InputParser(Scanner in) {
        this();
        this.in = in;
    }

    /**
     * Read input from user and decipher the given command
     * **/
    public Command parseCommand() {
        String input = in.nextLine();
        if (ExitCommand.isCommandWord(input)) {
            return new ExitCommand();
        } else {
            try {
                return determineAction(input);
            } catch (UnsupportedOperationException e) {
                return new InvalidCommand("unknown");
            } catch (IndexOutOfBoundsException e) {
                return new InvalidCommand("invalid_index");
            }
        }
    }

    /**
     * Determine the action to perform based on user's command
     *
     * @param command Input command from user
     * **/
    private Command determineAction(String command) throws UnsupportedOperationException, IndexOutOfBoundsException {
        // Check for single word command
        if (ListCommand.COMMAND.equals(command)) {
            return new ListCommand();
        }

        // Check and evaluate for multi-word command
        String[] commandList = command.split(" ");
        // - Throw out of bounds should it be a single word command
        if (commandList.length < 2) {
            throw new UnsupportedOperationException();
        }

        System.out.print(commandList[0]);
        // Check what type of command
        switch (commandList[0]) {
            case UpdateCommand.MARK_COMMAND:
            case UpdateCommand.UNMARK_COMMAND:
                if (commandList.length > 2) {
                    throw new UnsupportedOperationException();
                }

                try {
                    int index = Integer.parseInt(commandList[1]) - 1;
                    return new UpdateCommand(UpdateCommand.MARK_COMMAND.equals(commandList[0]), index);
                } catch (NumberFormatException e) {
                    throw new IndexOutOfBoundsException();
                }
            case AddCommand.TODO_COMMAND:
            case AddCommand.EVENT_COMMAND:
            case AddCommand.DEADLINE_COMMAND:

                System.out.print("ine");
                return new AddCommand(commandList[0], command.replaceFirst(commandList[0] + " ", ""));
            case DeleteCommand.COMMAND:
                if (commandList.length > 2) {
                    throw new UnsupportedOperationException();
                }

                try {
                    int index = Integer.parseInt(commandList[1]) - 1;
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    throw new IndexOutOfBoundsException();
                }
            default:
                throw new UnsupportedOperationException();
        }
    }

}
