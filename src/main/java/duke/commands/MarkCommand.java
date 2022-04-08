package duke.commands;

import duke.data.entity.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

import static duke.common.Messages.MESSAGE_TASK_MARK;

/**
 * Extension of Command class to mark task from Task List.
 */
public class MarkCommand  extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks = new TaskList(storage.load());
        if (isAll()) {
            for(int i = 0 ;i<tasks.getTasks().size();i++){
                if (!tasks.getTasks().get(i).isDone()) {
                    tasks.getTasks().get(i).markAsDone();
                    ui.showToUserWithTAB(tasks.getTasks().get(i).toString()+MESSAGE_TASK_MARK);
                }
            }
            ui.showTaskSize(tasks.getTasks().size());
            storage.save(tasks.getTasks());
        }else {
            if (super.getIndex() < 0 || super.getIndex() >= tasks.getSize()) {
                throw new DukeException("☹ OOPS!!! The index that want to mark is not in the list.");
            }
            tasks.getTasks().get(super.getIndex()).markAsDone();
            ui.showMarked(tasks.getTasks().get(super.getIndex()));
            ui.showTaskSize(tasks.getSize());
            storage.save(tasks.getTasks());
        }
    }
}