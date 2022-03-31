package duke.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import duke.commands.Command;
import duke.commands.Commands;
import duke.data.entity.*;
import duke.data.exception.DukeException;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        Command com = new Command();
        if (fullCommand.trim().equals(Commands.LIST.getCommand())) {
            com.setCommands(Commands.LIST);
        }
        else if (fullCommand.trim().equals(Commands.BYE.getCommand())) {
            com.setCommands(Commands.BYE);
        }
        else if (fullCommand.contains(Commands.UNMARK.getCommand())) {
            if (fullCommand.trim().equals(Commands.UNMARK.getCommand()))
                throw new DukeException("☹ OOPS!!! The index that want to unmark cannot be empty.");
            else {
                com.setCommands(Commands.UNMARK);
                com.setIndex(getIndex(fullCommand, Commands.UNMARK.getCommand()));
            }
        }
        else if (fullCommand.contains(Commands.MARK.getCommand())) {
            if (fullCommand.trim().equals(Commands.MARK.getCommand()))
                throw new DukeException("☹ OOPS!!! The index that want to unmark cannot be empty.");
            else {
                com.setCommands(Commands.MARK);
                com.setIndex(getIndex(fullCommand, Commands.MARK.getCommand()));
            }
        }
        else if (fullCommand.contains(Commands.TODO.getCommand())) {
            if (fullCommand.trim().equals(Commands.TODO.getCommand()))
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            else {
                com.setCommands(Commands.TODO);
                Task tsk = getTask(fullCommand, Commands.TODO.getCommand());
                if (tsk != null) {
                    com.setTask(tsk);
                }
            }
        }
        else if (fullCommand.contains(Commands.DEADLINE.getCommand())) {
            if (fullCommand.trim().equals(Commands.DEADLINE.getCommand()))
                throw new DukeException("☹ OOPS!!! The description of an deadline cannot be empty.");
            else {
                com.setCommands(Commands.DEADLINE);
                Task tsk = getTask(fullCommand, Commands.DEADLINE.getCommand());
                if (tsk != null) {
                    com.setTask(tsk);
                } else {
                    throw new DukeException("☹ OOPS!!! The description of an deadline or date of the deadline cannot be empty.");
                }
            }
        }
        else if (fullCommand.contains(Commands.EVENT.getCommand())) {
            if (fullCommand.trim().equals(Commands.EVENT.getCommand()))
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            else {
                com.setCommands(Commands.EVENT);
                Task tsk = getTask(fullCommand, Commands.EVENT.getCommand());
                if (tsk != null) {
                    com.setTask(tsk);
                } else {
                    throw new DukeException("☹ OOPS!!! The description of an event or date of the event cannot be empty.");
                }
            }
        }
        else if (fullCommand.contains(Commands.DELETE.getCommand())) {
            if (fullCommand.trim().equals(Commands.DELETE.getCommand()))
                throw new DukeException("☹ OOPS!!! The index that want to delete cannot be empty.");
            else {
                com.setCommands(Commands.DELETE);
                com.setIndex(getIndex(fullCommand, Commands.DELETE.getCommand()));
            }
        }
        return com;
    }

    public static int getIndex(String fullCommand, String command) {
        int i = Integer.parseInt(fullCommand.substring(command.length() + 1));
        return i-1;
    }

    public static Task getTask(String fullCommand, String command) {
        Task tsk = new Task();
        if (command.equals(Commands.TODO.getCommand())) {
            tsk = new Todo(fullCommand.substring(command.length() + 1));
        }
        if (command.equals(Commands.EVENT.getCommand()) || command.equals(Commands.DEADLINE.getCommand())) {
            int buffer = fullCommand.indexOf("/");
            if(buffer==-1)
                return null;
            String name = fullCommand.substring(command.length()+1, buffer - 1);
            String date = fullCommand.substring(buffer + 4);
            //Check if user key in empty event description or event date
            if (name.equals("") || date.equals("")) {
                return null;
            }
            //Else check event or deadline
            if (command.equals(Commands.EVENT.getCommand()))
                tsk = new Event(name, date);
            if (command.equals(Commands.DEADLINE.getCommand()))
                tsk = new Deadline(name, date);
        }
        return tsk;
    }


}
