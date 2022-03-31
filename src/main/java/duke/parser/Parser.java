package main.java.duke.parser;

import main.java.duke.commands.Command;
import main.java.duke.commands.Commands;
import main.java.duke.data.entity.Deadline;
import main.java.duke.data.entity.Event;
import main.java.duke.data.entity.Task;
import main.java.duke.data.entity.Todo;
import main.java.duke.data.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Parser {

    public static Command parse(String fullCommand) throws DukeException, ParseException {
        Command com = new Command();
        String first = fullCommand.split(" ")[0];
        if (first.equals(Commands.LIST.getCommand()) || first.equals(Commands.LIST.getShr())) {
            com.setCommands(Commands.LIST);
        } else if (first.equals(Commands.BYE.getCommand()) || first.equals(Commands.BYE.getShr())) {
            com.setCommands(Commands.BYE);
        } else if (first.equals(Commands.UNMARK.getCommand()) || first.equals(Commands.UNMARK.getShr())) {
            if (fullCommand.trim().equals(Commands.UNMARK.getCommand()))
                throw new DukeException("☹ OOPS!!! The index that want to unmark cannot be empty.");
            else {
                com.setCommands(Commands.UNMARK);
                com.setIndex(getIndex(fullCommand, first));
            }
        } else if (first.equals(Commands.MARK.getCommand()) || first.equals(Commands.MARK.getShr())) {
            if (fullCommand.trim().equals(Commands.MARK.getCommand()))
                throw new DukeException("☹ OOPS!!! The index that want to unmark cannot be empty.");
            else {
                com.setCommands(Commands.MARK);
                com.setIndex(getIndex(fullCommand, first));
            }
        } else if (first.equals(Commands.TODO.getCommand()) || first.equals(Commands.TODO.getShr())) {
            if (fullCommand.trim().equals(Commands.TODO.getCommand())||fullCommand.trim().equals(Commands.TODO.getShr()))
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            else {
                com.setCommands(Commands.TODO);
                Task tsk = getTask(fullCommand, first);
                if (tsk != null) {
                    com.setTask(tsk);
                }
            }
        } else if (first.equals(Commands.DEADLINE.getCommand()) || first.equals(Commands.DEADLINE.getShr())) {
            if (fullCommand.trim().equals(Commands.DEADLINE.getCommand())||fullCommand.trim().equals(Commands.DEADLINE.getShr()))
                throw new DukeException("☹ OOPS!!! The description of an deadline cannot be empty.");
            else {
                com.setCommands(Commands.DEADLINE);
                Task tsk = getTask(fullCommand, first);
                if (tsk != null) {
                    com.setTask(tsk);
                } else {
                    throw new DukeException("☹ OOPS!!! The description of an deadline or date of the deadline cannot be empty.");
                }
            }
        } else if (first.equals(Commands.EVENT.getCommand()) || first.equals(Commands.EVENT.getShr())) {
            if (fullCommand.trim().equals(Commands.EVENT.getCommand())||fullCommand.trim().equals(Commands.EVENT.getShr()))
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            else {
                com.setCommands(Commands.EVENT);
                Task tsk = getTask(fullCommand, first);
                if (tsk != null) {
                    com.setTask(tsk);
                } else {
                    throw new DukeException("☹ OOPS!!! The description of an event or date of the event cannot be empty.");
                }
            }
        } else if (first.equals(Commands.DELETE.getCommand()) || first.equals(Commands.DELETE.getShr())) {
            if (fullCommand.trim().equals(Commands.DELETE.getCommand())||fullCommand.trim().equals(Commands.DELETE.getShr()))
                throw new DukeException("☹ OOPS!!! The index that want to delete cannot be empty.");
            else {
                com.setCommands(Commands.DELETE);
                com.setIndex(getIndex(fullCommand, first));
            }
        }
        return com;
    }

    public static int getIndex(String fullCommand, String command) {
        int i = Integer.parseInt(fullCommand.substring(command.length() + 1));
        return i - 1;
    }

    public static Task getTask(String fullCommand, String command) throws ParseException, DukeException {
        Task tsk = new Task();
        try {
            if (command.equals(Commands.TODO.getCommand()) || command.equals(Commands.TODO.getShr())) {
                tsk = new Todo(fullCommand.substring(command.length() + 1));
            }System.out.println(command);
            if (command.equals(Commands.EVENT.getCommand()) || command.equals(Commands.EVENT.getShr()) ||
                    command.equals(Commands.DEADLINE.getCommand()) || command.equals(Commands.DEADLINE.getShr())) {
                int buffer = fullCommand.indexOf("/");
                if (buffer == -1)
                    return null;
                String name = fullCommand.substring(command.length() + 1, buffer - 1);
                String date = fullCommand.substring(buffer + 4);
                System.out.println(name+" "+date);
                //Check if user key in empty event description or event date
                if (name.equals("") || date.equals("")) {
                    return null;
                }
                Date dateObj = null;

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
                dateObj = sdf.parse(date);
                //Else check event or deadline
                if (command.equals(Commands.EVENT.getCommand()) || command.equals(Commands.EVENT.getShr()))
                    tsk = new Event(name, dateObj);
                if (command.equals(Commands.DEADLINE.getCommand()) || command.equals(Commands.DEADLINE.getShr()))
                    tsk = new Deadline(name, dateObj);
            }
        } catch (StringIndexOutOfBoundsException e) {
            if (command.equals(Commands.TODO.getCommand()) || command.equals(Commands.TODO.getShr())) {
                throw new DukeException("Please type as [todo task] or [t task]");
            }
            if (command.equals(Commands.DEADLINE.getCommand()) || command.equals(Commands.DEADLINE.getShr())) {
                throw new DukeException("Please type as [deadline return book /by 02/12/2022 1200] or [d return book /by 02/12/2022 1200]");
            }
            if (command.equals(Commands.EVENT.getCommand()) || command.equals(Commands.EVENT.getShr())) {
                throw new DukeException("Please type as [event graduation /on 02/12/2022 1200] or [e graduation /on 02/12/2022 1200]");
            }
        }
        return tsk;
    }
}
