package duke.commands;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.tasklist.Task;
import duke.parser.Parser;

import java.io.IOException;

/**
 * This class will store the userInput provided by Parser class, taskList to be worked on, and targetIndex of specific task for operation (if applicable).
 * Based on the stored information, specific operations will be executed based on command and variables provided in userInput on taskList with targetIndex,
 * Information on executed actions (successful or unsuccessful) will be returned as ResultCommand.
 */
public class Command {

    protected Parser userInput;
    protected TaskList taskList;
    private int targetIndex;

    /**
     * Constructor for Command
     *
     * @param targetIndex index of Task which operation should be done on (in integer format).
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    /**
     * Constructor for Command
     */
    protected Command() {
    }

    /**
     * Update userInput and taskList with the specified variables.
     *
     * @param userInput User Input obtain from UI.
     * @param taskList TaskList to do operation on.
     */
    public void setData(Parser userInput, TaskList taskList) {
        this.userInput = userInput;
        this.taskList = taskList;
    }

    /**
     * Return targetIndex variable stored in Command class
     *
     * @return targetIndex in int format.
     */
    public int getTargetIndex() {
        return targetIndex;
    }

    /**
     * Update targetIndex with the specified variables.
     *
     * @param targetIndex index of specific Task which should be operated on (in int format).
     */
    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Execute operation based on command and variables provided in userInput and return information on executed actions as ResultCommand.
     * Command includes : todo, deadline, event, echo, bye, exit, hello, list, delete, mark, unmark, save, free, find.
     *
     * @return Information on executed actions (successful or unsuccessful) will be returned as ResultCommand.
     */
    public ResultCommand execute() throws DukeException, IOException {
        String keyword = this.userInput.getUserInput(0);
        switch (keyword) {
            case "todo":
                Task newTodoTask = AddCommand.addTodo(this.userInput, this.taskList);
                return new ResultCommand("addTask", newTodoTask);

            case "deadline":
                Task newDeadlineTask = AddCommand.addDeadline(this.userInput, this.taskList);
                return new ResultCommand("addTask", newDeadlineTask);

            case "event":
                Task newEventTask = AddCommand.addEvent(this.userInput, this.taskList);
                return new ResultCommand("addTask", newEventTask);

            case "echo":
                String content = "";
                for (int i = 1; i < userInput.getUserInputSize(); i++) {
                    content = content + " " + userInput.getUserInput(i);
                }
                return new ResultCommand("echo", content);

            case "bye":

            case "exit":
                return new ResultCommand("goodbye", "");

            case "hello":
                return new ResultCommand("hello", "");

            case "list":
                ListCommand.showList(taskList);
                return new ResultCommand("noReply", "");

            case "delete":
                DeleteCommand.deleteTask(this.userInput.getUserInput(1), taskList);
                return new ResultCommand("delete", "");

            case "mark":
                boolean isMarked = MarkCommand.markTask(this.userInput.getUserInput(1), taskList);
                if (isMarked) {
                    return new ResultCommand("updateList", "");
                } else {
                    return new ResultCommand("markNoChange", "");
                }

            case "unmark":
                boolean isUnmarked = UnmarkCommand.unmarkTask(this.userInput.getUserInput(1), taskList);
                if (isUnmarked) {
                    return new ResultCommand("updateList", "");
                } else {
                    return new ResultCommand("unmarkNoChange", "");
                }

            case "save":
                return new ResultCommand("save", "");

            case "free":
                String message = FindCommand.findFreeTime(taskList);
                return new ResultCommand("freeTime", message);

            case "find":
                TaskList found = FindCommand.findTask(this.userInput, taskList);
                return new ResultCommand("find", found);

            case "update":
                Task updatedTask = UpdateCommand.updateDescription(this.userInput, taskList);
                return new ResultCommand("updateTask", updatedTask);

            default:
                return new ResultCommand("?", "");
        }
    }
}
