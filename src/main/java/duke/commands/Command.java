package duke.commands;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.tasklist.Task;
import duke.parser.Parser;

import java.io.IOException;

public class Command {

    protected Parser userInput;
    protected TaskList taskList;
    private int targetIndex;

    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    public void setData(Parser userInput, TaskList taskList) {
        this.userInput = userInput;
        this.taskList = taskList;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    public ResultCommand execute() throws DukeException, IOException {
        if (this.userInput.getUserInput(0).equalsIgnoreCase("todo")) {
            Task new_task = AddCommand.addTodo(this.userInput, this.taskList);
            return new ResultCommand("addTask", new_task);
        }
        else if (this.userInput.getUserInput(0).equalsIgnoreCase("deadline")) {
            Task new_task = AddCommand.addDeadline(this.userInput, this.taskList);
            return new ResultCommand("addTask", new_task);
        }
        else if (this.userInput.getUserInput(0).equalsIgnoreCase("event")) {
            Task new_task = AddCommand.addEvent(this.userInput, this.taskList);
            return new ResultCommand("addTask", new_task);
        }
        else if (this.userInput.getUserInput(0).equalsIgnoreCase("echo")) {
            String content = "";
            for (int i = 1; i < userInput.getUserInputSize(); i++) {
                content = content + " " + userInput.getUserInput(i);
            }
            return new ResultCommand("echo", content);
        }
        else if (this.userInput.getUserInput(0).equalsIgnoreCase("bye") || this.userInput.getUserInput(0).equalsIgnoreCase("exit")) {
            return new ResultCommand("goodbye", "");
        }
        else if (this.userInput.getUserInput(0).equalsIgnoreCase("hello")) {
            return new ResultCommand("hello", "");
        }
        else if (this.userInput.getUserInput(0).equalsIgnoreCase("list")) {
            ListCommand.showList(taskList);
            return new ResultCommand("noReply", "");
        }
        else if (this.userInput.getUserInput(0).equalsIgnoreCase("delete")) {
            DeleteCommand.deleteTask(this.userInput.getUserInput(1), taskList);
            return new ResultCommand("delete", "");
        }
        else if (this.userInput.getUserInput(0).equalsIgnoreCase("mark")) {
            boolean change = MarkCommand.markTask(this.userInput.getUserInput(1), taskList);
            if (change) {
                return new ResultCommand("updateList", "");
            }
            else {
                return new ResultCommand("markNoChange", "");
            }
        }
        else if (this.userInput.getUserInput(0).equalsIgnoreCase("unmark")) {
            boolean change = UnmarkCommand.unmarkTask(this.userInput.getUserInput(1), taskList);
            if (change) {
                return new ResultCommand("updateList", "");
            }
            else {
                return new ResultCommand("unmarkNoChange", "");
            }
        }
        else if (this.userInput.getUserInput(0).equalsIgnoreCase("save")) {
            return new ResultCommand("save", "");
        }
        else {
            return new ResultCommand("?", "");
        }
    }
}
