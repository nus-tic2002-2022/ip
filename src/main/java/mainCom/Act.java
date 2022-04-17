package mainCom;
import subTask.*;

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