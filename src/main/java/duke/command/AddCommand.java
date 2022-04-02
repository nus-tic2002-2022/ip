package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class AddCommand extends Command{

    private static final String REPLY_MESSAGE = "\tGot it. I've added this task:";

    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show(REPLY_MESSAGE);
        taskList.addTask(task);
        ui.show("\t\t" + task.toString());
        ui.show("\tNow you have " + TaskList.taskCount + " tasks in the list");
    }

}
