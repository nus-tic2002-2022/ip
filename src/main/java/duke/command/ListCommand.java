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

    private static final String REPLY_MESSAGE = "Here are the tasks in your list:";

    /**
     * Execute ListCommand to list down all the tasks in the list
     * @param taskList the task list that contains all the tasks
     * @param ui the CLI to show all the tasks
     * @param storage is not used in this method
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show("\t" + REPLY_MESSAGE);
        ArrayList<Task> tasks = taskList.getListOfSavedTask();

        if (!tasks.isEmpty()) {
            return showTaskFound(ui, tasks);
        } else {
            ui.show("\t\t0 record found.");
            return "0 record found.";
        }

    }

    /**
     * Return and show the tasks in list
     * @param ui the CLI to show the tasks
     * @param tasks the tasks in the list
     * @return the tasks converted to String
     */
    protected static String showTaskFound(Ui ui, ArrayList<Task> tasks) {
        String tasksFound = "";
        for (int i = 0; i < tasks.size(); i++) {
            ui.show("\t" + (i+1) + "." + tasks.get(i).toString());
            tasksFound += (i+1) + "." + tasks.get(i).toString() + System.lineSeparator();
        }
        return REPLY_MESSAGE + System.lineSeparator() + tasksFound.trim();
    }
}
