package duke.common;

import duke.commands.*;
import duke.data.entity.*;
import duke.data.exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static duke.common.Messages.MESSAGE_DATE_FORMAT_ERROR;

public class JCode {
    /**
     * function to check parameter is equal to "bye" or "b"
     *
     * @param toCheck - command string
     * @return boolean
     **/
    public static boolean isBYE(String toCheck) {
        return toCheck.equals(Commands.BYE.getCommand()) || toCheck.equals(Commands.BYE.getShr());
    }

    /**
     * function to check parameter is equal to "delete" or "del"
     *
     * @param toCheck - command string
     * @return boolean
     **/
    public static boolean isDELETE(String toCheck) {
        return toCheck.equals(Commands.DELETE.getCommand()) || toCheck.equals(Commands.DELETE.getShr());
    }

    /**
     * function to check parameter is equal to "deadline " or "d "
     *
     * @param toCheck- command string
     * @return boolean
     **/
    public static boolean isDEADLINE(String toCheck) {
        return toCheck.equals(Commands.DEADLINE.getCommand()) || toCheck.equals(Commands.DEADLINE.getShr());
    }

    /**
     * function to check parameter is equal to "event" or "e"
     *
     * @param toCheck- command string
     * @return boolean
     */
    public static boolean isEVENT(String toCheck) {
        return toCheck.equals(Commands.EVENT.getCommand()) || toCheck.equals(Commands.EVENT.getShr());
    }

    /**
     * function to check parameter is equal to "event" or "e"
     *
     * @param toCheck- command string
     * @return boolean
     */
    public static boolean isFIND(String toCheck) {
        return toCheck.equals(Commands.FIND.getCommand()) || toCheck.equals(Commands.FIND.getShr());
    }

    /**
     * function to check parameter is equal to "list" or "l"
     *
     * @param toCheck- command string
     * @return boolean
     */
    public static boolean isLIST(String toCheck) {
        return toCheck.equals(Commands.LIST.getCommand()) || toCheck.equals(Commands.LIST.getShr());
    }

    /**
     * function to check parameter is equal to "mark" or "m"
     *
     * @param toCheck- command string
     * @return boolean
     */
    public static boolean isMARK(String toCheck) {
        return toCheck.equals(Commands.MARK.getCommand()) || toCheck.equals(Commands.MARK.getShr());
    }

    /**
     * function to check parameter is equal to "mark" or "m"
     *
     * @param toCheck- command string
     * @return boolean
     */
    public static boolean isUNMARK(String toCheck) {
        return toCheck.equals(Commands.UNMARK.getCommand()) || toCheck.equals(Commands.UNMARK.getShr());
    }

    /**
     * function to check parameter is equal to todo or t
     *
     * @param toCheck- command string
     * @return boolean
     */
    public static boolean isTODO(String toCheck) {
        return toCheck.equals(Commands.TODO.getCommand()) || toCheck.equals(Commands.TODO.getShr());
    }

    /**
     * function to check parameter is equal to which Commands
     *
     * @param commandStr- command string
     * @return Commands
     */
    public static Commands getCommands(String commandStr) {
        if (JCode.isLIST(commandStr))
            return Commands.LIST;
        if (JCode.isBYE(commandStr))
            return Commands.BYE;
        if (JCode.isFIND(commandStr))
            return Commands.FIND;
        if (JCode.isUNMARK(commandStr))
            return Commands.UNMARK;
        if (JCode.isMARK(commandStr))
            return Commands.MARK;
        if (JCode.isTODO(commandStr))
            return Commands.TODO;
        if (JCode.isDEADLINE(commandStr))
            return Commands.DEADLINE;
        if (JCode.isEVENT(commandStr))
            return Commands.EVENT;
        if (JCode.isDELETE(commandStr))
            return Commands.DELETE;
        return null;
    }

