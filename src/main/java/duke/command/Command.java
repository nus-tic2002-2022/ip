package duke.command;

import java.util.List;
import java.io.IOException;
import duke.exceptions.*;
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