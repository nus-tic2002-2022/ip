package duke.commands;


import duke.Storage;
import duke.task.*;
import duke.Storage;

import java.io.IOException;
import java.util.List;

public class DeleteCommand implements Command{
    private final TaskList tasks;
    private Storage storage;

    public DeleteCommand(TaskList tasks, Storage storage){
        this.tasks = tasks;
        this.storage = storage;
    }


    @Override
    public List<String> run(String[] fullCommand)throws NumberFormatException, IndexOutOfBoundsException, IOException{
        try {
            Task deleteItem = tasks.remove(Integer.parseInt(fullCommand[1]) - 1);
            storage.store(tasks.convertAsLines());
            return List.of("Noted. I've removed this task: " + System.lineSeparator() + "     " + deleteItem);
        }catch (NumberFormatException e){
            return List.of("☹ OOPS!!! This is not a number: " + fullCommand[1]);
        }catch (IndexOutOfBoundsException e){
            return List.of("☹ OOPS!!! The index out of bound: " + fullCommand[1]);
        }
    }
}