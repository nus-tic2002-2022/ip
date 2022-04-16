package zhixuan.duke.storage;

import zhixuan.duke.commands.AddCommand;
import zhixuan.duke.data.task.Deadline;
import zhixuan.duke.data.task.Event;
import zhixuan.duke.data.task.Task;
import zhixuan.duke.data.task.Todo;


public class TaskDecoder {

    public static boolean decodeTask(String input) {

        //split takes a regular expression and | is a special character (means 'or').
        // requires additional \\ in regex since \ is Java's escape character in a string.
        // Java understands the string like "\|", and the regex then understands it like "|"

        String[] task = input.split("\\|");

        if (input.startsWith("T")) {
            return new AddCommand("todo", Boolean.parseBoolean((task[1].trim())), task[2].trim()).execute();
        }
        else if (input.startsWith("E")) {
            return new AddCommand(task[2].trim(), Boolean.parseBoolean((task[1].trim())), task[3].trim()).execute();
        }
        else if (input.startsWith("D")) {
            return new AddCommand(task[2].trim(), Boolean.parseBoolean((task[1].trim())), task[3].trim()).execute();
        }
        return false;
    }

}
