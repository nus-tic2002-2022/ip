package duke.parser;

import duke.commands.*;
import duke.common.JCode;
import duke.data.exception.DukeException;

/**
 * This class will store the user input after tokenizer and access the methods to process according to user command.
 */
public class Parser {
    public static void checkEmptyCommand(String command) throws DukeException {
        if (JCode.isTODO(command.trim()))
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        if (JCode.isDEADLINE(command.trim()))
            throw new DukeException("☹ OOPS!!! The description of an deadline cannot be empty.");
        if (JCode.isEVENT(command.trim()))
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        if (JCode.isDELETE(command.trim()))
            throw new DukeException("☹ OOPS!!! The index that want to delete cannot be empty.");
        if (JCode.isMARK(command.trim()))
            throw new DukeException("☹ OOPS!!! The index that want to mark cannot be empty.");
        if (JCode.isUNMARK(command.trim()))
            throw new DukeException("☹ OOPS!!! The index that want to unmark cannot be empty.");
        if (JCode.isFIND(command.trim()))
            throw new DukeException("☹ OOPS!!! The description to search cannot be empty.");
    }

    public static Command parse(String fullCommand) throws DukeException {
        checkEmptyCommand(fullCommand);
        Command com = new Command();
        String first = fullCommand.split(" ")[0];
        Commands commands = JCode.getCommands(first);
        if (commands != null) {
            switch (commands) {
                case LIST:
                    com = new ListCommand();
                    break;
                case MARK:
                    com = JCode.getMarkCommand(fullCommand, first);
                    break;
                case UNMARK:
                    com = JCode.getUnmarkCommand(fullCommand, first);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    com = JCode.getAddCommand(fullCommand, first);
                    break;
                case DELETE:
                    com = JCode.getDeleteCommand(fullCommand, first);
                    break;
                case FIND:
                    com = JCode.getFindCommand(fullCommand, first);
                    break;
                default:
                    com.setCommands(commands);
                    //throw new AssertionError(commands);
            }
        }
        return com;
    }


}
