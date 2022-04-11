package duke.command;

import java.util.List;

public class ByeCommand implements Command {
    /**
     * Returns the message to user when he/she leave.
     *
     * @param line array of string input from user.
     * @return message to show the user.
     */

    @Override
    public List<String> run(String[] line){
        return List.of("    Bye. Hope to see you again soon!");
    }
    /**
     * Return true to exit the program.
     *
     * @return true when execute this command to exit the program.
     */
    @Override
    public boolean isExit(){
        return true;
    }
}