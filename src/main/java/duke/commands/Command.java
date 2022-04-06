package duke.commands;

import duke.exceptions.*;

import java.io.IOException;
import java.util.List;

public interface Command {


    List<String> run(String[] fullCommand) throws DukeException, IOException;


    default boolean isExit(){
        return false;
    }
}