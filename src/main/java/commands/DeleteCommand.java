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

    public static final String MESSAGE_USAGE = "Delete tasks from the Task List \n"
                                               + "usage: delete <taskid> or all or marked \n\n"
                                               + "Eg. delete 1 \n "
                                               + "Deletes the first task on the list \n\n"
                                               + "Eg. delete all \n"
                                               + "Deletes all tasks on the list \n\n"
                                               + "Eg. delete marked \n"
                                               + "Deletes all marked tasks on the list";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            Task taskToDelete = taskList.getTask(index - 1);
            taskList.deleteTask(index - 1);
            ui.printTaskDeleted(taskToDelete.toString(), taskList.getNumberOfTask());
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.printErrorTaskDoesNotExist(String.valueOf(index));
        }
    }

}