    /**
     * check fullCommand and split it for description,
     *
     * @param fullCommand,command - the full user command and the first user command string
     * @return tsk after tokenize and check user input.
     */
    private static Task getTask(String fullCommand, String command) throws DukeException {
        Task tsk = new Task();
        try {
            if (JCode.isFIND(command)) {
                tsk.setDescription(fullCommand.substring(command.length() + 1));
            }
            if (JCode.isTODO(command)) {
                tsk = new Todo(fullCommand.substring(command.length() + 1));
            }
            if (JCode.isEVENT(command) || JCode.isDEADLINE(command)) {
                int buffer = -1;
                if (JCode.isEVENT(command)) buffer = fullCommand.indexOf("/on");
                if (JCode.isDEADLINE(command)) buffer = fullCommand.indexOf("/by");
                if (buffer == -1) {
                    assert command!=null: "command cannot be empty";
                    throw checkNoBufferError(command);
                }
                String name = fullCommand.substring(command.length() + 1, buffer - 1);
                String date = fullCommand.substring(buffer + 4);
                if (name.equals("") || date.equals("")) {
                    return null;
                }
                LocalDateTime ld = changeToLocalDateTime(date);
                LocalDateTime now = LocalDateTime.now();
                /*
                  Compare user input date with today date.
                 */
                if (ld.isBefore(now)) {
                    throw new DukeException("Sorry! Cannot schedule tasks in the past.");
                } else {
                    if (JCode.isEVENT(command)) tsk = new Event(name, ld);
                    if (JCode.isDEADLINE(command)) tsk = new Deadline(name, ld);
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            assert command!=null: "command cannot be empty";
            throw checkIndexOutOfBoundError(command);
        } catch (DateTimeException e) {
            throw new DukeException(MESSAGE_DATE_FORMAT_ERROR);
        }
        return tsk;
    }

    /**
     * @param command - command string
     * @return DukeException de
     */
    public static DukeException checkNoBufferError(String command) {
        DukeException de = new DukeException("");
        if (JCode.isDEADLINE(command))
            de = new DukeException(
                    "☹ OOPS!!! Something wrong with your format. Format should be like this : deadline return book /by 2/12/2022 18:00 ");
        if (JCode.isEVENT(command))
            de = new DukeException(
                    "☹ OOPS!!! Something wrong with your format. Format should be like this : event java test /on 11/4/2022 9:00 ");
        return de;
    }

    /**
     * @param command - command string
     * @return DukeException de
     */
    public static DukeException checkIndexOutOfBoundError(String command) {
        DukeException de = new DukeException("");
        if (JCode.isTODO(command)) {
            de = new DukeException("Please type as [todo task] or [t task]");
        }
        if (JCode.isDEADLINE(command)) {
            de = new DukeException(
                    "Please type as [deadline return book /by 02/12/2022 1200] or [d return book /by 02/12/2022 1200]");
        }
        if (JCode.isEVENT(command)) {
            de = new DukeException(
                    "Please type as [event graduation /on 02/12/2022 1200] or [e graduation /on 02/12/2022 1200]");
        }
        return de;
    }


    /**
     * @return index to process the command.
     */
    private static int getIndex(String fullCommand, String command) {
        int i = Integer.parseInt(getStringIndex(fullCommand, command));
        return i - 1;
    }

    /**
     * @return substring of the fullCommand
     */
    private static String getStringIndex(String fullCommand, String command) {
        return fullCommand.substring(command.length() + 1);
    }

    /**
     * check num is Number or not
     *
     * @param num - string to check
     * @return boolean
     */
    public static boolean isNumeric(String num) {
        if (num == null) {
            return false;
        }
        try {
            Integer.parseInt(num);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * check num is date or not
     *
     * @param str - string to check
     * @return boolean
     */
    public static boolean isDate(String str) {
        if (str == null) {
            return false;
        }
        try {
            changeToLocalDate(str);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }
    /**
     * check num is date or not
     *
     * @param str - string to check
     * @return boolean
     */
    public static boolean isDateTime(String str) {
        if (str == null) {
            return false;
        }
        try {
            changeToLocalDateTime(str);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    public static LocalDate changeToLocalDate(String strDate) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        if (strDate.contains("-")) {
            formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        }
        return LocalDate.parse(strDate, formatter);
    }

    public static LocalDateTime changeToLocalDateTime(String strDate) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
        if (strDate.contains("-")) {
            formatter = DateTimeFormatter.ofPattern("d-M-yyyy H:m");
        }
        return LocalDateTime.parse(strDate, formatter);
    }

    public static boolean isEqualDate(LocalDateTime ldt, String dtStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate ld = ldt.toLocalDate();
        try {
            if (ld.compareTo(LocalDate.parse(dtStr, formatter)) == 0)
                return true;
        } catch (DateTimeException e) {
            return false;
        }
        return false;
    }

    public static boolean isEqualDateTime(LocalDateTime ldt, String dtStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
        try {
            if (ldt.compareTo(LocalDateTime.parse(dtStr, formatter)) == 0)
                return true;
        } catch (DateTimeException e) {
            return false;
        }
        return false;
    }

    /**
     * get the description of task and check is it date or text
     * if description is text, the description of task from the TaskList
     *
     * @param tasks,task - TaskList, Task
     * @return List<Task></Task>
     */
    public static List<Task> findTask(TaskList tasks, Task task) {
        List<Task> resultArray = new ArrayList<>();
        if (task != null) {
            String description = task.getDescription();
            for (Task t : tasks.getTasks()) {
                if (isDate(description)) {
                    if (t instanceof Deadline && isEqualDate(((Deadline) t).getBy(), description)) {
                        resultArray.add(t);
                        continue;
                    }
                    if (t instanceof Event && isEqualDate(((Event) t).getAt(), description)) {
                        resultArray.add(t);
                        continue;
                    }
                }
                if (isDateTime(description)) {
                    if (t instanceof Deadline && isEqualDateTime(((Deadline) t).getBy(), description)) {
                        resultArray.add(t);
                        continue;
                    }
                    if (t instanceof Event && isEqualDateTime(((Event) t).getAt(), description)) {
                        resultArray.add(t);
                        continue;
                    }
                }
                if (t.getDescription().contains(description)) {
                    resultArray.add(t);
                }
            }
        }
        return resultArray;
    }


    /**
     * Check task is duplicated in the tasks or not
     * @return boolean
     */
    public static boolean notDuplicate(TaskList tasks, Task task) {
        List<Task> resultArray = new ArrayList<>();
        if (task != null) {
            String description = task.getDescription();
            for (Task t : tasks.getTasks()) {
                if (t.getDescription().equals(description)) {
                    resultArray.add(t);
                }
            }
        }
        return (resultArray.size() == 0);
    }

    /**
     * Set necessary property to com
     * @param com - command to set property
     * @param fullCommand - full line of user command
     * @param first - first word of user command
     * @return command after setting properties
     */
    private static Command setIndexOrAll(Command com, String fullCommand, String first) {
        String index = getStringIndex(fullCommand, first);
        if (isNumeric(index)) {
            com.setIndex(getIndex(fullCommand, first));
        } else {
            if (index.equalsIgnoreCase("all"))
                com.setAll(true);
            else
                com.setIndex(-1);
        }
        return com;
    }

    /**
     * initialise MarkCommand and call setIndexOrAll function to set property
     * @param fullCommand - full line of user command
     * @param first - first word of user command
     * @return MarkCommand
     */
    public static Command getMarkCommand(String fullCommand, String first) {
        Command com = new MarkCommand();
        return setIndexOrAll(com, fullCommand, first);
    }
    /**
     * initialise UnmarkCommand and call setIndexOrAll function to set property
     * @param fullCommand - full line of user command
     * @param first - first word of user command
     * @return UnmarkCommand
     */
    public static Command getUnmarkCommand(String fullCommand, String first) {
        Command com = new UnmarkCommand();
        return setIndexOrAll(com, fullCommand, first);
    }
    /**
     * initialise DeleteCommand and call setIndexOrAll function to set property
     * @param fullCommand - full line of user command
     * @param first - first word of user command
     * @return DeleteCommand
     */
    public static Command getDeleteCommand(String fullCommand, String first) {
        Command com = new DeleteCommand();
        return setIndexOrAll(com, fullCommand, first);
    }

    /**
     * initialise FindCommand
     * @param fullCommand - full line of user command
     * @param first - first word of user command
     * @return FindCommand
     */
    public static Command getFindCommand(String fullCommand, String first) throws DukeException {
        Command com = new FindCommand();
        Task toFind = getTask(fullCommand, first);
        if (toFind != null) {
            com.setTask(toFind);
        }
        return com;
    }
    /**
     * initialise AddCommand
     * @param fullCommand - full line of user command
     * @param first - first word of user command
     * @return AddCommand
     */
    public static Command getAddCommand(String fullCommand, String first) throws DukeException {
        Command com = new AddCommand();
        Task tsk = getTask(fullCommand, first);
        if (tsk != null) {
            com.setTask(tsk);
        }
        return com;
    }

}
