package duke.commands;

import java.util.List;

public class ByeCommand implements Command {


    @Override
    public List<String> run(String[] line){
        return List.of("    Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit(){
        return true;
    }
}