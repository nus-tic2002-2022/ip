package duke.task;

import duke.Storage;
import duke.commands.Command;
import duke.exceptions.*;
import duke.task.Task;
import java.util.List;


public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public Deadline(String isDone,String description, String by){
        super(isDone,description);
        this.by = by;
    }
    @Override
    public String toSavePattern() {
        String mode = super.isDone  == true? "1":"0";
        return "D | "+mode+" | "+super.description+" | "+this.by;
    }
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    public static void checkDescription(int index)throws DukeException{
        if(index == 1){
            throw new DukeException();
        }
    }
    public static void checkTime(int index)throws DukeCheckLineException{
        if(index == -1){
            throw new DukeCheckLineException();
        }
    }

    public static Command getCommand(TaskList tasks, Storage storage){
        return fullCommand -> {
            List<String> commandList = List.of(fullCommand);
            int position = commandList.indexOf("/by");
            try{
                checkDescription(position);
                checkTime(position);
                String description = String.join(" ", commandList.subList(1, position));
                String time = String.join(" ", commandList.subList(position + 1, fullCommand.length));
                Task deadlineTask = new Deadline(description, time);
                tasks.add(deadlineTask);
                storage.store(tasks.convertAsLines());
                return List.of("Got it. I've added this task: " + System.lineSeparator() +
                        "     " + deadlineTask + System.lineSeparator() +
                        "   Now you have " + tasks.size() + " tasks in the list.");
            }catch(DukeException e){
                return List.of("☹ OOPS!!! The description of a " + "Deadline" + " cannot be empty.");
            }catch(DukeCheckLineException e){
                return List.of("☹ OOPS!!! A deadline must have a time.");
            }
        };
    }
}