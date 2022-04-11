package duke.task;
import duke.exceptions.*;
import duke.command.Command;
import duke.Storage;
import duke.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToDo extends Task{

    public ToDo(String description){
        super(description);
    }

    public static void checkDescription(String[] command)throws DukeException {
        if(command.length == 1){
            throw new DukeException();
        }
    }

    /**
     * To generate a Todo detail and store in the tasks list according to user input.
     *
     * @param tasks the list of tasks;
     * @param storage to save the todo detail of the task.
     * @return a command which generates todo task.
     */
    public static Command getCommand(TaskList tasks, Storage storage){
        return fullCommand ->{
            try{
                checkDescription(fullCommand);
                String[] keyword = Arrays.copyOfRange(fullCommand, 1, fullCommand.length);
                Task todoTask = new ToDo(String.join(" ", keyword));
                tasks.add(todoTask);
                storage.store(tasks.convertAsLines());
                return List.of("Got it. I've added this task: " + System.lineSeparator() +
                        "     " + todoTask + System.lineSeparator() +
                        "   Now you have " + tasks.size() + " tasks in the list.");
            }catch(DukeException e){
                return List.of(" OOPS!!! The description of a " + "todo" + " cannot be empty.");
            }
        };
    }

    /**
     * Return a list of strings to user.
     *
     * @return this string task.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    /**
     * Return a list of strings that can be saved.
     *
     * @return a task list for saving.
     */
    @Override
    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("T");
        list.addAll(super.getList());
        return list;
    }
}
