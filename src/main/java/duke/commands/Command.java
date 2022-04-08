package duke.commands;

import duke.data.entity.Task;
import duke.data.entity.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

import static duke.common.Messages.MESSAGE_ERROR;
import static duke.common.Messages.MESSAGE_GOODBYE;


/**
 * Represents an executable command.
 */
public class Command {

    private Commands commands;
    private Task task;
    private int index;
    private boolean isAll = false;

    /**
     * setter for isAll
     * @param all to delete all
     */
    public void setAll(boolean all) {
        isAll = all;
    }

    /**
     * getter for isAll
     *
     * @return isAll
     */
    public boolean isAll() {
        return isAll;
    }

    /**
     * setter for index
     *
     * @param index index of the list
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * getter for index
     *
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * setter for Commands
     *
     * @param commands
     */
    public void setCommands(Commands commands) {
        this.commands = commands;
    }

    /**
     * setter for Task
     *
     * @param task
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * getter for Task
     *
     * @return task
     */
    public Task getTask() {
        return task;
    }


    /**
     * Executes the command and returns the result.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (this.commands != null) {
            if (this.commands == Commands.BYE) {
                ui.showToUser(MESSAGE_GOODBYE);
            }
        } else ui.showError(MESSAGE_ERROR);
    }

    /**
     * Exit the program when user type 'Bye'.
     */
    public boolean isExit() {
        if (this.commands != null) {
            return commands.equals(Commands.BYE);
        } else return false;
    }
}