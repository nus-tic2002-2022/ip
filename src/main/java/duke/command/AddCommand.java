package duke.command;


import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.io.IOException;


/**
 * Command to {@link TaskList} to add a new task
 */
public class AddCommand extends Command{

    private static final String REPLY_MESSAGE = "\tGot it. I've added this task:";
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute AddCommand to add a  task into a task list, including duplicate check
     * @param taskList the task list a new task is added in
     * @param ui the CLI to show message to users
     * @param storage the file to save a new task
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.checkHasDuplicate(task)) {
            ui.show("\t This task has already exists!");
        } else {
            ui.show(REPLY_MESSAGE);
            taskList.addTask(task);
            ui.show("\t\t" + task.toString());
            ui.show("\tNow you have " + taskList.getSize() + " tasks in the list");

            try {
                storage.save(taskList);
            } catch (IOException e) {
                ui.show(e.getMessage());
            }
        }

    }

}
