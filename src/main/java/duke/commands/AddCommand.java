package duke.commands;

import duke.common.JCode;
import duke.data.entity.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

import static duke.common.Messages.MESSAGE_TASK_DUPLICATE;

/**
 * Extension of Command class to add commands into Task List.
 * Check duplicate task from the list
 * 1. Add
 * 2. Show added task to user
 * 3. Show updated list size
 * if duplicated
 * 1. show duplicate message to user
 */
public class AddCommand extends Command {


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks = new TaskList(storage.load());
        if (super.getTask() != null) {
            if (JCode.notDuplicate(tasks, super.getTask())) {
                tasks.add(super.getTask());
                ui.showAdded(super.getTask());
                ui.showTaskSize(tasks.getSize());
                storage.save(tasks.getTasks());
            } else ui.showToUserWithTAB(MESSAGE_TASK_DUPLICATE);
        }

    }
}
