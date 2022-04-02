package duke.command;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Command {

    private static final String REPLY_MESSAGE = "\tNoted. I've removed this task:";
    private String args;
    public DeleteCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int taskNumber = Parser.getTaskNumber(args);
        ArrayList<Task> tasks = taskList.getListOfSavedTask();

        ui.show(REPLY_MESSAGE);
        ui.show("\t [X] " + tasks.get(taskNumber).toString());
        taskList.deleteTask(taskNumber);
        ui.show("\tNow you have " + TaskList.taskCount + " tasks in the list");
    }

}
