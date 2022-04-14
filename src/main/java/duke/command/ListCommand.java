package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.ArrayList;

/**
 * Command to show all the tasks in the list
 */
public class ListCommand extends Command {

    private static final String REPLY_MESSAGE = "\tHere are the tasks in your list:";

    /**
     * Execute ListCommand to list down all the tasks in the list
     * @param taskList the task list that contains all the tasks
     * @param ui the CLI to show all the tasks
     * @param storage is not used in this method
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show(REPLY_MESSAGE);
        ArrayList<Task> tasks = taskList.getListOfSavedTask();

        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                ui.show("\t" + (i+1) + "." + tasks.get(i).toString());
            }
        } else {
            ui.show("\t\t0 record found.");
        }

    }
}
