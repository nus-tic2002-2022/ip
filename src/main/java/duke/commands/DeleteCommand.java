package duke.commands;

import duke.data.entity.Task;
import duke.data.entity.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

import static duke.common.Messages.MESSAGE_TASK_DELETE;

/**
 * Extension of Command class to delete commands from Task List.
 * Check user command (delete all tasks?)
 * 1. show deleted tasks to user
 * 2. create new TaskList *
 * if delete 1
 * Check index, if valid index
 * 1. delete from the list
 *
 * Show updated list size
 * save list to the file
 *
 */
public class DeleteCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks = new TaskList(storage.load());
        if (isAll()) {
            ui.showToUserWithTAB(MESSAGE_TASK_DELETE);
            for (Task t : tasks.getTasks()) {
                ui.showToUserWithTAB(t.toString());
            }
            tasks = new TaskList();
        } else {
            if (super.getIndex() < 0 || super.getIndex() >= tasks.getSize()) {
                throw new DukeException("☹ OOPS!!! There is no such task to delete.");
            }
            Task deleted = tasks.delete(super.getIndex());
            ui.showDeleted(deleted);
        }

        ui.showTaskSize(tasks.getSize());
        storage.save(tasks.getTasks());
    }

}