package duke.parser;

import duke.command.*;
import duke.exception.*;
import duke.task.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parse user inputs to get command and related info
 */
public class Parser {

    /**
     * Convert task number to integer from user input
     * @param args task number in string
     * @return task number in integer
     */
    public static int parseTaskNumber(String args) {
        return Integer.parseInt(args) - 1;
    }

    /**
     * Parse command and related info from user input
     * @param userInput source of command and related info
     * @return {@link Command} to be executed
     * @throws DukeException for showing customised exception message
     */
    public static Command parseCommand(String userInput) throws DukeException{
        Command command;
        String[] args = userInput.split(" ", 2);
        String commandWord = args[0].trim().toLowerCase();
        String[] desc;
        String taskNumber;
        String description;
        String taskDate;

        switch (commandWord) {
            case "bye":
                command = new ExitCommand();
                break;
            case "list":
                command = new ListCommand();
                break;
            case "clear":
                command = new ClearCommand();
                break;
            case "help":
                command = new HelpCommand();
                break;
            case "mark":
                try {
                    taskNumber = args[1].trim();
                } catch (Exception e) {
                    throw new DukeException(DukeException.MISSING_TASK_NUMBER);
                }
                command = new MarkCommand(taskNumber, true);
                break;
            case "unmark":
                try {
                    taskNumber = args[1].trim();
                } catch (Exception e) {
                    throw new DukeException(DukeException.MISSING_TASK_NUMBER);
                }
                command = new MarkCommand(taskNumber, false);
                break;
            case "todo":
                try {
                    description = args[1].trim();
                } catch (Exception e) {
                    throw new DukeException(DukeException.INVALID_TODO_FORMAT);
                }
                command = new AddCommand(new Todo(description));
                break;
            case "deadline":
                try {
                    desc = args[1].split("/by");
                    description = desc[0].trim();
                    taskDate = desc[1].trim();
                } catch (Exception e) {
                    throw new DukeException(DukeException.INVALID_DEADLINE_FORMAT);
                }
                command = new AddCommand(new Deadline(description, parseDate(taskDate)));
                break;
            case "event":
                try {
                    desc = args[1].split("/at");
                    description = desc[0].trim();
                    taskDate = desc[1].trim();
                } catch (Exception e) {
                    throw new DukeException(DukeException.INVALID_EVENT_FORMAT);
                }
                command = new AddCommand(new Event(description, parseDateTime(taskDate)));
                break;
            case "delete":
                try {
                    taskNumber = args[1].trim();
                } catch (Exception e) {
                    throw new DukeException(DukeException.MISSING_TASK_NUMBER);
                }
                command = new DeleteCommand(taskNumber);
                break;
            case "find":
                try {
                    description = args[1].trim();
                } catch (Exception e) {
                    throw new DukeException(DukeException.INVALID_FIND_FORMAT);
                }
                command = new FindCommand(description);
                break;
            default:
                throw new DukeException(DukeException.INVALID_COMMAND + "[" + commandWord + "] /!\\");
        }

        return command;

    }

    /**
     * Parse task date from user input
     * @param s user input in String
     * @return task date in YYYY-MM-DD format
     * @throws DukeException for showing customised exception message
     */
    public static LocalDate parseDate(String s) throws DukeException {
        LocalDate localDate;
        s = s.trim();

        try {
            localDate = LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.INVALID_DATE_FORMAT);
        }
        return localDate;
    }

    /**
     * Parse task datetime from user input
     * @param s user input in String
     * @return task datetime in YYYY-MM-DD HH:mm format
     * @throws DukeException for showing customised exception message
     */
    public static LocalDateTime parseDateTime(String s) throws DukeException {
        LocalDateTime localDateTime;
        s = s.trim();

        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            localDateTime = LocalDateTime.parse(s, format);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.INVALID_DATETIME_FORMAT);
        }
        return localDateTime;
    }

}
