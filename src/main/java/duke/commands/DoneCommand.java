package duke.commands;

import duke.Storage;
import duke.task.*;

import java.io.IOException;
import java.util.List;

public class DoneCommand implements Command{
    private final TaskList tasks;
    private Storage storage;

    public DoneCommand(TaskList tasks, Storage storage){
        this.tasks = tasks;
        this.storage = storage;
    }


    @Override
    public List<String> run(String[] fullCommand) throws NumberFormatException, IndexOutOfBoundsException, IOException{
        try{
            Task markItem = tasks.get(Integer.parseInt(fullCommand[1]) - 1);
            markItem.markAsDone();
            storage.store(tasks.convertAsLines());
            return List.of("Nice! I've marked this task as done: " + System.lineSeparator() + "     " + markItem);
        }catch(NumberFormatException e){
            return List.of("☹ OOPS!!! This is not a number: " + fullCommand[1]);
        }catch(IndexOutOfBoundsException e){
            return List.of("☹ OOPS!!! The index out of bound: " + fullCommand[1]);
        }
    }
}