package duke.commands;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.tasklist.Deadline;
import duke.tasklist.Event;
import duke.tasklist.Task;
import duke.tasklist.TaskList;

import java.time.LocalDateTime;

/**
 * Extended class of Command with methods involving addition of Task into TaskList.
 */
public class AddCommand extends Command{

    /**
     * Returns Task after adding Task created based on userInput into the TaskList.
     *
     * @param userInput User Input obtain from UI.
     * @param taskList TaskList which specified Task should be added to.
     * @return Task as Task that has been added into TaskList.
     */
    public static Task addTodo (Parser userInput, TaskList taskList) throws DukeException{
        String content = "";
        for (int i = 1; i < userInput.getUserInputSize(); i++) {
            content = content + " " + userInput.getUserInput(i);
        }
        content = content.stripLeading();
        content = content.stripTrailing();
        if (content.equalsIgnoreCase("")){
            throw new DukeException("missing task");
        } else {
            Task curTask = new Task(content);
            taskList.add(curTask);
            return curTask;
        }
    }

    /**
     * Returns Deadline after adding Deadline created based on userInput into the TaskList.
     *
     * @param userInput User Input obtain from UI.
     * @param taskList TaskList which specified Task should be added to.
     * @return Deadline as Task that has been added into TaskList.
     */
    public static Task addDeadline (Parser userInput, TaskList taskList) throws DukeException{
        String content = "";
        LocalDateTime by = null;
        for (int i = 1; i < userInput.getUserInputSize()-1; i++) {
            if (!userInput.getUserInput(i).equalsIgnoreCase("/by")) {
                content = content + " " + userInput.getUserInput(i);
            } else {
                by = Parser.dateTimeParser(i+1);
                break;
            }
        }
        content = content.stripLeading();
        content = content.stripTrailing();
        if (content.equalsIgnoreCase("")) {
            if (by == null){
                throw new DukeException("missing task & missing by");
            } else {
                throw new DukeException("missing task");
            }
        }
        else {
            if (by == null) {
                throw new DukeException("missing by");
            } else {
                Deadline curTask = new Deadline(content, by);
                taskList.add(curTask);
                return curTask;
            }
        }
    }

    /**
     * Returns Event after adding Event created based on userInput into the TaskList.
     *
     * @param userInput User Input obtain from UI.
     * @param taskList TaskList which specified Task should be added to.
     * @return Event as Task that has been added into TaskList.
     */
    public static Task addEvent (Parser userInput, TaskList taskList) throws DukeException {
        String content = "";
        LocalDateTime at = null;
        for (int i = 1; i < userInput.getUserInputSize(); i++) {
            if (!userInput.getUserInput(i).equalsIgnoreCase("/at")) {
                content = content + " " + userInput.getUserInput(i);
            } else {
                at = Parser.dateTimeParser(i+1);
                break;
            }
        }
        content = content.stripLeading();
        content = content.stripTrailing();
        if (content.equalsIgnoreCase("")) {
            if (at == null){
                throw new DukeException("missing task & missing at");
            } else {
                throw new DukeException("missing task");
            }
        } else {
            if (at == null){
                throw new DukeException("missing at");
            } else {
                Event curTask = new Event(content, at);
                taskList.add(curTask);
                return curTask;
            }
        }
    }

}
