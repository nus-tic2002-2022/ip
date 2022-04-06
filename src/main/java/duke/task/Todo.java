package duke.task;

import duke.Storage;
import duke.commands.Command;
import duke.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Todo extends Task{
    public Todo (String description){
        super(description);
    }
    public Todo(String isDone,String description){
        super(isDone,description);
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
    @Override
    public String toSavePattern() {
        String mode = super.isDone  == true? "1":"0";
        return "T | "+mode+" | "+super.description;
    }

    public static void checkDescription(String[] command)throws DukeException {
        if(command.length == 1){
            throw new DukeException();
        }
    }


    public static Command getCommand(TaskList tasks, Storage storage){
        return fullCommand ->{
            try{
                checkDescription(fullCommand);
                String[] keyword = Arrays.copyOfRange(fullCommand, 1, fullCommand.length);
                Task todoTask = new Todo(String.join(" ", keyword));
                tasks.add(todoTask);
                storage.store(tasks.convertAsLines());
                return List.of("Got it. I've added this task: " + System.lineSeparator() +
                        "     " + todoTask + System.lineSeparator() +
                        "   Now you have " + tasks.size() + " tasks in the list.");
            }catch(DukeException e){
                return List.of("☹ OOPS!!! The description of a " + "todo" + " cannot be empty.");
            }
        };
    }


    @Override
    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("T");
        list.addAll(super.getList());
        return list;
    }
}