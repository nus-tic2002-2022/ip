package parser;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.IncorrectCommand;
import commands.ListCommand;
import commands.MarkDoneCommand;
import commands.MassDeleteCommand;
import commands.UnmarkDoneCommand;
import commands.UpdateCommand;
import commands.ViewCommand;
import exceptions.TooManyDatesException;
import tasks.Deadline;
import tasks.Event;
import tasks.FixedDuration;
import tasks.Task;
import tasks.Todo;

/**
 * Parses user input.
 */
public class Parser {
    /** Used for initial separation of command word and args.*/
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /** Used for initial separation of deadline description and date.*/
    private static final Pattern DEADLINE_FORMAT = Pattern.compile("(?<description>.*) /by (?<date>.*)");

    /** Used for initial separation of event description and date.*/
    private static final Pattern EVENT_FORMAT = Pattern.compile("(?<description>.*) /at (?<date>.*)");

    /** Used for initial separation of event description and date.*/
    private static final Pattern FIXED_FORMAT = Pattern.compile("(?<duration>\\d+) (?<description>.*)");

    /** Used for checking if the user input for updating a task is in the right format.*/
    private static final Pattern UPDATE_FORMAT = Pattern.compile("\\d+ (desc|date) (?<content>.*)");


    /**
     * Parses user input into commands for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("Invalid Command");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments").trim();


        switch (commandWord) {

        case AddCommand.COMMAND_WORD_TODO:
            return prepareTodo(arguments);

        case AddCommand.COMMAND_WORD_DEADLINE:
            return prepareDeadline(arguments);

        case AddCommand.COMMAND_WORD_EVENT:
            return prepareEvent(arguments);

        case AddCommand.COMMAND_WORD_FIXED:
            return prepareFixed(arguments);

        case MarkDoneCommand.COMMAND_WORD:
            return prepareMarkDone(arguments);

        case UnmarkDoneCommand.COMMAND_WORD:
            return prepareUnmarkDone(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);

        case UpdateCommand.COMMAND_WORD:
            return prepareUpdate(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_WORD_2:
            return new HelpCommand(arguments);

        case ViewCommand.COMMAND_WORD:
            return prepareView(arguments);

        default:
            return new IncorrectCommand("Command does not exist");
        }
    }

    /**
     * Parses arguments in the context of the view date command.
     *
     * @param arguments full command args string
     * @return the prepared command
     */
    private Command prepareView(String arguments) {
        try {
            final Date date = DateParser.parseDate(arguments);
            return new ViewCommand(date);
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand("Invalid Date");
        } catch (TooManyDatesException e) {
            return new IncorrectCommand("Too many Dates Provided");
        }
    }

    /**
     * Parses arguments in the context of the find tasks command.
     *
     * @param arguments full command args string
     * @return the prepared command
     */
    private Command prepareFind(String arguments) {
        if (arguments.isEmpty()) {
            return new IncorrectCommand("Search term is missing!");
        } else {
            return new FindCommand(arguments);
        }
    }

    /**
     * Parses arguments in the context of the update tasks command.
     *
     * @param arguments full command args string
     * @return the prepared command
     */
    private Command prepareUpdate(String arguments) {
        final Matcher matcher = UPDATE_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("Wrong Format of Update Command");
        }
        assert !arguments.isEmpty() : "Arguments cannot be empty";

        String[] splittedString = arguments.split(" ", 3);

        String taskNumber = splittedString[0];
        String partToUpdate = splittedString[1];
        String newContent = splittedString[2];
        int taskNumberInInt;

        try {
            taskNumberInInt = Integer.parseInt(taskNumber);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Invalid task number: " + arguments);
        }

        if (partToUpdate.equalsIgnoreCase("desc")) {
            return new UpdateCommand(taskNumberInInt, partToUpdate, newContent);
        }

        if (partToUpdate.equalsIgnoreCase("date")) {
            try {
                final Date date = DateParser.parseDate(newContent);
                return new UpdateCommand(taskNumberInInt, partToUpdate, date);
            } catch (IndexOutOfBoundsException e) {
                return new IncorrectCommand("Invalid Date");
            } catch (TooManyDatesException e) {
                return new IncorrectCommand("Too many Dates Provided");
            }

        }
        return new IncorrectCommand("Wrong Format of Update Command");

    }


    /**
     * Parses arguments in the context of the add todotask command.
     *
     * @param arguments full command args string
     * @return the prepared command
     */
    private Command prepareTodo(String arguments) {
        if (arguments.isEmpty()) {
            return new IncorrectCommand("Description is missing!");
        } else {
            Task taskToAdd = new Todo(arguments);
            return new AddCommand(taskToAdd);
        }
    }


    /**
     * Parses arguments in the context of the add deadlinetask command.
     *
     * @param arguments full command args string
     * @return the prepared command
     */
    private Command prepareDeadline(String arguments) {
        final Matcher matcher = DEADLINE_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("Date or Description is missing!");
        }
        assert !arguments.isEmpty() : "Arguments cannot be empty";

        try {
            final String description = matcher.group("description");
            final String dateString = matcher.group("date");
            final Date date = DateParser.parseDate(dateString);

            Task taskToAdd = new Deadline(description, date);
            return new AddCommand((taskToAdd));
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand("Invalid Date");
        } catch (TooManyDatesException e) {
            return new IncorrectCommand("Too many Dates Provided");
        }

    }

    /**
     * Parses arguments in the context of the add event task command.
     *
     * @param arguments full command args string
     * @return the prepared command
     */
    private Command prepareEvent(String arguments) {
        final Matcher matcher = EVENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("Date or Description is missing!");
        }
        assert !arguments.isEmpty() : "Arguments cannot be empty";

        try {
            final String description = matcher.group("description");
            final String dateString = matcher.group("date");
            final Date date = DateParser.parseDate(dateString);

            Task taskToAdd = new Event(description, date);
            return new AddCommand((taskToAdd));
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand("Invalid Date");
        } catch (TooManyDatesException e) {
            return new IncorrectCommand("Too many Dates Provided");
        }

    }

    /**
     * Parses arguments in the context of the mark task as done command.
     *
     * @param arguments full command args string
     * @return the prepared command
     */
    private Command prepareMarkDone(String arguments) {
        try {
            int taskNumber = Integer.parseInt(arguments);
            return new MarkDoneCommand(taskNumber);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Invalid task number: " + arguments);
        }
    }

    /**
     * Parses arguments in the context of the unmark task as done command.
     *
     * @param arguments full command args string
     * @return the prepared command
     */
    private Command prepareUnmarkDone(String arguments) {
        try {
            int taskNumber = Integer.parseInt(arguments);
            return new UnmarkDoneCommand(taskNumber);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Invalid task number: " + arguments);
        }
    }

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param arguments full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String arguments) {

        if (arguments.equalsIgnoreCase("all") || arguments.equalsIgnoreCase("marked")) {
            return new MassDeleteCommand(arguments);
        }

        try {
            int taskNumber = Integer.parseInt(arguments);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Invalid task number: " + arguments);
        }
    }

    /**
     * Parses arguments in the context of the add FixedDurationTask command.
     *
     * @param arguments full command args string
     * @return the prepared command
     */
    private Command prepareFixed(String arguments) {
        final Matcher matcher = FIXED_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("Duration or Description is missing!");
        }
        assert !arguments.isEmpty() : "Arguments cannot be empty";
        try {
            final int duration = Integer.parseInt(matcher.group("duration"));
            final String description = matcher.group("description");
            Task taskToAdd = new FixedDuration(description, duration);
            return new AddCommand(taskToAdd);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Invalid duration: " + arguments);
        }

    }

}
