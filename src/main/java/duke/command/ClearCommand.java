package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ClearCommand extends Command {

    private static final String REPLY_MESSAGE = "\tNoted. I've cleared your list!";

    /**
     * Execute ClearCommand to clear all the tasks in the list
     * @param taskList the list that contains all the task
     * @param ui the CLI to show message to users
     * @param storage the file to update after clearing all the tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        ui.show(REPLY_MESSAGE);
        taskList.deleteAllTask();

        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.show(e.getMessage());
        }
    }
}
