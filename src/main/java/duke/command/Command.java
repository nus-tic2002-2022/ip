package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;


/**
 * Abstract class to derive customised commands
 */
public abstract class Command {

    protected Command() {}

    /**
     * Abstract method to be implemented by derived command class
     * @param taskList the task list that contains the tasks
     * @param ui the CLI to show message to users
     * @param storage the file that keeps the tasks in hard disk
     * @throws DukeException for showing customised exception message
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

}
