package com.calebjianhui.duke.parser;

import com.calebjianhui.duke.commands.*;
import com.calebjianhui.duke.enums.UpdateCommandType;
import com.calebjianhui.duke.ui.DukeUI;

import java.lang.reflect.MalformedParametersException;
import java.util.Arrays;
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
            } catch (UnsupportedOperationException | IndexOutOfBoundsException | MalformedParametersException e) {
                return new InvalidCommand(e.getMessage());
            }
        }
    }

    /**
     * Determine the action to perform based on user's command
     *
     * @param command Input command from user
     * **/
    private Command determineAction(String command) throws UnsupportedOperationException, IndexOutOfBoundsException, MalformedParametersException {
        // Check and evaluate for multi-word command
        String[] commandList = command.split(" ");

        // Check what type of command
        switch (commandList[0]) {
            case ListCommand.COMMAND:
                // Ensure that command is in the proper format
                if (commandList.length > 1) {
                    throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
                }

                return new ListCommand();
            case FindCommand.COMMAND:
                // Ensure that command is in the proper format
                if (commandList.length <= 1) {
                    throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
                }

                if (commandList[1].equals(FindCommand.PARAMS_CHAR_SEARCH)) {
                    // Character Search
                    if (commandList.length <= 2) {
                        throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
                    }
                    return new FindCommand(true,
                            String.join(" ",
                                    Arrays.copyOfRange(commandList, 2, commandList.length)
                            ));
                } else {
                    // Word Search
                    return new FindCommand(false,
                            String.join(" ",
                                    Arrays.copyOfRange(commandList, 1, commandList.length)
                            ));
                }
            case UpdateCommand.MARK_COMMAND:
            case UpdateCommand.UNMARK_COMMAND:
            case UpdateCommand.EDIT_COMMAND:
                // Ensure that command is in the proper format
                if (commandList.length <= 1) {
                    throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
                }
                try {
                    int index = Integer.parseInt(commandList[1]) - 1;
                    if (commandList[0].equals(UpdateCommand.EDIT_COMMAND)) {
                        // Update command
                        if (commandList.length <= 2) {
                            throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
                        }
                        UpdateCommandType editField = UpdateCommand.checkCommandType(commandList[2]);
                        if (editField.equals(UpdateCommandType.INVALID_COMMAND)) {
                            throw new MalformedParametersException(InvalidCommand.UNKNOWN_PARAMETERS_MESSAGE);
                        }
                        return new UpdateCommand(editField, index,
                                String.join(" ",
                                        Arrays.copyOfRange(commandList, 3, commandList.length)
                                )
                        );
                    } else {
                        // Mark / Unmark command have length of 2
                        if (commandList.length > 2) {
                            throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
                        }
                        return new UpdateCommand(
                                commandList[0].equalsIgnoreCase(UpdateCommand.MARK_COMMAND) ?
                                        UpdateCommandType.MARK :
                                        UpdateCommandType.UNMARK,
                                index, "");
                    }
                } catch (NumberFormatException e) {
                    throw new IndexOutOfBoundsException(InvalidCommand.INVALID_INDEX_MESSAGE);
                }
            case AddCommand.TODO_COMMAND:
            case AddCommand.EVENT_COMMAND:
            case AddCommand.DEADLINE_COMMAND:
            case AddCommand.FIXED_DURATION_COMMAND:
                if (commandList.length <= 1) {
                    throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
                }
                return new AddCommand(commandList[0], command.replaceFirst(commandList[0] + " ", ""));
            case CloneCommand.COMMAND:
                if (commandList.length > 2) {
                    throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
                }

                try {
                    int index = Integer.parseInt(commandList[1]) - 1;
                    return new CloneCommand(index);
                } catch (NumberFormatException e) {
                    throw new IndexOutOfBoundsException(InvalidCommand.INVALID_INDEX_MESSAGE);
                }
            case DeleteCommand.COMMAND:
                if (commandList.length > 2) {
                    throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
                }

                try {
                    int index = Integer.parseInt(commandList[1]) - 1;
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    throw new IndexOutOfBoundsException(InvalidCommand.INVALID_INDEX_MESSAGE);
                }
            default:
                throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
        }
    }

}
