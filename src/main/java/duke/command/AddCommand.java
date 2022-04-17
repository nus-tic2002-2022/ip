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

    private static final String REPLY_MESSAGE = "Got it. I've added this task:";
    private static final String DUPLICATE_MESSAGE = "This task is already exists!";
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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.checkHasDuplicate(task)) {
            ui.show("\t" + DUPLICATE_MESSAGE);
            return (DUPLICATE_MESSAGE);
        } else {
            ui.show("\t" + REPLY_MESSAGE);
            taskList.addTask(task);
            String newTask = task.toString();
            ui.show("\t\t" + newTask);
            ui.show("\tNow you have " + taskList.getSize() + " tasks in the list");

            try {
                storage.save(taskList);
            } catch (IOException e) {
                ui.show(e.getMessage());
            }

            return REPLY_MESSAGE
                    + System.lineSeparator()
                    + newTask
                    + System.lineSeparator()
                    + "Now you have " + taskList.getSize() + " tasks in the list";
        }

    }

}
