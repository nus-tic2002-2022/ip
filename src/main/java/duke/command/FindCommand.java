package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.ArrayList;
import static duke.command.ListCommand.showTaskFound;

/**
 * Command to {@link TaskList} to find if specified tasks exists in the list
 */
public class FindCommand extends Command {

    private static final String REPLY_MESSAGE = "Here are the tasks in your list:";
    private static final String NO_RECORD_MESSAGE = "0 record found.";
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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show("\t" + REPLY_MESSAGE);
        ArrayList<Task> tasks = taskList.findTaskByKeyword(args);

        if (!tasks.isEmpty()) {
            return showTaskFound(ui, tasks);
        } else {
            ui.show("\t\t" + NO_RECORD_MESSAGE);
            return NO_RECORD_MESSAGE;
        }
    }

}
