package duke.command;

import duke.Tasklist.Task;
import duke.UI.UI;
import duke.Storage.fileaccess;

import java.util.ArrayList;

public class ExitCommand extends Command {

    public ExitCommand(String passed)
    {
        super(passed);
    }



    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(ArrayList<Task> tasklist, UI ui, fileaccess f) {
        this.isExit =true;
        System.out.println("Bye. Hope to see you again soon!");

    }
}
