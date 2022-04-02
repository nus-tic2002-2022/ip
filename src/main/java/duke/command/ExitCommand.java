package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    private static boolean isExit = false;

    public static boolean getIsExit() {
        return isExit;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showByeMessage();
        isExit = true;
    }
}
