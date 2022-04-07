package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.UI;

/**
 * Deletes a task identified using it's displayed index from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            Task taskToDelete = taskList.get(index - 1);
            taskList.deleteTask(index - 1);
            ui.printTaskDeleted(taskToDelete.toString(), taskList.getNumberOfTask());
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorTaskDoesNotExist(String.valueOf(index));
        }
    }

}
