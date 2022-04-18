package zhixuan.duke.storage;

import zhixuan.duke.commands.AddCommand;

/**
 * Handler for decoding of task strings in file
 *
 **/
public class TaskDecoder {

    /**
     * Splits and organize strings into task type
     * Calls AddCommand to add tasks based on task type
     *
     * @param input full task string from file
     *
     * @return true if task decoded and added to tasklist
     **/
    public static boolean decodeTask(String input) {

        // split takes a regular expression and | is a special character (means 'or').
        // requires additional \\ in regex since \ is Java's escape character in a string.
        // Java understands the string like "\|", and the regex then understands it like "|"

        String[] task = input.split("\\|");

        if (input.startsWith("T")) {
            return new AddCommand(true, "todo", Boolean.parseBoolean((task[1].trim())), task[2].trim()).execute();
        }
        else if (input.startsWith("E")) {
            return new AddCommand(true, "event", Boolean.parseBoolean((task[1].trim())), task[2] + " /at " + task[3].trim()).execute();
        }
        else if (input.startsWith("D")) {
            return new AddCommand(true, "deadline", Boolean.parseBoolean((task[1].trim())), task[2] + " /by " + task[3].trim()).execute();
        }
        return false;
    }

}
