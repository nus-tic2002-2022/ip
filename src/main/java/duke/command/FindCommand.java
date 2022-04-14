package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.ArrayList;

/**
 * Command to {@link TaskList} to find if specified tasks exists in the list
 */
public class FindCommand extends Command {

    private static final String REPLY_MESSAGE = "\tHere are the tasks in your list:";
    private final String args;

    public FindCommand(String args) {
        this.args = args;
    }

    /**
     * Execute FindCommand to find tasks by keyword
     * @param taskList the task list to search from
     * @param ui the CLI to show the tasks found
     * @param storage is not used in this method
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show(REPLY_MESSAGE);
        ArrayList<Task> tasks = taskList.findTaskByKeyword(args);

        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                ui.show("\t" + (i+1) + "." + tasks.get(i).toString());
            }
        } else {
            ui.show("\t\t0 record found.");
        }
    }
}
