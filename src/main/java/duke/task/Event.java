package duke.task;

import duke.Storage;
import duke.commands.Command;
import duke.exceptions.*;

import java.util.List;

public class Event extends Task{

    private String time;

    public Event(String description, String time){
        super(description);
        this.time = time;
    }
    public static void checkTime(int index)throws DukeCheckLineException{
        if(index == -1){
            throw new DukeCheckLineException();
        }
    }
    public static void checkDescription(int index)throws DukeException{
        if(index == 1){
            throw new DukeException();
        }
    }
    public Event(String isDone,String description, String time){
        super(isDone,description);
        this.time = time;
    }
    @Override
    public String toSavePattern() {
        String mode = super.isDone  == true? "1":"0";
        return "D | "+mode+" | "+super.description+" | "+this.time;
    }

    public static Command getCommand(TaskList tasks, Storage storage){
        return fullCommand -> {
            List<String> commandList = List.of(fullCommand);
            int position = commandList.indexOf("/at");
            try{
                checkDescription(position);
                checkTime(position);
                String description = String.join(" ", commandList.subList(1, position));
                String time = String.join(" ", commandList.subList(position + 1, fullCommand.length));
                Task eventTask = new Event(description, time);
                tasks.add(eventTask);
                storage.store(tasks.convertAsLines());
                return List.of("Got it. I've added this task: " + System.lineSeparator() +
                        "     " + eventTask + System.lineSeparator() +
                        "   Now you have " + tasks.size() + " tasks in the list.");
            }catch(DukeException e){
                return List.of("☹ OOPS!!! The description of a " + "Event" + " cannot be empty.");
            }catch(DukeCheckLineException e){
                return List.of("☹ OOPS!!! An event must have a time.");
            }
        };
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}