package parser;

import commands.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;

public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern DEADLINE_FORMAT= Pattern.compile("(?<description>.*) /by (?<date>.*)");
    public static final Pattern EVENT_FORMAT= Pattern.compile("(?<description>.*) /at (?<date>.*)");

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

            case MarkDoneCommand.COMMAND_WORD:
                return prepareMarkDone(arguments);

            case UnmarkDoneCommand.COMMAND_WORD:
                return prepareUnmarkDone(arguments);

            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);
/*
            case FindCommand.COMMAND_WORD:
                return prepareFind(arguments);
*/
            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            default:
                return new IncorrectCommand("Command does not exist");
        }
    }

    private Command prepareTodo(String arguments){
        if(arguments.isEmpty()){
            return new IncorrectCommand("Description is missing!");
        }else{
            Task taskToAdd = new Todo(arguments);
            return new AddCommand(taskToAdd);
        }
    }

    private Command prepareDeadline(String arguments){
        final Matcher matcher = DEADLINE_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("Date or Description is missing!");
        }

        try {
            final String description = matcher.group("description");
            final String dateString = matcher.group("date");
            final Date date = DateParser.parseDate(dateString);

            Task taskToAdd = new Deadline(description, date);
            return new AddCommand((taskToAdd));
        }catch(IndexOutOfBoundsException e) {
            return new IncorrectCommand("Invalid Date");
        }

    }

    private Command prepareEvent(String arguments){
        final Matcher matcher = EVENT_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("Date or Description is missing!");
        }
        try{
            final String description = matcher.group("description");
            final String dateString = matcher.group("date");
            final Date date = DateParser.parseDate(dateString);

            Task taskToAdd = new Event(description, date);
            return new AddCommand((taskToAdd));
        }catch(IndexOutOfBoundsException e){
            return new IncorrectCommand("Invalid Date");
        }

    }

    private Command prepareMarkDone(String arguments){
        try{
            int taskNumber = Integer.parseInt(arguments);
            return new MarkDoneCommand(taskNumber);
        }catch(NumberFormatException e) {
            return new IncorrectCommand("Invalid task number: " + arguments);
        }
    }

    private Command prepareUnmarkDone(String arguments){
        try{
            int taskNumber = Integer.parseInt(arguments);
            return new UnmarkDoneCommand(taskNumber);
        }catch(NumberFormatException e) {
            return new IncorrectCommand("Invalid task number: " + arguments);
        }
    }

    private Command prepareDelete(String arguments){
        try{
            int taskNumber = Integer.parseInt(arguments);
            return new DeleteCommand(taskNumber);
        }catch(NumberFormatException e) {
            return new IncorrectCommand("Invalid task number: " + arguments);
        }
    }


}
