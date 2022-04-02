package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.ArrayList;


public class ListCommand extends Command {

    private static final String REPLY_MESSAGE = "\tHere are the tasks in your list:";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.show(REPLY_MESSAGE);
        ArrayList<Task> tasks = taskList.getListOfSavedTask();
        for (int i = 0; i < tasks.size(); i++) {
            ui.show("\t" + (i+1) + "." + tasks.get(i).toString());
        }
    }
}
