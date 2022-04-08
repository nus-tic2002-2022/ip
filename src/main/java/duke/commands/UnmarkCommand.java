package duke.commands;

import duke.data.entity.Task;
import duke.data.entity.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

import static duke.common.Messages.MESSAGE_TASK_UNMARK;

/**
 * Extension of Command class to unmark task from Task List.
 */
public class UnmarkCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks = new TaskList(storage.load());
        if(isAll()) {
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                if (tasks.getTasks().get(i).isDone()) {
                    tasks.getTasks().get(i).markAsNotDone();
                    ui.showToUserWithTAB(tasks.getTasks().get(i).toString()+MESSAGE_TASK_UNMARK);
                }
            }
        } else {
            /*
              Check if user key in invalid number
             */
            if (super.getIndex() < 0 || super.getIndex() >= tasks.getSize()) {
                throw new DukeException("☹ OOPS!!! The index that want to unmark is not in the list.");
            }
            Task toUnMark = tasks.getTasks().get(super.getIndex());
            if (!toUnMark.isDone())
                throw new DukeException("☹ OOPS!!! This task is not marked yet.");
            tasks.getTasks().get(super.getIndex()).markAsNotDone();
            ui.showUnMarked(tasks.getTasks().get(super.getIndex()));
        }
        ui.showTaskSize(tasks.getSize());
        storage.save(tasks.getTasks());
    }
}