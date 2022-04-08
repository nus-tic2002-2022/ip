package duke.commands;

import duke.data.entity.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

import static duke.common.Messages.MESSAGE_TASK_SHOW;
import static duke.common.Messages.MESSAGE_ZERO_TASK;

/**
 * Extension of Command class to list commands from Task List.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        tasks = new TaskList(storage.load());
        if (tasks.getSize() == 0) {
            ui.showTaskSize(tasks.getSize());
            ui.showToUser(MESSAGE_ZERO_TASK);}
        else {
            ui.showToUserWithTAB(MESSAGE_TASK_SHOW);
            tasks = new TaskList(storage.load());
            ui.showTask(tasks);
        }
    }
}