package duke;
import duke.exceptions.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import duke.command.*;

public class Parser {
    private Map<String, Command>commands = new HashMap<>();

    public static void checkWord(String keyword) throws DukeCheckLineException{
        if(keyword == null){
            throw new DukeCheckLineException();
        }
    }

    /**
     * Capture a command to be returned later.
     *
     * @param name the key word that trigger the command.
     * @param command for mapping the name and to be returned later.
     */
    public void capture(String name, Command command){
        commands.put(name, command);
    }

    /**
     * To check and return an appropriate command.
     *
     * @param fullCommand the input from user.
     * @return command to use for the next processing.
     */
    public Command parse(String [] fullCommand){
        Command command = commands.get(fullCommand[0].toLowerCase());

        try{
            checkWord(fullCommand[0]);
        }catch (DukeCheckLineException e){
            return (Command) List.of(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}
