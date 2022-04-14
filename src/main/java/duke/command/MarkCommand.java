package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;


/**
 * Command to mark the done status of a task
 */
public class MarkCommand extends Command{

    private static final String REPLY_MESSAGE_DONE = "\tNice! I've marked this task as done:";
    private static final String REPLY_MESSAGE_UNDONE = "\tNice! I've unmarked this task as done:";
    private final String args;
    private final boolean done;

    public MarkCommand(String args, boolean done) {
        this.args = args;
        this.done = done;
    }

    /**
     * Execute MarkCommand to update the done status of a task
     * @param taskList the task list that contains the tasks to update
     * @param ui the CLI to show the updated task
     * @param storage the file to update after the done status changes
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        try {
            int taskNumber = Parser.getTaskNumber(args);
            if (done) {
                taskList.markDone(taskNumber);
                ui.show(REPLY_MESSAGE_DONE);
            } else {
                taskList.unmarkDone(taskNumber);
                ui.show(REPLY_MESSAGE_UNDONE);
            }
            ui.show("\t\t" + taskList.getTaskByIndex(taskNumber).toString());
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
