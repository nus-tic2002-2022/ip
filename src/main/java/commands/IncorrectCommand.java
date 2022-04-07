package commands;

import storage.Storage;
import tasks.TaskList;
import ui.UI;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command {

    private final String errorMessage;

    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.printError(errorMessage);
    }
}
