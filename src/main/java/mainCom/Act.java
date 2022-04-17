package mainCom;
import subTask.*;

/**
 * This class registers the user-inputs and makes sense of the command that the program is supposed to execute.
 * It contains the following approved actions:
 *    List: Allows to list all the tasks inside TaskList
 *    Bye: Exits the Task Manager
 *    Done: Marks a task as completed
 *    Delete: Deletes a task from the TaskList
 *    Todo / Event / Deadline: Adds one of the tasks into the TaskList
 */


public class Act {
    protected static String input;

    public Act(String input) {
        Act.input = input;
    }

    public static void parse(String input) throws DukeException {
        if (input.trim().equalsIgnoreCase("bye")) {
            TKlist.Bye();
        } else if (input.trim().equalsIgnoreCase("list")) {
            TKlist.List();
        } else if (input.toLowerCase().contains("mark")) {
            TKlist.Mark(input);
        } else if (input.toLowerCase().contains("delete")) {
            TKlist.Delete(input);
        } else if (input.toLowerCase().contains("todo")) {
            TKlist.Todo(input);
        } else if (input.toLowerCase().contains("deadline")) {
            TKlist.Deadline(input);
        } else if (input.toLowerCase().contains("event")) {
            TKlist.Event(input);
        } else {
            throw new DukeException("OOPS!!! Please enter a valid task such as todo / deadline / event\n");
        }
    }
} 