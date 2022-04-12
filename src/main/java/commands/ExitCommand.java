package commands;

import storage.Storage;
import tasks.TaskList;
import ui.UI;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = "Exits the application \n"
                                               + "usage: bye";

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
