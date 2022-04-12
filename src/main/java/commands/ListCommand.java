package commands;

import storage.Storage;
import tasks.TaskList;
import ui.UI;


/**
 * Lists all tasks in the Task List.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = "Lists all tasks in the task List \n"
                                               + "usage: list";

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.printAllTasks(taskList);
    }
}
