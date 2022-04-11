package duke.task;

import java.util.ArrayList;
import java.util.List;
import duke.Storage;
import duke.command.Command;
import duke.exceptions.*;

public class Event extends Time{


    public Event(String description, String time){
        super(description,time);
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
    /**
     * To generate a Event detail and store in the tasks list according to user input.
     *
     * @param tasks the list of tasks;
     * @param storage to save the event detail of the task.
     * @return a command which generates events task.
     */
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
                return List.of(" OOPS!!! The description of a " + "Event" + " cannot be empty.");
            }catch(DukeCheckLineException e){
                return List.of(" OOPS!!! An event must have a time.");
            }
        };
    }

    /**
     * Return a list of strings that can be saved.
     *
     * @return a task list for saving.
     */
    @Override
    public List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("E");
        list.addAll(super.getList());
        list.add(convertSaveTimeString());
        return list;
    }

    /**
     * Return a list of strings to user.
     *
     * @return this string task.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + convertTimeString() + ")";
    }
}
