package com.calebjianhui.duke.parser;

import com.calebjianhui.duke.commands.*;
import com.calebjianhui.duke.common.Pair;
import com.calebjianhui.duke.enums.ListCommandType;
import com.calebjianhui.duke.enums.UpdateCommandType;
import com.calebjianhui.duke.ui.DukeUI;

import java.lang.reflect.MalformedParametersException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Parser to interpret given inputs from user
 * - Convert to different types of commands which will then perform their own actions.
 * - Perform minor checks, does not check with task list. This is done in the task manager.
 */
public class InputParser {
    private Scanner in ;
    DukeUI ui;

    /**
     * InputParser constructor
     */
    private InputParser() {
        ui = new DukeUI();
    }

    /**
     * InputParser constructor
     *
     * @param in Input stream
     */
    public InputParser(Scanner in) {
        this();
        this.in = in;
    }

    /**
     * Read the next input from user and determine the command that the user is specifying
     *
     * @return Command given by user. Any unrecognised command will default to InvalidCommand
     **/
    public Command parseCommand() {
        String userInput = in.nextLine();

        if (ExitCommand.isCommandWord(userInput)) {
            return new ExitCommand();
        }

        try {
            // Split user's input to an array of string to make it easier to work with
            String[] commandList = userInput.split(" ");

            switch (commandList[0]) {
                case ListCommand.COMMAND:
                    return craftListCommand(commandList);
                case FindCommand.COMMAND:
                    return craftFindCommand(commandList);
                case UpdateCommand.MARK_COMMAND:
                case UpdateCommand.UNMARK_COMMAND:
                case UpdateCommand.EDIT_COMMAND:
                    return craftUpdateCommand(commandList);
                case AddCommand.TODO_COMMAND:
                case AddCommand.EVENT_COMMAND:
                case AddCommand.DEADLINE_COMMAND:
                case AddCommand.FIXED_DURATION_COMMAND:
                    return craftAddCommand(commandList);
                case CloneCommand.COMMAND:
                    return craftCloneCommand(commandList);
                case DeleteCommand.COMMAND:
                    return craftDeleteCommand(commandList);
                default:
                    throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
            }
        } catch (UnsupportedOperationException | IndexOutOfBoundsException | MalformedParametersException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    /**
     * Based on the given user input, craft out a corresponding ListCommand
     *
     * @param commandList Input command from user
     * @return Command that correspond to the input given by user
     * @throws UnsupportedOperationException For malformed commands given by user
     * @throws MalformedParametersException For malformed parameters given by user
     **/
    private Command craftListCommand(String[] commandList) throws UnsupportedOperationException, MalformedParametersException {
        assert ListCommand.COMMAND.equals(commandList[0]) : "Crafting of ListCommand requires a valid input command list.";

        // ListCommand have max word length of 2
        if (commandList.length > 2) {
            throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
        }

        // Normal listing of tasks
        if (commandList.length == 1) {
            return new ListCommand();
        }

        // Determine the type of list that user wishes to view
        ListCommandType listField = ListCommand.checkCommandType(commandList[1]);
        if (listField.equals(ListCommandType.INVALID_COMMAND)) {
            throw new MalformedParametersException(InvalidCommand.UNKNOWN_PARAMETERS_MESSAGE);
        }
        return new ListCommand(new Pair<>(listField, String.join(" ",
                Arrays.copyOfRange(commandList, 2, commandList.length)
        )));
    }

    /**
     * Based on the given user input, craft out a corresponding FindCommand
     *
     * @param commandList Input command from user
     * @return Command that correspond to the input given by user
     * @throws UnsupportedOperationException For malformed commands given by user
     **/
    private Command craftFindCommand(String[] commandList) throws UnsupportedOperationException {
        assert FindCommand.COMMAND.equals(commandList[0]) : "Crafting of FindCommand requires a valid input command list.";

        // FindCommand have a minimum word length of 2
        if (commandList.length <= 1) {
            throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
        }

        // Determine if it is character search or word search
        if (commandList[1].equals(FindCommand.PARAMS_CHAR_SEARCH)) {
            // Character Search
            // - Require a minimum word length of 3
            if (commandList.length <= 2) {
                throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
            }
            return new FindCommand(true, String.join(" ",
                    Arrays.copyOfRange(commandList, 2, commandList.length)
            ));
        } else {
            // Word Search
            return new FindCommand(false, String.join(" ",
                    Arrays.copyOfRange(commandList, 1, commandList.length)
            ));
        }
    }

    /**
     * Based on the given user input, craft out a corresponding UpdateCommand
     *
     * @param commandList Input command from user
     * @return Command that correspond to the input given by user
     * @throws UnsupportedOperationException For malformed commands given by user
     * @throws MalformedParametersException For malformed parameters given by user
     * @throws IndexOutOfBoundsException Invalid index of task given by user
     **/
    private Command craftUpdateCommand(String[] commandList) throws UnsupportedOperationException, IndexOutOfBoundsException, MalformedParametersException {
        assert Arrays.asList(UpdateCommand.MARK_COMMAND, UpdateCommand.UNMARK_COMMAND, UpdateCommand.EDIT_COMMAND)
                .contains(commandList[0]) : "Crafting of UpdateCommand requires a valid input command list.";

        // UpdateCommand have a minimum word length of 2
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
                return new UpdateCommand(editField, index, String.join(" ",
                        Arrays.copyOfRange(commandList, 3, commandList.length)
                ));
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
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(InvalidCommand.INVALID_INDEX_MESSAGE);
        }
    }

    /**
     * Based on the given user input, craft out a corresponding AddCommand
     *
     * @param commandList Input command from user
     * @return Command that correspond to the input given by user
     * @throws UnsupportedOperationException For malformed commands given by user
     **/
    private Command craftAddCommand(String[] commandList) throws UnsupportedOperationException {
        assert Arrays.asList(AddCommand.TODO_COMMAND, AddCommand.EVENT_COMMAND, AddCommand.DEADLINE_COMMAND, AddCommand.FIXED_DURATION_COMMAND)
                .contains(commandList[0]) : "Crafting of AddCommand requires a valid input command list.";

        // AddCommand have a minimum word length of 2
        if (commandList.length <= 1) {
            throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
        }

        return new AddCommand(commandList[0], String.join(" ",
                Arrays.copyOfRange(commandList, 1, commandList.length)
        ));
    }

    /**
     * Based on the given user input, craft out a corresponding CloneCommand
     *
     * @param commandList Input command from user
     * @return Command that correspond to the input given by user
     * @throws UnsupportedOperationException For malformed commands given by user
     * @throws IndexOutOfBoundsException Invalid index of task given by user
     **/
    private Command craftCloneCommand(String[] commandList) throws UnsupportedOperationException, IndexOutOfBoundsException {
        assert CloneCommand.COMMAND.equals(commandList[0]) : "Crafting of CloneCommand requires a valid input command list.";

        // CloneCommand have a fixed word length of 2
        if (commandList.length != 2) {
            throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
        }

        try {
            int index = Integer.parseInt(commandList[1]) - 1;
            return new CloneCommand(index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(InvalidCommand.INVALID_INDEX_MESSAGE);
        }
    }

    /**
     * Based on the given user input, craft out a corresponding DeleteCommand
     *
     * @param commandList Input command from user
     * @return Command that correspond to the input given by user
     * @throws UnsupportedOperationException For malformed commands given by user
     * @throws IndexOutOfBoundsException Invalid index of task given by user
     **/
    private Command craftDeleteCommand(String[] commandList) throws UnsupportedOperationException, IndexOutOfBoundsException {
        assert DeleteCommand.COMMAND.equals(commandList[0]) : "Crafting of DeleteCommand requires a valid input command list.";

        // DeleteCommand have a fixed word length of 2
        if (commandList.length != 2) {
            throw new UnsupportedOperationException(InvalidCommand.UNKNOWN_COMMAND_MESSAGE);
        }

        // Whether to delete all tasks
        if (DeleteCommand.PARAMS_DELETE_ALL.equals(commandList[1])) {
            return new DeleteCommand(true, 1);
        }

        // Else, delete a task at a specific index
        try {
            int index = Integer.parseInt(commandList[1]) - 1;
            return new DeleteCommand(false, index);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(InvalidCommand.INVALID_INDEX_MESSAGE);
        }
    }

}
