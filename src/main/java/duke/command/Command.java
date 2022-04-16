package duke.command;

import duke.exceptions.DukeException;

import java.io.IOException;
import java.util.List;

public interface Command {

    /**
     * Returns the message to user after the command is run.
     *
     * @param fullCommand array of command from the user input.
     * @return message to the user.
     */
    List<String> run(String[] fullCommand) throws DukeException, IOException;



    /**
     * Returns the message to user after the command is run.
     *
     * @param strNum array of command from the user input.
     * @return message to the user.
     */

    public default boolean isDigit(String strNum) {
        return strNum.matches("[0-9]{1,}");
    }
    default boolean isExit(){
        return false;
    }
}