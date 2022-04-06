package duke;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import duke.commands.*;
import duke.exceptions.*;

public class Parser {
    private Map<String, Command>commands = new HashMap<>();

    public static void checkWord(String keyword) throws DukeCheckLineException{
        if(keyword == null){
            throw new DukeCheckLineException();
        }
    }


    public void capture(String name, Command command){
        commands.put(name, command);
    }


    public Command parse(String [] fullCommand){
        Command command = commands.get(fullCommand[0].toLowerCase());

        try{
            checkWord(fullCommand[0]);
        }catch (DukeCheckLineException e){
            return (Command) List.of("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return command;
    }
}
