package duke.commands;
import duke.Storage;

import duke.task.*;

import java.util.ArrayList;
import java.util.List;

public class ListCommand implements Command{
    private TaskList tasks;

    public ListCommand(TaskList tasks){
        this.tasks = tasks;
    }


    public List<String> run(String[] fullCommand){
        List<String> messages = new ArrayList<>();
        messages.add("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++){
            messages.add((i + 1) + "." + tasks.get(i));
        }
        return messages;
    }
}