package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Command to {@link TaskList} to delete a task from the task list
 */
public class DeleteCommand extends Command {

    private static final String REPLY_MESSAGE = "\tNoted. I've removed this task:";
    private final String args;

    public DeleteCommand(String args) {
        this.args = args;
    }

    /**
     * Execute DeleteCommand to delete a task
     * @param taskList the task list a task is deleted from
     * @param ui the CLI to show message to users
     * @param storage the file to update after a task is deleted
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        try {
            int taskNumber = Parser.getTaskNumber(args);
            ui.show(REPLY_MESSAGE);
            ui.show("\t" + taskList.getTaskByIndex(taskNumber).toString());
            taskList.deleteTask(taskNumber);
            ui.show("\tNow you have " + taskList.getSize() + " tasks in the list");
        } catch (Exception e) {
            throw new DukeException(DukeException.INVALID_TASK_NUMBER + "[" + args + "] /!\\");
        }

        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.show(e.getMessage());
        }
    }

}
