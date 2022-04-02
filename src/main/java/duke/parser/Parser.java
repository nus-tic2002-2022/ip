package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static int getTaskNumber(String args) {
        return Integer.parseInt(args) - 1;
    }

    public Command parseCommand(String userInput) throws DukeException{
        Command command = null;
        String[] args = userInput.split(" ", 2);
        String commandWord = args[0].trim().toLowerCase();
        String[] desc;

        switch (commandWord) {
            case "bye":
                command = new ExitCommand();
                break;
            case "list":
                command = new ListCommand();
                break;
            case "mark":
                command = new MarkCommand(args[1]);
                break;
            case "unmark":
                command = new UnmarkCommand(args[1]);
                break;
            case "todo":
                command = new AddCommand(new Todo(args[1]));
                break;
            case "deadline":
                desc = args[1].split("/by");
                command = new AddCommand(new Deadline(desc[0], desc[1]));
                break;
            case "event":
                desc = args[1].split("/at");
                command = new AddCommand(new Event(desc[0], desc[1]));
                break;
            case "delete":
                command = new DeleteCommand(args[1]);
                break;
            default:
                throw new DukeException("This command [" + commandWord + "] is not a valid command");
        }

        return command;

    }

    public Command parseCommandFromFile(String data) {
        Command command = null;
        String[] args = data.split("\\|", 2);
        String commandWord = args[0].trim();
        String[] desc;

        switch (commandWord) {
            case "T":
                desc = data.split("\\|",3);
                command = new AddCommand(new Todo(desc[2].stripLeading()));
                break;
            case "D":
                desc = data.split("\\|",4);
                command = new AddCommand(new Deadline(desc[2].stripLeading(), desc[3]));
                break;
            case "E":
                desc = data.split("\\|",4);
                command = new AddCommand(new Event(desc[2].stripLeading(), desc[3]));
                break;
        }

        return command;
    }

    public Command parseDoneStatusFromFile(String data, int count) {

        Command command = null;
        String[] args = data.split("\\|", 3);
        String status = args[1].trim();

        switch (status) {
            case "0":
                command = new UnmarkCommand(Integer.toString(count));
                break;
            case "1":
                command = new MarkCommand(Integer.toString(count));
                break;
        }

        return command;
    }
}
