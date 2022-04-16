package duke.commands;

import duke.common.JCode;
import duke.data.entity.Task;
import duke.data.entity.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.List;

import static duke.common.Messages.MESSAGE_RESULT_NOT_FOUND;
import static duke.common.Messages.MESSAGE_RESULT_TASK_SHOW;

/**
 * Extension of Command class to find string from Task List.
 * find task from the Tasklist
 * show list of task when found
 * show MESSAGE_RESULT_NOT_FOUND when not found *
 */
public class FindCommand extends Command {


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks = new TaskList(storage.load());
        assert super.getTask() != null : "task to find cannot be empty";
        List<Task> resultArray = JCode.findTask(tasks, super.getTask());
        if (resultArray.size() == 0)
            ui.showToUser(MESSAGE_RESULT_NOT_FOUND);
        else {
            TaskList resultTList = new TaskList(resultArray);
            ui.showToUserWithTAB(MESSAGE_RESULT_TASK_SHOW);
            ui.showTask(resultTList);
        }
    }
}