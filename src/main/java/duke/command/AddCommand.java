package duke.command;

import duke.Tasklist.Task;
import duke.UI.UI;
import duke.Storage.fileaccess;

import java.util.ArrayList;

public class AddCommand extends Command {

    protected Task passed_task;
    public AddCommand(String passed, Task t_passed)
    {
        super(passed);
        this.passed_task=t_passed;
    }


    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute (ArrayList<Task> tasklist, UI ui, fileaccess f)
    {
        tasklist.add(passed_task);
        UI.showGotit();
        passed_task.getStatus();
        UI.showAdd(tasklist.size());
    }
}
