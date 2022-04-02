package duke.command;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.ArrayList;


public class MarkCommand extends Command{

    private static final String REPLY_MESSAGE = "\tNice! I've marked this task as done:";
    private String args;

    public MarkCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int taskNumber = Parser.getTaskNumber(args);
        taskList.markDone(taskNumber);
        ArrayList<Task> tasks = taskList.getListOfSavedTask();
        ui.show(REPLY_MESSAGE);
        ui.show("\t\t" + tasks.get(taskNumber).toString());
    }

}
